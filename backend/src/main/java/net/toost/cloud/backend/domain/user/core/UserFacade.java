package net.toost.cloud.backend.domain.user.core;

import io.quarkus.runtime.StartupEvent;
import net.toost.cloud.backend.domain.user.core.model.User;
import net.toost.cloud.backend.domain.user.core.ports.incoming.PasswordEncoder;
import net.toost.cloud.backend.domain.user.core.ports.incoming.TokenGenerator;
import net.toost.cloud.backend.util.KeyPairUtils;
import net.toost.cloud.backend.util.TokenUtils;
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
public class UserFacade implements PasswordEncoder, TokenGenerator {

    @ConfigProperty(name = "toost.password.secret") String passwordSecret;
    @ConfigProperty(name = "toost.password.iterations") int passwordIterations;
    @ConfigProperty(name = "toost.password.keylength") int passwordKeyLength;

    @ConfigProperty(name = "toost.jwt.keys.size") int jwtKeysSize;
    @ConfigProperty(name = "mp.jwt.verify.issuer") String jwtIssuer;
    @ConfigProperty(name = "toost.jwt.keys.privatekey.location") String jwtPrivateKeyLocation;
    @ConfigProperty(name = "toost.jwt.keys.publickey.location") String jwtPublicKeyLocation;

    @Override
    public String encodePassword(CharSequence sequence) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(new PBEKeySpec(sequence.toString().toCharArray(), passwordSecret.getBytes(), passwordIterations, passwordKeyLength))
                .getEncoded();
        return Base64.getEncoder().encodeToString(result);
    }

    @Override
    public String generateToken(User user, long duration) throws Exception{
        return TokenUtils.generateToken(user.getId(), user.getGroups(), duration, jwtIssuer, jwtPrivateKeyLocation);
    }

    /**
     * Generate default key pairs
     *
     * @param event
     * @throws Exception
     */
    void onStart(@Observes StartupEvent event) throws Exception {
        KeyPairUtils.generateKeys(jwtPublicKeyLocation, jwtPrivateKeyLocation, jwtKeysSize);
    }
}
