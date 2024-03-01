package com.image.extract.vitraya.vitrayaimageextractor.authentication.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import lombok.experimental.UtilityClass;

/**
 * Utility to generate unique key
 * 
 * @author Suraj G
 *
 */
@UtilityClass
public class KeyGeneratorUtil {
	
	/**
	 * Generate unique key
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String generateUniqueKey() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		
		// generate a random number
		@SuppressWarnings("removal")
		String randomNumber = new Integer(secureRandom.nextInt()).toString();

		// Provides applications the functionality of a message digest algorithm, such as MD5 or SHA
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

		// Performs a final update on the digest using the specified array of bytes, then completes the digest computation
		byte[] randomNumberBytes = messageDigest.digest(randomNumber.getBytes());

		return hexEncode(randomNumberBytes);
		 
		
	}

	private String hexEncode(byte[] input) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < input.length; ++idx) {
			byte b = input[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}

}
