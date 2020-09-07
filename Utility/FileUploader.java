package Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploader {
 public static ArrayList<String> getUploadedPath(ServletContext context,String parentDirectory,HttpServletRequest request)
   {
       String photo="";
       String img="";
       ArrayList<String> imagePath=new ArrayList();
       //System.out.println("reqqqqq:"+request);
       try{
              System.out.println("Multipart data found.......");    
                    //HttpSession session = request.getSession();
                     FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List items = null;
                    System.out.println("============");
                    items = upload.parseRequest(request);
                System.out.println("multi op="+items); 
                    Iterator itr = items.iterator();
                    while (itr.hasNext()) {
                        FileItem item = (FileItem) itr.next();
                        if (!item.isFormField()) {
                            photo = item.getName();
                            System.out.println("photo : "+ photo);
                            
                            img = parentDirectory + "/" + photo;
                            File savedFile = new File(context.getRealPath("/") + img);
                            item.write(savedFile);
           
                             imagePath.add(img);
                            }                                          
                        }
                  }catch(Exception e)
                  {
                      System.out.println("Error : "+e.getMessage());
                  }
  
       System.out.println("imagepath from fileuploader"+imagePath);
       return imagePath;
               
   }  
 public static String getUploadedPath1(ServletContext context,String parentDirectory,HttpServletRequest request)
   {
       String photo="";
       String img="";
       String imagePath=null;
       //System.out.println("reqqqqq:"+request);
       try{
              System.out.println("Multipart data found.......");    
                    //HttpSession session = request.getSession();
                     FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List items = null;
                    System.out.println("============");
                    items = upload.parseRequest(request);
                System.out.println("multi op="+items); 
                    Iterator itr = items.iterator();
                    while (itr.hasNext()) {
                        FileItem item = (FileItem) itr.next();
                        if (!item.isFormField()) {
                            photo = item.getName();
                            System.out.println("photo : "+ photo);
                            
                            img = parentDirectory + "/" + photo;
                            File savedFile = new File(context.getRealPath("/") + img);
                            item.write(savedFile);
           
                             imagePath=img;
                            }                                          
                        }
                  }catch(Exception e)
                  {
                      System.out.println("Error : "+e.getMessage());
                  }
  
       System.out.println("imagepath from fileuploader"+imagePath);
       return imagePath;
               
   }  
    
    
}