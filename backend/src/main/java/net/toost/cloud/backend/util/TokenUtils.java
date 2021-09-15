package net.toost.cloud.backend.util;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Set;

public class TokenUtils {

	/**
	 * Generate a jwt token
	 *
	 * @param username
	 * @param groups
	 * @param duration
	 * @param issuer
	 * @param privateKeyLocation
	 * @return
	 * @throws Exception
	 */
	public static String generateToken(String username, Set<String> groups, long duration, String issuer, String privateKeyLocation) throws Exception {
		PrivateKey privateKey = readPrivateKey(privateKeyLocation);
		
		JwtClaimsBuilder claimsBuilder = Jwt.claims();
		long currentTimeInSecs = currentTimeInSecs();

		claimsBuilder.issuer(issuer);
		claimsBuilder.subject(username);
		claimsBuilder.issuedAt(currentTimeInSecs);
		claimsBuilder.expiresAt(currentTimeInSecs + duration);
		claimsBuilder.groups(groups);

		return claimsBuilder.jws().keyId(privateKeyLocation).sign(privateKey);
	}

	/**
	 * Read raw private key
	 *
	 * @param pemResName = location of the private key
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
		try (InputStream contentIS = new FileInputStream(pemResName)) {
			byte[] tmp = new byte[4096];
			int length = contentIS.read(tmp);
			return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
		}
	}

	/**
	 * Generate private key by decoded key
	 *
	 * @param pemEncoded = content of file
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
		byte[] encodedBytes = toEncodedBytes(pemEncoded);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(keySpec);
	}

	/**
	 * Decode from base64 to encoded bytes
	 *
	 * @param pemEncoded = full key string
	 * @return
	 */
   public static byte[] toEncodedBytes(final String pemEncoded) {
		final String normalizedPem = removeBeginEnd(pemEncoded);
		return Base64.getDecoder().decode(normalizedPem);
	}

	/**
	 * Remove header and footer from file
	 *
	 * @param pem = full key string
	 * @return
	 */
	public static String removeBeginEnd(String pem) {
		pem = pem.replaceAll("-----BEGIN (.*)-----", "");
		pem = pem.replaceAll("-----END (.*)----", "");
		pem = pem.replaceAll("\r\n", "");
		pem = pem.replaceAll("\n", "");
		return pem.trim();
	}

	public static int currentTimeInSecs() {
		long currentTimeMS = System.currentTimeMillis();
		return (int) (currentTimeMS / 1000);
	}

}