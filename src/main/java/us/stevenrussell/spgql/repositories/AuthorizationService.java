package us.stevenrussell.spgql.repositories;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorizationService {

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();

        SecurityContext context = SecurityContextHolder.getContext();
        if(context != null) {
            Authentication authentication = context.getAuthentication();
            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
            roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        }

        return roles;
    }
}
