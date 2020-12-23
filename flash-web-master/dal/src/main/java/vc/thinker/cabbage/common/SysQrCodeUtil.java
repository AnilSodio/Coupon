package vc.thinker.cabbage.common;

public class SysQrCodeUtil {
	/**
	 * 得到系统二维码
	 * @param qrCode
	 * @return
	 * @throws SysQrCodeException 
	 */
	public static String getSysCode(String qrCode){
		
		//如果是http开头
		if(qrCode.startsWith("http")){
			//对老系统二维码兼容
			if(qrCode.startsWith("http://server.nomo.sg")){
				String code=qrCode.substring(qrCode.lastIndexOf("=")+1);
				return SysCodeUtil.repair(code);
			}else{
				return qrCode.substring(qrCode.lastIndexOf("=")+1);
			}
		}else{
			return qrCode;
		}
	}
	
	/**
	 * 生成系统格式二维码
	 * @param type
	 * @param code
	 * @return
	 */
	public static String generateQrCode(String url, String sysCode){
		return new StringBuilder(url).append(sysCode).toString();
	}
	public static void main(String[] args) {
		System.out.println(getSysCode("http://server.nomo.sg/spb/scancode?mod=wxpay&paymod=index&devicetype=battery&q=1&sn=31"));
	}
}
