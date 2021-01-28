package co.za.entelect.relationship;

import co.za.entelect.relationship.domain.UserProfile;
import co.za.entelect.relationship.service.WebRegistrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class WebOneApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WebOneApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(WebRegistrationService service) throws Exception {
        return (String[] args) ->
        {
            UserProfile userProfile = new UserProfile("pdntspa","Kemendran","Moodley",
                    "moodleyk@gmail.com", LocalDate.of(1982,06,22),true);
            service.register(userProfile);
        };
    }
}
