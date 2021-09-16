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

    private static final int KEY_SIZE = 2048;

    /**
     * Generate public and private key on the given directory location
     *
     * @param location = directory
     * @throws Exception
     */
    public static void generateKeys(String location) throws Exception {
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
        pairGenerator.initialize(KEY_SIZE);

        File directory = new File(location);
        if(directory.exists()) {
            return;
        }
        directory.mkdirs();

        KeyPair keyPair = pairGenerator.generateKeyPair();
        writeIntoFile(location.concat("publickey.pem"), "PUBLIC", keyPair.getPublic().getEncoded());
        writeIntoFile(location.concat("privatekey.pem"), "PRIVATE", keyPair.getPrivate().getEncoded());

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
        @Cleanup Writer out = new FileWriter(location);
        out.write("-----BEGIN RSA "+key+" KEY-----\n");
        out.write(Base64.encodeBase64String(content));
        out.write("\n-----END RSA "+key+" KEY-----\n");
        out.close();
    }
}
