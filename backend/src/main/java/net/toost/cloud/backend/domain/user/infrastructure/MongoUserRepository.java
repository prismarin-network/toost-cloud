package net.toost.cloud.backend.domain.user.infrastructure;

import io.quarkus.runtime.StartupEvent;
import net.toost.cloud.backend.domain.user.core.model.User;
import net.toost.cloud.backend.domain.user.core.ports.incoming.PasswordEncoder;
import net.toost.cloud.backend.domain.user.core.ports.outgoing.UserRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@ApplicationScoped
public class MongoUserRepository implements UserRepository {

    @Inject
    PasswordEncoder encoder;

    @ConfigProperty(name = "toost.default.user.name") String defaultUserName;
    @ConfigProperty(name = "toost.default.user.password") String defaultUserPassword;


    @Override
    public User create(String name, String password, String... groups) throws Exception{
        User user = new User();
        user.setId(name.toLowerCase());
        user.setDisplayName(name);
        user.setPassword(encoder.encodePassword(password));
        user.setMail("");
        Set<String> storedGroups = new HashSet<>();
        for(String group : groups) {
            storedGroups.add(group);
        }
        user.setGroups(storedGroups);
        return user;
    }

    void onStart(@Observes StartupEvent event) throws Exception {
        persist(create(defaultUserName, defaultUserPassword, "Admin", "User"));
    }
}
