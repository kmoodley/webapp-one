package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.Roles;
import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.exception.UserAlreadyExistException;
import co.za.entelect.relationship.repository.RolesRepository;
import co.za.entelect.relationship.repository.UserProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class WebRegistrationService
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserProfileRepository userRepository;
    private RolesRepository rolesRepository;

    public WebRegistrationService()
    {
    }

    @Autowired
    public WebRegistrationService(UserProfileRepository userRepository, RolesRepository rolesRepository)
    {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    public boolean register(UserProfile newUserProfile) throws UserAlreadyExistException
    {
        LOG.info("register ==>> user : " + newUserProfile);

        String emailAddress = newUserProfile.getEmailAddress();

        if (emailExist(emailAddress))
        {
            throw new UserAlreadyExistException("A user with " + emailAddress + " already exists.");
        }

        saveUserProfile(newUserProfile, true);
        return false;
    }

    private void saveUserProfile(UserProfile newUserProfile, boolean encode)
    {
        if (encode)
        {
            newUserProfile.setPassword(passwordEncoder.encode(newUserProfile.getPassword()));
        }

        userRepository.save(newUserProfile);
    }

    public List<UserProfile> getUserList()
    {
        List<UserProfile> userList = new ArrayList<>();
        userRepository.findAll().forEach(s -> userList.add(s));
        return userList;
    }

    public List<Roles> getRolesList()
    {
        List<Roles> rolesList = new ArrayList<>();
        rolesRepository.findAll().forEach(s -> rolesList.add(s));
        return rolesList;
    }

    public void deleteUser(Long userId)
    {
        userRepository.deleteById(userId);
    }

    private boolean emailExist(String emailAddress)
    {
        UserProfile profile = userRepository.findByEmailAddress(emailAddress);
        return profile != null;
    }

    public UserProfile getUserProfileByFirstname(String firstname)
    {
        return userRepository.findByFirstname(firstname);
    }

    public UserProfile getUserProfileByEmailAddress(String emailAddress)
    {
        return userRepository.findByEmailAddress(emailAddress);
    }

    public Collection<UserProfile> getActiveUsers()
    {
        return userRepository.findAllActiveUsers();
    }

    public Collection<Roles> getAllUserRoles(String emailAddress)
    {
        return userRepository.findAllUserRoles(emailAddress);
    }
}
