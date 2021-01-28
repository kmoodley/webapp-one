package co.za.entelect.relationship.domain.dto;

import co.za.entelect.relationship.domain.Roles;

import java.util.HashSet;
import java.util.Set;

public class UserProfileDTO
{
    private String emailAddress;
    private Set<Roles> roles = new HashSet<>();

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public Set<Roles> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Roles> roles)
    {
        this.roles = roles;
    }
}
