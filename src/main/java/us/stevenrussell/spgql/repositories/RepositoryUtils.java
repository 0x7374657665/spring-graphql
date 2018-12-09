package us.stevenrussell.spgql.repositories;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryUtils {

    public static List<String> getUserAuthorities(SecurityContext secCtxt) {

        List<String> authorities = new ArrayList<>();
        Authentication authentication = secCtxt.getAuthentication();
        if(authentication != null) {
            authorities = authentication
                    .getAuthorities()
                    .stream()
                    .map(auth -> ((GrantedAuthority) auth).getAuthority())
                    .collect(Collectors.toList());
        }
        return authorities;
    }
}
