package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RegistrationService
{
    private Set<UserProfile> userProfiles = new HashSet<>();
    private UserProfileRepository userRepository;

    @Autowired
    public RegistrationService(UserProfileRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public boolean register(UserProfile newUserProfile)
    {
        if (validateUser(newUserProfile))
        {
            boolean isRegistered = userProfiles.add(newUserProfile);

            if (isRegistered)
            {
                userRepository.save(newUserProfile);
            }

            return isRegistered;
        }
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
}
