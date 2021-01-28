package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.Roles;
import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.repository.RolesRepository;
import co.za.entelect.relationship.repository.UserProfileRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWebRegistration
{
    @Autowired
    private WebRegistrationService registrationService;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private RolesRepository rolesRepository;

    @Test
    public void getUserTest()
    {
        UserProfile user1 = new UserProfile("pass123", "Kemendran", "Moodley", "moodleyA@gmail.com", LocalDate.of(1982, 6, 22), true);
        UserProfile user2 = new UserProfile("pass123", "Neressa", "Moodley", "moodleyB@gmail.com", LocalDate.of(1982, 6, 22), true);
        UserProfile user3 = new UserProfile("pass123", "Saroj", "Moodley", "moodleyA@gmail.com", LocalDate.of(1982, 6, 22), true);
        List<UserProfile> userList = Stream.of(user1, user2, user3).collect(Collectors.toList());
        when(userProfileRepository.findAll()).thenReturn(userList);
        Assert.assertEquals(3, registrationService.getUserList().size());
    }

    @Test
    public void findUserByFirstname()
    {
        UserProfile user = new UserProfile("pass123", "Kemendran", "Moodley", "moodleyA@gmail.com", LocalDate.of(1982, 6, 22), true);
        when(userProfileRepository.findByFirstname(user.getFirstname())).thenReturn(user);
        Assert.assertEquals(user, registrationService.getUserProfileByFirstname(user.getFirstname()));
    }

    @Test
    public void findUserByEmailAddress()
    {
        UserProfile user = new UserProfile("pass123", "Kemendran", "Moodley", "moodleyA@gmail.com", LocalDate.of(1982, 6, 22), true);
        when(userProfileRepository.findByEmailAddress(user.getEmailAddress())).thenReturn(user);
        Assert.assertEquals(user, registrationService.getUserProfileByEmailAddress(user.getEmailAddress()));
    }

    @Test
    public void findAllUsers()
    {
        UserProfile user1 = new UserProfile("pass123", "Kemendran", "Moodley", "moodleyA@gmail.com", LocalDate.of(1982, 6, 22), true);
        UserProfile user2 = new UserProfile("pass123", "Neressa", "Moodley", "moodleyB@gmail.com", LocalDate.of(1982, 6, 22), true);
        UserProfile user3 = new UserProfile("pass123", "Saroj", "Moodley", "moodleyA@gmail.com", LocalDate.of(1982, 6, 22), true);
        List<UserProfile> userList = Stream.of(user1, user2, user3).collect(Collectors.toList());
        when(userProfileRepository.findAllActiveUsers()).thenReturn(userList);
        Assert.assertEquals(3, registrationService.getActiveUsers().size());
    }

    @Test
    public void findAllRoles()
    {
        Roles role1 = new Roles("ROLE_ADMIN");
        Roles role2 = new Roles("ROLE_GUEST");
        Set<Roles> rolesList = Stream.of(role1,role2).collect(Collectors.toSet());
        when(rolesRepository.findAll()).thenReturn(rolesList);
        Assert.assertEquals(2, registrationService.getRolesList().size());
    }

    @Test
    public void findUserRole()
    {
        Roles role = new Roles("ROLE_ADMIN");
        Set<Roles> rolesList = Stream.of(role).collect(Collectors.toSet());
        when(rolesRepository.findAll()).thenReturn(rolesList);
        Assert.assertEquals(1, registrationService.getRolesList().size());

        UserProfile user = new UserProfile("pass123", "Kemendran", "Moodley", "moodleyA@gmail.com", LocalDate.of(1982, 6, 22), true);
        user.setRoles(rolesList);
        when(userProfileRepository.findAllUserRoles(user.getEmailAddress())).thenReturn(rolesList);
        Assert.assertEquals(1, registrationService.getAllUserRoles(user.getEmailAddress()).size());
    }

}
