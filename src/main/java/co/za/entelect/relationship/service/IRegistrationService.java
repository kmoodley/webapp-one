package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.RegisteredUser;

import java.util.Set;

public interface IRegistrationService
{
    boolean register(RegisteredUser newRegisteredUser);

    boolean validateUser(RegisteredUser newRegisteredUser);

    Set<RegisteredUser> retrieveUsers();
}
