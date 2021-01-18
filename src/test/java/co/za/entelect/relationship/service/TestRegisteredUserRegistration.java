package co.za.entelect.relationship.service;

import co.za.entelect.relationship.domain.RegisteredUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRegisteredUserRegistration
{
    private IRegistrationService registrationService;

    @Before
    public void setup()
    {
        registrationService = new WebRegistrationService();
    }

    @Test
    public void testRegisterUser()
    {
        RegisteredUser user1 = new RegisteredUser("moodleyk@gmail.com", "pass123", true);
        RegisteredUser user2 = new RegisteredUser("moodleyk@gmail.com", "password123", true);
        RegisteredUser user3 = new RegisteredUser("moodleyB@gmail.com", "password123", false);

        Assert.assertTrue("Valid User registration failed!", registrationService.register(user1));
        Assert.assertFalse("Invalid User Registered!", registrationService.register(user2));
        Assert.assertFalse("Invalid User Registered!", registrationService.register(user3));

        Assert.assertEquals("Only one valid user should be registered",
                registrationService.retrieveUsers().size(),
                1);
    }
}
