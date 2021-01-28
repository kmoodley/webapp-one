package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.Roles;
import co.za.entelect.relationship.domain.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserProfileDetails implements UserDetails
{
    private static final Logger LOG = LogManager.getLogger();


    private static final long serialVersionUID = 7860991398615837782L;
    private UserProfile userProfile;


    public UserProfileDetails(UserProfile user)
    {
        this.userProfile = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {

        Collection<GrantedAuthority> authorities = new HashSet<>();
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles("ROLE_ADMIN"));
        roles.add(new Roles("ROLE_GUEST"));

        for (Roles role : roles)
        {
            LOG.info("getAuthorities ==>> role : " + role.toString());
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return authorities;
    }

    @Override
    public String getPassword()
    {
        return userProfile.getPassword();
    }

    @Override
    public String getUsername()
    {
        return userProfile.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
