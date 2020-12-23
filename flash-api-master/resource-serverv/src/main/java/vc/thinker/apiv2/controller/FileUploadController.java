package vc.thinker.apiv2.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.weedfs.client.RequestResult;
import net.weedfs.client.WeedFSClient;
import vc.thinker.apiv2.http.response.ListResponse;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.web.UserAuthentication;

@RestController
@Controller
@RequestMapping(value = "/api/fs", method = {RequestMethod.POST} , produces = "application/json")
public class FileUploadController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public enum FileType {
		IMAGE, AUDIO, VIDEO
	}

	@Autowired
	private WeedFSClient imageFsClient;

	@RequestMapping(value="/upload_batch", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<ListResponse<RequestResult>> uploadFileBatchV2(@UserAuthentication User user,@RequestPart String fileType, @RequestPart("file") List<MultipartFile> files) {
		ListResponse<RequestResult> resp = new ListResponse<>();
		
		List<RequestResult> results=new ArrayList<>();
		if (files != null && files.size() > 0) {
			for (MultipartFile file : files) {
				try {
					byte[] bytes = file.getBytes();
					if (fileType.contains(FileType.IMAGE.name())) {
						RequestResult result = imageFsClient.upload(bytes,
								user.getUsername() + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(file.getOriginalFilename()),
								"image/" + FilenameUtils.getExtension(file.getOriginalFilename()));
						results.add(result);
					}
				} catch (IOException e) {
					logger.error("", e);
					continue;
				}
			}
		}
		resp.setItems(results);
		if(results.size() < 1){
			resp.setSuccess(false);
			resp.setError("500");
			resp.setErrorDescription("上传失败");
		}
		return ResponseEntity.ok(resp);
	}
	
	@RequestMapping(value="/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
    public ResponseEntity<SingleResponse<RequestResult>> uploadFileV2(@UserAuthentication User user,@RequestPart String fileType,@RequestPart MultipartFile file) {
		SingleResponse<RequestResult> resp = new SingleResponse<>();

		RequestResult result=null;
    	try{
    		long size = file.getSize();
    		logger.info("size:[{}]",size);
	    	byte[]  bytes = file.getBytes();
	    	result = imageFsClient.upload(bytes, user.getUsername() + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(file.getOriginalFilename()), "image/" + FilenameUtils.getExtension(file.getOriginalFilename()));
    	} catch (IOException e) {
    		logger.error("IOException: [{}]",e);
		}catch (Exception e) {
			logger.error("Exception:[{}]",e);
		}
    	resp.setItem(result);
    	if(result == null){
    		resp.setSuccess(false);
    		resp.setError("500");
    		resp.setErrorDescription("上传失败");
    	}
        return ResponseEntity.ok(resp);
    }

	@RequestMapping(value="/", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@UserAuthentication User user,@RequestPart String fileType, @RequestPart MultipartFile file) {
		RequestResult result = null;

    	try{
	    	byte[]  bytes = file.getBytes();
	    	if(fileType.contains(FileType.IMAGE.name())){
				 result = imageFsClient.upload(bytes, user.getUsername() + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(file.getOriginalFilename()), "image/" + FilenameUtils.getExtension(file.getOriginalFilename()));
	    	}
//	    	if(fileType.contains(FileType.AUDIO.name())){
//	    		 result = audioFsClient.upload(bytes, user.getUsername() + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(file.getOriginalFilename()), "audio/" + FilenameUtils.getExtension(file.getOriginalFilename()));
//	    	}
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	logger.info("====================="+result+"========"+fileType);
    	String ok = result==null ? new JSONObject().put("code", "error").toString():JSON.toJSONString(result);
        return ResponseEntity.ok(ok);
    }

	@RequestMapping(value="/image",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
	@ResponseBody
    public SingleResponse<RequestResult> uploadWebFile(HttpServletRequest request, HttpServletResponse response) {

		SingleResponse<RequestResult> resp=new SingleResponse<>();
		RequestResult result = null;

    	try{
    		InputStream is = request.getInputStream();
    		 result = imageFsClient.upload(is, System.currentTimeMillis() + ".jpg", "image/jpeg");
    		 resp.setItem(result);
    	} catch (IOException e) {
			logger.error("上传图片错误",e);
		}
        return resp;
    }

}
