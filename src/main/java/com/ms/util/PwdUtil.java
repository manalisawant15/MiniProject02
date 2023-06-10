/**
 * 
 */
package com.ms.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Sawant
 *
 */
public class PwdUtil {

	public static String generateRandomPwd() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 6, characters );
	
		return pwd;
	}
}
