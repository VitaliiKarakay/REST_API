package com.tech1.tech1test.security.jwt;

import com.tech1.tech1test.domain.User;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getName());
    }
}
