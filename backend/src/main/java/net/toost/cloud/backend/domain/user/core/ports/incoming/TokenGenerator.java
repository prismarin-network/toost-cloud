package net.toost.cloud.backend.domain.user.core.ports.incoming;

import net.toost.cloud.backend.domain.user.core.model.User;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
public interface TokenGenerator {

    String generateToken(User user, long duration) throws Exception;
}
