package com.sathwikhbhat.reponyx.security;

import com.sathwikhbhat.reponyx.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class AppUserPrincipal implements OAuth2User {

    @Getter
    private final User user;
    private final Map<String, Object> attributes;

    @Override
    public @NonNull Map<String, Object> getAttributes() {
        return attributes;
    }

    public UUID getId() {
        return user.getId();
    }

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public @NonNull String getName() {
        return user.getId().toString();
    }
}
