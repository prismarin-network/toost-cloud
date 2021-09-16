package net.toost.cloud.backend.domain.user.core;

import io.quarkus.runtime.StartupEvent;
import net.toost.cloud.backend.domain.user.core.ports.incoming.PasswordEncoder;
import net.toost.cloud.backend.util.KeyPairUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/

@ApplicationScoped
public class UserFacade implements PasswordEncoder {

    @ConfigProperty(name = "toost.password.secret")
    String secret;

    @ConfigProperty(name = "toost.password.iterations")
    int iterations;

    @ConfigProperty(name = "toost.password.keylength")
    int keyLength;

    @ConfigProperty(name = "toost.jwt.generation.keys.directory")
    String keysDirectory;

    @Override
    public String encodePassword(CharSequence sequence) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(new PBEKeySpec(sequence.toString().toCharArray(), secret.getBytes(), iterations, keyLength))
                .getEncoded();
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * Generate default key pairs
     *
     * @param event
     * @throws Exception
     */
    void onStart(@Observes StartupEvent event) throws Exception {
        KeyPairUtils.generateKeys(keysDirectory);
    }
}
