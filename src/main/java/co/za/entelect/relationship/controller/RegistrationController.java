package co.za.entelect.relationship.controller;

import co.za.entelect.relationship.domain.RegisteredUser;
import co.za.entelect.relationship.domain.SecurityData;
import co.za.entelect.relationship.repository.RegisteredUserRepository;
import co.za.entelect.relationship.repository.SecurityDataUserRepository;
import co.za.entelect.relationship.service.WebRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class RegistrationController
{
    private WebRegistrationService registrationService;
    private RegisteredUserRepository userRepository;
    private SecurityDataUserRepository securityDataUserRepository;

    @Autowired
    public RegistrationController(WebRegistrationService registrationService,
                                  RegisteredUserRepository userRepository,
                                  SecurityDataUserRepository securityDataUserRepository)
    {
        this.userRepository = userRepository;
        this.registrationService = registrationService;
        this.securityDataUserRepository = securityDataUserRepository;
    }

    @GetMapping("/register")
    public String register(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "termsConditions") boolean termsConditions)
    {
        RegisteredUser user = new RegisteredUser(email, password, termsConditions);
        if (registrationService.register(user))
        {
            //return email + " ::>> registered successfully";
            System.out.println(email + " > registered successfully");
        } else
        {
            //return email + " ::>> registration failed";
            System.out.println(email + " > registration failed");
        }
        return "register";
    }

    @GetMapping("/registration_result")
    public String registerResult(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "termsConditions") boolean termsConditions)
    {
        RegisteredUser user = new RegisteredUser(email, password, termsConditions);
        if (registrationService.register(user))
        {
            //return email + " ::>> registered successfully";
            System.out.println(email + " > registered successfully");
        } else
        {
            //return email + " ::>> registration failed";
            System.out.println(email + " > registration failed");
        }
        return "register";
    }

    @GetMapping("/test_register")
    public String testRegister()
    {
        System.out.println("testRegister ==>> saving data...");

        String password = "p@!rnWE34%";
        String firstname = "Kemendran";
        String lastname = "Moodley";
        String emailAddress = "moodleyk@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(1982, 6, 22);
        boolean termsAndConditions = true;

        RegisteredUser user = new RegisteredUser
                (password,
                        firstname,
                        lastname,
                        emailAddress,
                        dateOfBirth,
                        termsAndConditions);

        userRepository.save(user);
        return "test_register";
    }

}
