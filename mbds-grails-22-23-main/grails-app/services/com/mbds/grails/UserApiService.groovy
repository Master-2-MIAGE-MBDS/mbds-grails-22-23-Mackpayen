package com.mbds.grails

import grails.gorm.transactions.Transactional
import org.bouncycastle.jcajce.provider.symmetric.AES

@Transactional
class UserApiService {

    def serviceMethod() {

    }
    int put(User user, body, Role roleCurrentUser) {
        def roleOfuser = user.getAuthorities().authority[0] // le role de l'utilisateur quon souhiate changer son role
        if (body.role ){
            // si lutilsateur qui demande le changement d'un role n'est pas un admin
            if (roleCurrentUser.authority != 'ROLE_ADMIN') {
                return 401
            }
            if (body.role != roleOfuser){
                // supprimer le role depuis la table UserRole de la persone qu'on souhaite changer son role
                UserRole.remove(user, user.getAuthorities()[0])

                Role role = Role.findByAuthority(body.role)
                // ajouter le role dans la table user role
                UserRole.create(user, role , true)
            }
            body.remove('role')
        }

        user.properties = body
        user.save(flush: true)



        return 200


    }

    int patch(User user, body, roleCurrentUser){
        def roleOfuser = user.getAuthorities().authority[0] // le role de l'utilisateur quon souhiate changer son role
        if (body.role != "" )// a voir !!!!!
            return 404
        if (body.role && body.role != '' ){
            // si lutilsateur qui demande le changement d'un role n'est pas un admin
            if (roleCurrentUser.authority != 'ROLE_ADMIN') {
                return 401
            }
            if (body.role != roleOfuser){
                // supprimer le role depuis la table UserRole de la persone qu'on souhaite changer son role
                UserRole.remove(user, user.getAuthorities()[0])

                Role role = Role.findByAuthority(body.role)
                // ajouter le role dans la table user role
                UserRole.create(user, role , true)
            }
            body.remove('role')
        }

        user.properties = body
        user.save(flush: true)

        return 200
    }

    def post(params){
        def user = new User(params)


        /*// si lutilsateur qui ajoute n'est pas un admin‡
        if (roleCurrentUser.authority != 'ROLE_ADMIN') {
            return 401
        }*/

        // si tu veux ajouter un role admmin il faut que tu sois admin

        Role role = Role.findByAuthority('ROLE_CLIENT')
        // ajouter le role dans la table userRole
        UserRole.create(user, role , true)

        user.save(flush: true, failOnError: true)

        return 200
    }

}
