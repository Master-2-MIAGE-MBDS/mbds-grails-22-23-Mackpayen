package com.mbds.grails

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.http.HttpMethod

@Secured('ROLE_ADMIN')
class ApiController {

    def index() {}
    // CRUD USER
    // Methods: GET, PUT, DELETE, PATCH
    def user() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def userInstance = User.get(params.id)
                if (!userInstance)
                    return response.status = 404
                withFormat {
                    json { render userInstance as JSON }
                    xml { render userInstance as XML }
                }
                break

            case "PUT":
                if (!params.id)
                    return response.status = 400
                def oldUser = User.get(params.id);
                oldUser.properties = request.JSON;

                if (oldUser.save(flush: true)) {
                    response.status = 200 // OK
                    render oldUser as JSON
                } else {
                    response.status = 500 //Internal Server Error
                    render "Could not create new User due to errors:\n ${oldUser.errors}"
                }
                break
            case "PATCH":
                break
            case "DELETE":
                if (!params.id)
                    return response.status = 400
                else {
                    UserRole.removeAll(User.get(params.id))
                    def userInstance = User.get(params.id)
                    userInstance.delete(flush: true)
                    return response.status = 200
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }
    // Methods: GET All, POST
    def users() {
        switch (request.getMethod()) {
            case "POST":
                def user = new User(request.JSON)
                if (user.save()) {
                    def adminRole = new Role(authority: "ROLE_ADMIN").save()
                    UserRole.create(user, adminRole, true)
                    response.status = 201 // Created
                    render user as JSON
                } else {
                    response.status = 500
                    // Internal Server Error
                    render "Could not create new User due to errors:\n ${user.errors} \n ${params.user}"
                }
                break
            case "GET":
                def user = new User(request.JSON)
                def userInstance = User.findAll()
                if (!userInstance)
                    return response.status = 404
                withFormat {
                    json { render userInstance as JSON }
                    xml { render userInstance as XML }
                }
                break

        }
    }
    // Get User by Username
    def getUserByUserName(){
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def userInstance = User.findByUsername(params.id)
                if (!userInstance)
                    return response.status = 404
                withFormat {
                    json { render userInstance as JSON }
                    xml { render userInstance as XML }
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }

    // CRUD Roles

    // Add Role To User
    def addRoleToUser() {
        switch (request.getMethod()) {
            case "POST":
                if (!params.id)
                    return response.status = 400
                def user = User.get(params.id)
                if (user) {
                    def role = new Role(request.JSON)
                    UserRole.create(user, Role.findByAuthority(role.getAuthority()), true)
                    println(role)
                    println(user)
                    response.status = 201 // Created
                } else {
                    response.status = 500
                    // Internal Server Error
                    render "Could not create new User due to errors:\n ${user.errors} \n ${params.user}"
                }
                break

        }
    }
    // delete Role to User
    def deleteRoleToUser() {
        switch (request.getMethod()) {
            case "POST":
                if (!params.id)
                    return response.status = 400
                def user = User.get(params.id)
                if (user) {
                    def role = new Role(request.JSON)
                    UserRole.remove(user, Role.findByAuthority(role.getAuthority()))
                    response.status = 201 // Deleted
                } else {
                    response.status = 500
                    // Internal Server Error
                    render "Could not Delete RoleUser due to errors:\n ${user.errors} \n ${params.user}"
                }
                break

        }
    }

    // Get All Role By Id
    def rolesById() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def roles = Role.findById(params.id)

                if (!roles)
                    return response.status = 404
                withFormat {
                    json { render roles as JSON }
                    xml { render roles as XML }
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }
    //  Get All Role By User
    def rolesByUser() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def roles = UserRole.findAllByUser(User.findById(params.id)).role

                if (!roles)
                    return response.status = 404
                withFormat {
                    json { render roles as JSON }
                    xml { render roles as XML }
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }
    // Get Roles Unused by User
    def roleUnusedByUser() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def roles = UserRole.findAllByUser(User.findById(params.id)).role
                print(roles)
                List<Role> listRole = new ArrayList<>()
                for (Role r : Role.findAll()) {
                    if (!roles.contains(r)) listRole.add(r)
                }
                if (!listRole)
                    return response.status = 404
                withFormat {
                    json { render listRole as JSON }
                    xml { render listRole as XML }
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }

    // CRUD Annonce
    // Chart.js
    def getCountAnnonceByUser() {
        switch (request.getMethod()) {
            case "GET":
                def res = User.executeQuery(
                        "SELECT u.username, count(*) as ct FROM User u join u.annonces s group by u.username"
                )
                if (!res)
                    return response.status = 404
                withFormat {
                    json { render res as JSON }
                    xml { render res as XML }
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }

    // Get annonce By User
    def annonceByUser() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def annonce = User.findById(params.id).annonces.findAll()
                if (!annonce)
                    return response.status = 404
                withFormat {
                    json { render annonce as JSON }
                    xml { render annonce as XML }
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }
    // Methods: GET, PUT, DELETE, PATCH
    def annonce() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def annonceInstance = Annonce.get(params.id)
                if (!annonceInstance)
                    return response.status = 404
                withFormat {
                    json { render annonceInstance as JSON }
                    xml { render annonceInstance as XML }
                }
                break
            case "PUT":
                if (!params.id)
                    return response.status = 400
                def oldAnnonce = Annonce.get(params.id);
                oldAnnonce.properties = request.JSON;

                if (oldAnnonce.save(flush: true)) {
                    response.status = 200 // OK
                    render oldAnnonce as JSON
                } else {
                    response.status = 500 //Internal Server Error
                    render "Could not Update new Annonce due to errors:\n ${oldAnnonce.errors}"
                }
                break
            case "DELETE":
                if (!params.id)
                    return response.status = 400
                else {
                    def annonceInstance = Annonce.get(params.id)
                    annonceInstance.delete(flush: true)
                    return response.status = 200
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }
    // Methods: GET All, POST, DELETE, PATCH
    def annonces() {
        switch (request.getMethod()) {
            case "POST":
                def annonceInstance = new Annonce(request.JSON)
                if (annonceInstance.save()) {
                    response.status = 201 // Created
                    render annonceInstance as JSON
                } else {
                    response.status = 500
                    // Internal Server Error
                    render "Could not create new Annonce due to errors:\n ${annonceInstance.errors} \n ${params.user}"
                }
                break
        }
    }


    // CRUD Illustration

    // Methods: GET, PUT, DELETE, PATCH
    def illustrations() {
        switch (request.getMethod()) {
            case "POST":
                //("print")
                def illustrationInstance = new Illustration(request.JSON)
                if (illustrationInstance.save()) {
                    response.status = 201 // Created
                    render illustrationInstance as JSON
                } else {
                    response.status = 500
                    // Internal Server Error
                    render "Could not create new Illustration due to errors:\n ${illustrationInstance.errors} \n ${params.user}"
                }
                break
            case "GET":
                def illustrationInstance = Illustration.findAll()
                if (!illustrationInstance)
                    return response.status = 404
                withFormat {
                    json { render illustrationInstance as JSON }
                    xml { render illustrationInstance as XML }
                }
                break
            case "DELETE":
                if (!params.id)
                    return response.status = 400
                else {
                    def illustrationInstance = Illustration.get(params.id)
                    illustrationInstance.delete(flush: true)
                    return response.status = 200
                }
                break
            default:
                return response.status = 405
                break
        }
    }
    // Get illustration by Annonce
    def illustrationByAnnonce() {
        switch (request.getMethod()) {
            case "GET":
                if (!params.id)
                    return response.status = 400
                def illustrationInstance = Annonce.findById(params.id).illustrations
                if (!illustrationInstance)
                    return response.status = 404
                withFormat {
                    json { render illustrationInstance as JSON }
                    xml { render illustrationInstance as XML }
                }
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }

}
