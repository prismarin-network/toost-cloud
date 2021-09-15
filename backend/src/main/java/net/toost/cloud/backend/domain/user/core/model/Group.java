package net.toost.cloud.backend.domain.user.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Getter
@AllArgsConstructor
public enum Group {

    ADMIN("Admin"),
    USER("User");

    private final String name;
}
