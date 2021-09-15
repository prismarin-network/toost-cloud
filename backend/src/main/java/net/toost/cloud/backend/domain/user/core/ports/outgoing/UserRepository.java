package net.toost.cloud.backend.domain.user.core.ports.outgoing;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import net.toost.cloud.backend.domain.user.core.model.User;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
public interface UserRepository extends PanacheMongoRepositoryBase<User, String> {

    /**
     * Create a user with the given details,
     * it also stores the user into the database
     *
     * @param name
     * @param password
     * @param groups
     * @return {@link User}
     * @throws Exception
     */
    User create(String name, String password, String... groups) throws Exception;
}
