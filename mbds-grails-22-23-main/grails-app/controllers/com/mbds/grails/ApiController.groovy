package com.mbds.grails

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor
import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import jdk.nashorn.internal.runtime.UserAccessorProperty
import org.springframework.http.HttpMethod


@Secured(['ROLE_ADMIN', 'ROLE_CLIENT'])
class ApiController {
    UserService  userService;
    UserApiService userApiService;
    AnnonceService annonceService;
    AnnonceApiService annonceApiService;

    SpringSecurityService springSecurityService

    /**
     * Gestion des points d'entrée de l'application : GET / PUT / PATCH / DELETE
     */

    def annonce() {
        User currentUser = springSecurityService.getCurrentUser()
        Role role = currentUser.getAuthorities()[0]
        if (!params.id)
            return response.status = 400
        // On vérifie que l'id corresponde bien à une instance existante
        def annonceInstance = annonceService.get(params.id)
        if (!annonceInstance)
            return response.status = 404

        switch (request.getMethod()) {

            case "GET":
                renderThis(request.getHeader("Accept"), annonceInstance)
                break;

            case "PUT":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR'
                        || currentUser != annonceInstance.getAuthor()) {
                    return response.status = 401
                }
                def annonceJsonPut = JSON.parse(request);
                int result = annonceApiService.put(annonceInstance, annonceJsonPut)
                return response.status = result
                break;

            case "PATCH":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR'
                        || currentUser != annonceInstance.getAuthor()) {
                    return response.status = 401
                }
                def annonceJsonPatch = JSON.parse(request);
                int result = annonceApiService.patch(annonceInstance, annonceJsonPatch)
                return response.status = result
                break;
            case "DELETE":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR'
                        || currentUser != annonceInstance.getAuthor()) {
                    return response.status = 401
                }
                annonceInstance.delete(flush: true)
                return response.status = 200
                break;
            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }

    /**
     * Gestion d'un User ( GET, PUT, PATCH, DELETE ) à partir de l'API
     */
    def user() {
        User currentUser = springSecurityService.getCurrentUser()
        Role role = currentUser.getAuthorities()[0]

        // Vérification que l'identifiant a bien été renseigné

        if (!params.id)
            return response.status = 400

        def userId = params.id
        def userInstance = User.get(userId)
        if (!userInstance)
            return response.status = 404

        switch (request.getMethod()) {

            case "GET":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR'
                        || currentUser != userInstance.getUsername()) {
                    return response.status = 401
                }
                renderThis(request.getHeader("Accept"), userInstance)
                break;
            case "PUT":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR'
                        || currentUser != userInstance.getUsername()) {
                    return response.status = 401
                }
                def body =  request.getJSON()
                if (!userInstance || !body.username || !body.password ) {
                    return response.status = 404
                }
                Role roleCurrentUser = ((User)springSecurityService.getCurrentUser()).getAuthorities()[0]
                def res = userApiService.put(userInstance, body, roleCurrentUser)
                return response.status = res
                break;
            case "PATCH":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR'
                        || currentUser != userInstance.getUsername()) {
                    return response.status = 401
                }
                def body =  request.getJSON()
                Role roleCurrentUser = ((User)springSecurityService.getCurrentUser()).getAuthorities()[0]
                def res = userApiService.patch(userInstance, body, roleCurrentUser)
                return response.status = res
                break;
            case "DELETE":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR'
                        || currentUser != userInstance.getUsername()) {
                    return response.status = 401
                }
                userInstance.delete(flush: true)
                return response.status = 200
                break;
            default:
                return response.status = 405
                break;
        }
    }


    /**
     * Collection
     * POST / GET
     */
    def annonces() {
        User currentUser = springSecurityService.getCurrentUser()
        Role role = currentUser.getAuthorities()[0]
        def annonces = annonceService.list()
        switch (request.getMethod()) {

            case "GET":
                renderThis(request.getHeader("Accept"), annonces)
                break;

            case "POST":
                def file = request.getFile('illustration')
                def src = grailsApplication.config.illustrations.basePath
                int result = annonceApiService.postAnnonce(params, file, src)
                return response.status = result
                break;

            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }


    def users() {

        User currentUser = springSecurityService.getCurrentUser()
        Role role = currentUser.getAuthorities()[0]

        System.out.println(">>>>> methods " + request.getMethod())
        switch (request.getMethod()) {

            case "GET":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR') {
                    return response.status = 401
                }
                def users = userService.list()
                renderThis(request.getHeader("Accept"), users)
                break;

            case "POST":
                if (role.getAuthority() != 'ROLE_ADMIN' || role.getAuthority() != 'ROLE_MODERATEUR') {
                    return response.status = 401
                }
                if (User.findByUsername(params.username))
                    return response.status = 400
                if ( params == [:] || params.username == null || params.password == null )
                    return response.status = 400


                int res = userApiService.post(params)
                return response.status = res
                break;

            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }


    def renderThis(String acceptHeader, Object object) {
        System.out.println(">>>>>>> header " + acceptHeader)
        if (acceptHeader == '*/*')
            acceptHeader = 'application/json'
        switch (acceptHeader) {
            case 'xml':
            case 'text/xml':
            case 'application/xml':
                render object as XML
                break;
            case 'json':
            case 'text/json':
            case 'application/json':
                render object as JSON
                break;
        }
    }

    def responseStatus(String test, Object source ){

        switch (test){
            case 'id':
                System.out.println(">>>>> Verification ID ")
                if (!params.id)
                    return response.status = 400
                break;
            case 'SourceNotExist':
                System.out.println(">>>>> Verification Source ")
                if (source == null) {
                    System.out.println(">>>>> Source "+ source)
                    return response.status = 404
                }
                break;
            case 'NotAcceptable':
                System.out.println(">>>>> Method no accept ")
                return response.status = 405
                break;
            default:
                return response.status = 406
                break;

        }
    }
}
