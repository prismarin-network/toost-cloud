package net.toost.cloud.backend.domain.user.core.ports.incoming;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
public interface PasswordEncoder {

    /**
     * Encode given sequence
     *
     * @param sequence {@link CharSequence}
     * @return = encoded string
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    String encodePassword(CharSequence sequence) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
