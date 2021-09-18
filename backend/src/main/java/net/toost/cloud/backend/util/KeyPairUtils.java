package net.toost.cloud.backend.util;

import lombok.Cleanup;
import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
public class KeyPairUtils {


    /**
     * Generate rsa keys on the given location
     *
     * @param publicKeyLocation
     * @param privateKeyLocation
     * @throws Exception
     */
    public static void generateKeys(String publicKeyLocation, String privateKeyLocation, int keySize) throws Exception {
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
        pairGenerator.initialize(keySize);
        KeyPair keyPair = pairGenerator.generateKeyPair();
        writeIntoFile(publicKeyLocation, "PUBLIC", keyPair.getPublic().getEncoded());
        writeIntoFile(privateKeyLocation, "PRIVATE", keyPair.getPrivate().getEncoded());

    }

    /**
     * Write given key content into file
     * Adding BEGIN header and END footer
     *
     * @param location
     * @param key
     * @param content
     * @throws IOException
     */
    private static void writeIntoFile(String location, String key, byte[] content) throws IOException {
        File file = new File(location);
        if(file.exists()) {
           return;
        }
        file.getParentFile().mkdirs();

        @Cleanup Writer out = new FileWriter(location);
        out.write("-----BEGIN RSA "+key+" KEY-----\n");
        out.write(Base64.encodeBase64String(content));
        out.write("\n-----END RSA "+key+" KEY-----\n");
        out.close();
    }
}
