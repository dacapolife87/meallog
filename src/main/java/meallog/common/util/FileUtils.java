package meallog.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import meallog.common.dao.AbstractDAO;

@Component("fileUtils")
public class FileUtils {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
//	private static final String filePath1 = "C:\\meallog\\file\\";
    public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map,String filePath, HttpServletRequest request) throws Exception{
       MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
       Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
       
       
       MultipartFile multipartFile = null;
       String originalFileName = null;
       String originalFileExtension = null;
       String storedFileName = null;
       
       List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
       Map<String, Object> listMap = null; 
        
       String boardIdx = map.get("IDX").toString();
       String userName = map.get("USERNICK").toString();
       
       log.debug(filePath);
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
                
               file = new File(userFilePath +file.separator+ storedFileName);
               multipartFile.transferTo(file);
                
               listMap = new HashMap<String,Object>();
               listMap.put("BOARD_IDX", boardIdx);
               listMap.put("ORIGINAL_FILE_NAME", originalFileName);
               listMap.put("STORED_FILE_NAME", storedFileName);
               listMap.put("FILE_SIZE", multipartFile.getSize());
               list.add(listMap);
           }
       }
       return list;
   }
}
