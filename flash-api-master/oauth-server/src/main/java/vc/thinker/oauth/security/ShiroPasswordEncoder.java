package vc.thinker.oauth.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.sinco.common.security.Digests;
import com.sinco.common.security.PasswordUtil;
import com.sinco.common.utils.Encodes;



public class ShiroPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		final StringBuilder password = new StringBuilder(rawPassword.length());
		password.append(rawPassword);
		return PasswordUtil.entryptPassword(password.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		final StringBuilder password = new StringBuilder(rawPassword.length());
		password.append(rawPassword);
		return validatePassword(password.toString(), encodedPassword);
	}
	public static boolean validatePassword(String plainPassword, String password)
	/*    */   {
	/* 32 */     byte[] salt = Encodes.decodeHex(password.substring(0, 16));
	/* 33 */     byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, 1024);
	/* 34 */     return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
	/*    */   }

	public static void main(String[] args) {
		String s = "8e77d2b0e6a6f561e259f1ca0ebb6aca415a6e7d9f2aeb64d0c60aed";
		System.out.println(ShiroPasswordEncoder.validatePassword("123456", s));
	}


}
