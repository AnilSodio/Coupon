package vc.thinker.cabbage.common;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.springframework.util.Base64Utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 * @author james
 *
 */
public class QRCodeUtil {

	private static final int BLACK = 0xFF000000;//用于设置图案的颜色  
    private static final int WHITE = 0xFFFFFFFF; //用于背景色  
    
	/**
	 * 生成
	 * @param param
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static OutputStream generate(String param,int width, int height){
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(param,
					BarcodeFormat.QR_CODE, width, height, null);

			MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
			return stream;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成,输出base64
	 * @param param
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static String generateToBase64(String param,int width, int height){
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(param,
					BarcodeFormat.QR_CODE, width, height, null);
			 int margin = 0;  //自定义白边边框宽度
			 bitMatrix = updateBit(bitMatrix,margin);
			MatrixToImageWriter.writeToStream(bitMatrix, "jpg", stream);
			return Base64Utils.encodeToString(stream.toByteArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 设置白边的边距
	 * @param matrix
	 * @param margin
	 * @return
	 */
	 private static BitMatrix updateBit(BitMatrix matrix, int margin){
	        int tempM = margin*2;
	       int[] rec = matrix.getEnclosingRectangle();   //获取二维码图案的属性
	            int resWidth = rec[2] + tempM;
	            int resHeight = rec[3] + tempM;
	            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
	            resMatrix.clear();
	        for(int i= margin; i < resWidth- margin; i++){   //循环，将二维码图案绘制到新的bitMatrix中
	            for(int j=margin; j < resHeight-margin; j++){
	                if(matrix.get(i-margin + rec[0], j-margin + rec[1])){
	                    resMatrix.set(i,j);
	                }
	            }
	        }
	         return resMatrix;
	    }
	 
	 
	 /**
	  * 生成二维码，输出base64
	  * @param param 扫码二维码的结果
	  * @param url logo 的url
	  * @param width 宽度
	  * @param height 高度 
	  * @return
	  */
	 public static String generateLogoToBase64 (String param,String url,int width, int height) {
		 
		 Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		 
		 hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
	     hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  // 内容所使用字符集编码    
        
         ByteArrayOutputStream stream = new ByteArrayOutputStream();
         BitMatrix bitMatrix;
         try {
        	 bitMatrix = new MultiFormatWriter().encode(param,
 					BarcodeFormat.QR_CODE, width, height, hints);
        	 
        	 int margin = 0;  //自定义白边边框宽度
			 bitMatrix = updateBit(bitMatrix,margin);
			 
			 BufferedImage image = toBufferedImage(bitMatrix);
			 
			 LogoToMatrix(url,image);
			 
			 ImageIO.write(image, "jpg", stream);
			 
			 return Base64Utils.encodeToString(stream.toByteArray());
			 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	 }
	 
	 public static BufferedImage LogoToMatrix(String url,BufferedImage matrixImage){
		 
		 int resultHeight = 50;
		 int resultWidth = 50;
		 
		 try{
			 
			 Graphics2D g2 = matrixImage.createGraphics(); 
			 
			 int matrixWidth = matrixImage.getWidth();  
			 int matrixHeigh = matrixImage.getHeight();  
			 
			 BufferedImage logo = ImageIO.read(new URL(url));
			 
			 g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制
			 
			 int logoWidth = logo.getWidth();
			 int logoHeight = logo.getHeight();
			 if(logoWidth > resultWidth){
				 logoWidth = resultWidth;
			 }
			 if(logoHeight > resultHeight){
				 logoHeight = resultHeight;
			 }
			 
			 int x = (matrixWidth  - logoWidth) / 2;
			 int y = (matrixHeigh  - logoHeight) / 2;
			 
			 g2.drawImage(logo, x, y, logoWidth, logoHeight, null);
			 
			 Shape shape = new RoundRectangle2D.Float(x, y, logoWidth, logoHeight, 6, 6);  
			 g2.setStroke(new BasicStroke(3f)); 
			 g2.draw(shape);  
			 
	         g2.dispose();  
	         
	         matrixImage.flush() ;  
	         return matrixImage ;  
	         
		 }catch (Exception e) {
			 throw new RuntimeException(e);
		}
	 }
	 
	 public static BufferedImage toBufferedImage(BitMatrix matrix) {  
	        int width = matrix.getWidth();  
	        int height = matrix.getHeight();  
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	        for (int x = 0; x < width; x++) {  
	            for (int y = 0; y < height; y++) {  
	                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));  
	            }  
	        }  
	        return image;  
	    } 
}
