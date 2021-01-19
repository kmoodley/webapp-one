package co.za.entelect.relationship.controller;

import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.domain.SecurityData;
import co.za.entelect.relationship.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController
{
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService)
    {
        this.registrationService = registrationService;
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

        String message = "Success " + user.getFirstname() + " " + user.getLastname() + " has been registered!";
        user.setSecurityData(securityData);
        registrationService.register(user);

        model.addAttribute("userRegisterResult", message);
        return "registration_result";
    }

    @GetMapping("/viewUsers")
    public String viewUsers(Model model)
    {
        model.addAttribute("users", registrationService.getUserList());
        return "view_users";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") String id, Model model)
    {
        registrationService.deleteUser(Long.parseLong(id));
        model.addAttribute("users", registrationService.getUserList());
        return "view_users";
    }
}
