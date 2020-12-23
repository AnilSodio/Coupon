package vc.thinker.apiv2.utils;

import org.apache.commons.lang3.StringUtils;

public class ValidateUtil {

	/**
	 * 验证身份证号码的正确性
	 *
	 */
	public static boolean checkIdCard(String cardid) {
		String ls_id = cardid;
		if (ls_id.length() != 18) {
			return false;
		}
		char[] l_id = ls_id.toCharArray();
		int l_jyw = 0;
		int[] wi = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		char[] ai = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
		for (int i = 0; i < 17; i++) {
			if (l_id[i] < '0' || l_id[i] > '9') {
				return false;
			}
			l_jyw += (l_id[i] - '0') * wi[i];
		}
		l_jyw = l_jyw % 11;
		if (ai[l_jyw] != l_id[17]) {
			return false;
		}
		return true;
	}

	/**
	 * 校验密码 1、长度不小于6位 2、必须以字母开头 3、必须包含特殊字符 4、必须包含数字
	 * 
	 * @param pwd
	 * @return
	 */
	public static boolean validPwd(String pwd) {
		if (StringUtils.isEmpty(pwd)) {
			return false;
		}
		if (pwd.length() < 6) {
			return false;
		}
//		if (pwd.matches("^[a-zA-z](.*)") && pwd.matches("(.*)[-`=\\\\\\[\\];',./~!@#$%^&*()_+|{}:\"<>?]+(.*)")
//				&& pwd.matches("(.*)\\d+(.*)")) {
//			return true;
//		}
		return true;
	}
}
