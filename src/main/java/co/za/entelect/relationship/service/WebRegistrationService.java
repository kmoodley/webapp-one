package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.exception.UserAlreadyExistException;
import co.za.entelect.relationship.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WebRegistrationService
{
    private Set<UserProfile> userProfiles = new HashSet<>();
    private UserProfileRepository userRepository;

    public WebRegistrationService()
    {
    }

    @Autowired
    public WebRegistrationService(UserProfileRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public boolean register(UserProfile newUserProfile) throws UserAlreadyExistException
    {
        String emailAddress = newUserProfile.getSecurityData().getEmailAddress();
        if (emailExist(emailAddress))
        {
            throw new UserAlreadyExistException("A user with " + emailAddress + " already exists.");
        }
        userRepository.save(newUserProfile);
        return false;
    }

    public boolean validateUser(UserProfile userProfile)
    {
        return userProfile.isTermsAndConditions();
    }

    public Set<UserProfile> retrieveUsers()
    {
        return new HashSet<>(userProfiles);
    }

    public List<UserProfile> getUserList()
    {
        List<UserProfile> userList = new ArrayList<>();
        userRepository.findAll().forEach(s -> userList.add(s));
        return userList;
    }

    public void deleteUser(Long userId)
    {
        userRepository.deleteById(userId);
    }

    private boolean emailExist(String emailAddress)
    {
        UserProfile profile = userRepository.findBySecurityData_EmailAddress(emailAddress);
        System.out.println("emailExist ==>> " + profile);
        return profile != null;
    }

    public UserProfile getUserProfileByFirstname(String firstname)
    {
        return userRepository.findByFirstname(firstname);
    }

    public UserProfile getUserProfileByEmailAddress(String emailAddress)
    {
        return userRepository.findBySecurityData_EmailAddress(emailAddress);
    }
}