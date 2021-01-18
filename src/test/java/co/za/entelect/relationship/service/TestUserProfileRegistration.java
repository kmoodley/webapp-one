package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.UserProfile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUserProfileRegistration
{
    private RegistrationService registrationService;

    @Before
    public void setup()
    {
        registrationService = new RegistrationService(null);
    }

    @Test
    public void testRegisterUser()
    {
        UserProfile user1 = new UserProfile("moodleyk@gmail.com", "pass123", true);
        UserProfile user2 = new UserProfile("moodleyk@gmail.com", "password123", true);
        UserProfile user3 = new UserProfile("moodleyB@gmail.com", "password123", false);

        Assert.assertTrue("Valid User registration failed!", registrationService.register(user1));
        Assert.assertFalse("Invalid User Registered!", registrationService.register(user2));
        Assert.assertFalse("Invalid User Registered!", registrationService.register(user3));

        Assert.assertEquals("Only one valid user should be registered",
                registrationService.retrieveUsers().size(),
                1);
    }
}
