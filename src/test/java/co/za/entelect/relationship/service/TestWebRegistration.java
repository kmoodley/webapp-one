package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.SecurityData;
import co.za.entelect.relationship.domain.UserProfile;
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
        SecurityData securityData = user.getSecurityData();
        when(userProfileRepository.findBySecurityData_EmailAddress(securityData.getEmailAddress())).thenReturn(user);
        Assert.assertEquals(user, registrationService.getUserProfileByEmailAddress(securityData.getEmailAddress()));
    }

}
