package com.mbds.grails

import grails.util.Holders
import org.springframework.web.multipart.MultipartFile

class FileUtil {

    public static String getRootPath(){
        return Holders.servletContext?.getRealPath("")
    }


    public static File makeDirectory(String path){
        File file = new File(path)
        if (!file.exists()){
            file.mkdirs()
        }
        return file
    }

//    request.getFile("productFile")
    public static String uploadContactImage(Integer id, MultipartFile multipartFile){
        if (id && multipartFile){
            String contactImagePath = "${getRootPath()}illustration-image/"
            makeDirectory(contactImagePath)
            multipartFile.transferTo(new File(contactImagePath, id + "-" + multipartFile.originalFilename))
            return multipartFile.originalFilename
        }
        return ""
    }
}
