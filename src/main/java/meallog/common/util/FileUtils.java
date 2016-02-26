package meallog.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import meallog.common.dao.AbstractDAO;
import meallog.meal.vo.Meal;

@Component("fileUtils")
public class FileUtils {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
    public List<Map<String,Object>> parseInsertFileInfo(Map<String, Object> meal,String filePath, HttpServletRequest request) throws Exception{
       MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
       Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
       log.debug("test1");
       MultipartFile multipartFile = null;
       String originalFileName = null;
       String originalFileExtension = null;
       String storedFileName = null;
       
       List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
       Map<String, Object> listMap = null; 
       log.debug("test2");
       
       log.debug("meal map : "+meal.get("IDX"));
//       String boardIdx = (String) meal.get("IDX");
//       log.debug("test3");
       String userName = (String) meal.get("USERNAME");
       
       
//       String boardIdx = Integer.toString(mealVO.getIDX());
//       String userName = mealVO.getUSERNAME();

       log.debug("filePAth : "+ filePath);
       String userFilePath = filePath+"meal"+File.separator+userName;
       
       log.debug("file upload : "+userFilePath);
       File file = new File(userFilePath);
       if(file.exists() == false){
           file.mkdirs();
       }
        
       while(iterator.hasNext()){
           multipartFile = multipartHttpServletRequest.getFile(iterator.next());
           if(multipartFile.isEmpty() == false){
               originalFileName = multipartFile.getOriginalFilename();
               originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
               storedFileName = CommonUtils.getRandomString() + originalFileExtension;
                
               file = new File(userFilePath +File.separator+ storedFileName);
               multipartFile.transferTo(file);
                
               listMap = new HashMap<String,Object>();
               listMap.put("BOARD_IDX", meal.get("IDX"));
               listMap.put("ORIGINAL_FILE_NAME", originalFileName);
               listMap.put("STORED_FILE_NAME", storedFileName);
               listMap.put("FILE_SIZE", multipartFile.getSize());
               listMap.put("USER_NICK", userName);
               list.add(listMap);
           }
       }
       return list;
   }
    
    public List<Map<String,Object>> parseInsertFileInfoMobile(Meal mealVO,String filePath, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        log.debug("-------------------------------------------------------");
        log.debug(multipartHttpServletRequest.getAttribute("file"));
        
        log.debug(multipartHttpServletRequest.getContentType());
        
        log.debug(multipartHttpServletRequest.getFile("image.jpg"));
        
        log.debug(multipartHttpServletRequest.getHeaderNames());
        
        log.debug(multipartHttpServletRequest.getFileMap().get("file"));
        
        log.debug(multipartHttpServletRequest);
        log.debug("-------------------------------------------------------");
        //Iterator<String> iterator2 = multipartHttpServletRequest.get();
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//        while(iterator2.hasNext()){
//        	log.debug("test");
//        	
//        }
        log.debug("test1");
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null; 
        log.debug("test2");
        String boardIdx = Integer.toString(mealVO.getIDX());
        String userName = mealVO.getUSERNAME();

        log.debug("filePAth : "+ filePath);
        String userFilePath = filePath+"meal"+File.separator+userName;
        
        log.debug("file upload : "+userFilePath);
        File file = new File(userFilePath);
        if(file.exists() == false){
            file.mkdirs();
        }
        log.debug("test3");
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            log.debug("test4");
            if(multipartFile.isEmpty() == false){
            	log.debug("test5");
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = CommonUtils.getRandomString() + originalFileExtension;
                 
                file = new File(userFilePath +File.separator+ storedFileName);
                multipartFile.transferTo(file);
                 
                listMap = new HashMap<String,Object>();
                listMap.put("BOARD_IDX", boardIdx);
                listMap.put("ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                listMap.put("USER_NICK", userName);
                list.add(listMap);
            }
        }
        return list;
    }
}
