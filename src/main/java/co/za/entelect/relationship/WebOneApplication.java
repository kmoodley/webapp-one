package co.za.entelect.relationship;

import co.za.entelect.relationship.repository.RolesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WebOneApplication implements CommandLineRunner
{
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private RolesRepository rolesRepository;

    public static void main(String[] args)
    {
        SpringApplication.run(WebOneApplication.class, args);
    }

    @PostConstruct
    public void postRun()
    {
        LOG.info("postRun() ...");
    }

    @Override
    public void run(String... args)
    {
        LOG.info("run() ...");
        /*  Add Roles   */
//        Roles r1 = new Roles("ROLE_ADMIN");
//        Roles r2 = new Roles("ROLE_GUEST");
//        rolesRepository.save(r1);
//        rolesRepository.save(r2);
//
//        LOG.info("Roles saved : " + StreamSupport
//                .stream(rolesRepository.findAll().spliterator(), false)
//                .collect(Collectors.toList()));


    }


}
