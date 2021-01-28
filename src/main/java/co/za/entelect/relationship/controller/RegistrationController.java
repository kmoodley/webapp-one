package co.za.entelect.relationship.controller;

import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.exception.UserAlreadyExistException;
import co.za.entelect.relationship.service.WebRegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController
{
    private static final Logger LOG = LogManager.getLogger();

    private WebRegistrationService webRegistrationService;

    @Autowired
    public RegistrationController(WebRegistrationService webRegistrationService)
    {
        this.webRegistrationService = webRegistrationService;
    }

    @GetMapping("/registrationForm")
    public String registerUserForm(Model model)
    {
        LOG.info("registerUserForm ==>> register user");
        UserProfile user = new UserProfile();
        model.addAttribute("userProfile", user);
        model.addAttribute("defaultEmailAddress", "moodleyk@gmail.com");
        return "registrationForm";
    }

    @PostMapping("/registerUser")
    public String registerUserSubmit(@Valid @ModelAttribute("userProfile") UserProfile user, Model model)
    {
        LOG.info("registerUserForm ==>> register user");
        String successMessage = "Success " + user.getFirstname() + " " + user.getLastname() + " has been registered!";

        try
        {
            webRegistrationService.register(user);
        } catch (UserAlreadyExistException e)
        {
            String errorMessage = "r/HolUp " + user.getEmailAddress() + " has already been registered!";
            model.addAttribute("registrationErrorMessage", errorMessage);
            return "index";
        }

        System.out.println("registerUserSubmit ==>> " + successMessage);
        model.addAttribute("userRegisterResult", successMessage);
        return "registration_result";
    }

    @GetMapping("/admin/viewUsers")
    public String viewUsers(Model model)
    {
        model.addAttribute("users", webRegistrationService.getUserList());
        return "view_users";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") String id, Model model)
    {
        webRegistrationService.deleteUser(Long.parseLong(id));
        model.addAttribute("users", webRegistrationService.getUserList());
        return "view_users";
    }
}
