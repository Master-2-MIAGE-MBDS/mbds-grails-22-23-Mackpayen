package com.mbds.grails

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class AnnonceApiService {

    def serviceMethod() {

    }
    // on passe en paramètre l'annonce qu'on veut changer avec les nouvelles valeurs Json
    int patch(Annonce annoce, def jsonVal) {

        if (jsonVal.title)
            annoce.title = jsonVal.title
        if (jsonVal.description)
            annoce.description = jsonVal.description
        if (jsonVal.price)
            annoce.price = jsonVal.price
        if (jsonVal.active)
            annoce.active = jsonVal.active
        if (jsonVal.author)
            annoce.author = jsonVal.author
        annoce.save(flush: true);
        return 200
    }

    int put(Annonce annoce, def json) {
        if (!json.title ||!json.description ||!json.username ||!json.price ||!json.active) {

            return 400
        } else {

            annoce.title = json.title
            annoce.description = json.description
            annoce.price = json.price as Float
            annoce.active = json.active
            annoce.author = User.findByUsername(json.username)
            annoce.save(flush: true)
            return 200
        }

    }

    int postAnnonce(params, file, src) {
        if (!params.title || !params.description || !params.price) {
            return 400
        } else if (!User.findByUsername(params.username)) {
            return 400
        } else {
            User userInstance = User.findByUsername(params.username)

            def annonceInstance = new Annonce(title: params.title, description: params.description, price: params.price
                    , author: userInstance, active: Boolean.TRUE)

            if(file) { // s'il ya un fichier
                def originalFileName = file.getOriginalFilename()

                if (params.fileName)
                    originalFileName = params.fileName + '.jpeg'

                File fileDest = new File(src + originalFileName)
                file.transferTo(fileDest)

                annonceInstance.addToIllustrations(new Illustration(filename: originalFileName))
            }
            userInstance.addToAnnonces(annonceInstance)
            userInstance.save(flush: true, failOnError: true)
            return 200
        }
    }
}
