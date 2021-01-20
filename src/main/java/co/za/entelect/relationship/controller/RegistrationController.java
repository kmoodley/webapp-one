package co.za.entelect.relationship.controller;

import co.za.entelect.relationship.domain.SecurityData;
import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.exception.UserAlreadyExistException;
import co.za.entelect.relationship.service.WebRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController
{
    private WebRegistrationService webRegistrationService;

    @Autowired
    public RegistrationController(WebRegistrationService webRegistrationService)
    {
        this.webRegistrationService = webRegistrationService;
    }

    @GetMapping("/")
    public String registerUserForm(Model model)
    {
        UserProfile user = new UserProfile();
        SecurityData securityData = new SecurityData();
        model.addAttribute("userProfile", user);
        model.addAttribute("securityData", securityData);
        return "index";
    }

    @PostMapping("/registerUser")
    public String registerUserSubmit(@Valid @ModelAttribute("userProfile") UserProfile user,
                                     @Valid @ModelAttribute("securityData") SecurityData securityData,
                                     Model model)
    {
        String successMessage = "Success " + user.getFirstname() + " " + user.getLastname() + " has been registered!";
        user.setSecurityData(securityData);
        try
        {
            webRegistrationService.register(user);
        } catch (UserAlreadyExistException e)
        {
            String errorMessage = "r/HolUp " + securityData.getEmailAddress() + " has already been registered!";
            model.addAttribute("registrationErrorMessage", errorMessage);
            return "index";
        }

        model.addAttribute("userRegisterResult", successMessage);
        return "registration_result";
    }

    @GetMapping("/viewUsers")
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
