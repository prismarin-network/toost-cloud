package net.toost.cloud.backend.domain.user.core.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.Set;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Data
@MongoEntity(collection = "users")
public class User {

    @BsonId
    private String id;

    private String password;
    private String displayName;
    private String mail;
    private Set<String> groups;
}
