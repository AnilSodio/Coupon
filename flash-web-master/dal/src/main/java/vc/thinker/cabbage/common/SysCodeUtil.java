package vc.thinker.cabbage.common;

public class SysCodeUtil {
	/**
	 * 自动补8位（前补0）
	 * 
	 * @param sysCode
	 * @return
	 */
	public static String repair(String sysCode) {

		if (null != sysCode && !"".equals(sysCode)) {

			StringBuilder sb = new StringBuilder();
			int len = 7 - sysCode.length();
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					sb.append("0");

				}
				sb.append(sysCode);
				return sb.toString();
			} else {
				return sysCode;
			}
		} else {
			return null;
		}
	}

	
}
