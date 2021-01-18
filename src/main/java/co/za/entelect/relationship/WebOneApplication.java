package co.za.entelect.relationship;

import co.za.entelect.relationship.domain.RegisteredUser;
import co.za.entelect.relationship.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@SpringBootApplication
public class WebOneApplication
{
	private static final Logger log = Logger.getLogger(WebOneApplication.class.getName());

	@Autowired
	RegisteredUserRepository registeredUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebOneApplication.class, args);
	}

	@PostConstruct
	void seeRegisteredUsers()
	{
		log.info("seeRegisteredUsers ==>> method called...");

		for(RegisteredUser user : registeredUserRepository.findAll())
		{
			log.info(user.toString());
		}
	}

}
