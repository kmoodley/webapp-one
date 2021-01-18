package co.za.entelect.relationship.controller;

import co.za.entelect.relationship.service.WebRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteAdminController
{
    private WebRegistrationService registrationService;

    @Autowired
    public SiteAdminController(WebRegistrationService registrationService)
    {
        this.registrationService = registrationService;
    }

    @GetMapping("/viewusers")
    public String viewuser(Model model)
    {
        model.addAttribute("users",registrationService.getUserList());
        return "user_list";
    }

}
