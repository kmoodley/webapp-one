package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.domain.dto.UserProfileDTO;
import co.za.entelect.relationship.repository.UserProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityManager;

public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserProfileRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        LOG.info("loadUserByUsername ==>> username=" + username);
        UserProfile user = userRepository.findByEmailAddress(username); //TODO: Resolve Lazy Initialisation issue here

        if (user == null)
        {
            throw new UsernameNotFoundException(username);
        }

        UserProfileDetails userDetails = new UserProfileDetails(user);
        return userDetails;
    }

}
