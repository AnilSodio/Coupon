package vc.thinker.cabbage.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import net.weedfs.client.RequestResult;
import net.weedfs.client.WeedFSClient;

/**
 * 
 * @ClassName: UploadController 
 * @Description: TODO(上传图片) 
 * @author Tang
 * @date 2015年12月4日 下午3:52:10 
 *
 */
@Controller
@RequestMapping("${adminPath}")
public class FileUploadController{
	
	private Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private WeedFSClient fsClient;
	
	/**
	 * 系统编辑器图片上传
	 *
	 * @param request
	 * @param response
	 * @throws ClassNotFoundException
	 */
	

	@RequestMapping(value="/upload",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject upload(HttpServletRequest request, HttpServletResponse response,
			@RequestPart(name="imgFile") MultipartFile imgFile)
			throws ClassNotFoundException {

		//用于保存返回数据
		JSONObject obj = new JSONObject();
		
		try {
			
			if(imgFile!=null){
			
				BufferedImage image = ImageIO.read(imgFile.getInputStream());
				
				//用户上传的文件大小
				int width = image.getWidth();
				int height = image.getHeight();
				
				//文件限制的大小
				String img_width = request.getParameter("width");
				String img_height = request.getParameter("height");
				
				/**
				 * 校验文件的宽度和高度
				 */
				String isEqualComparison  = request.getParameter("isEqualComparison");
				if(!StringUtils.isEmpty(isEqualComparison) && "true".equals(isEqualComparison)){
					//相等比较
					if(height != Integer.parseInt(img_height) || width != Integer.parseInt(img_width)){
						obj.put("error", "请上传"+img_width+"*"+img_height+"像素大小的图片！");
						return obj;
					}
					
				}else {
					if(!StringUtils.isEmpty(img_width)){
						if(width>Integer.parseInt(img_width)){
							obj.put("error", "文件宽度超过最大值"+img_width);
							return obj;
						}
					}
					
					if(!StringUtils.isEmpty(img_height)){
						if(height>Integer.parseInt(img_height)){
							obj.put("error", "文件高度超过最大值"+img_height);
							return obj;
						}
					}
					
				}
				
				/**
				 * 校验大小
				 */
				String img_size = request.getParameter("size");
				if(!StringUtils.isEmpty(img_size)){
					if(imgFile.getSize() > (Long.parseLong(img_size) * 1024l)){
						obj.put("error", "文件超过最大值"+img_size+"KB");
						return obj;
					}
				}
				
				RequestResult result = fsClient.upload(imgFile.getInputStream(), imgFile.getName(), URLConnection.guessContentTypeFromName((imgFile.getOriginalFilename())));
				obj.put("error", 0);
				obj.put("url", result.getUrl());
				
				
			}else{
				obj.put("error", "file is null");
			}
		} catch (IOException e) {
			obj.put("error", 1);
			obj.put("message", e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}
}
