package core.common;


import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lokesh
 */
public class FileUploadPath {
    
    public static String getFileUploadPath(HttpServletRequest request)
    {
        return request.getServletContext().getRealPath("/").replace("\\", "/")+"/";//request.getLocalName()+request.getContextPath();
    }
    
    public static String getFileDatabasePath(HttpServletRequest request,String uploadPath)
    {
        String servletPath=request.getServletContext().getRealPath("/").replace("\\", "/");
        String newPath=request.getRequestURL().toString();
        newPath=newPath.substring(0,newPath.lastIndexOf("/"));
//        String newPathAr[]=newPath.split("/");
//        newPath="";
//        for(int i=0;i<newPathAr.length-1;i++)
//            newPath=newPath+newPathAr[i]+"/";
        String dbPath=newPath+uploadPath.replace(servletPath,"");
        return dbPath;
    }
    
}
