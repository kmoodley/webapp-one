package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.RegisteredUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WebRegistrationService implements IRegistrationService
{
    private Set<RegisteredUser> registeredRegisteredUsers = new HashSet<>();

    @Override
    public boolean register(RegisteredUser newRegisteredUser)
    {
        if (validateUser(newRegisteredUser))
        {
            return registeredRegisteredUsers.add(newRegisteredUser);
        }
        return false;
    }

    @Override
    public boolean validateUser(RegisteredUser registeredUser)
    {
        return registeredUser.isTermsAndConditions();
    }

    @Override
    public Set<RegisteredUser> retrieveUsers()
    {
        return new HashSet<>(registeredRegisteredUsers);
    }

    public List<RegisteredUser> getUserList()
    {
        List<RegisteredUser> userList = new ArrayList<>();
        userList.add(new RegisteredUser("moodleyk@gmail.com", "pass123", true));
        userList.add(new RegisteredUser("JaneSmithBSc@yahoo.com", "password123", false));
        userList.add(new RegisteredUser("Timothycruise@hackerank.com", "password123", true));
        return userList;
    }
}
