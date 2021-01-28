package co.za.entelect.relationship.repository;

import co.za.entelect.relationship.domain.Roles;
import co.za.entelect.relationship.domain.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestUserRepository
{
    @Autowired
    private UserProfileRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private EntityManager entityManager;

    private SessionFactory sessionFactory;

    @Before
    public void injectedComponentsAreNotNull()
    {
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(entityManager);
        sessionFactory = entityManager.unwrap(org.hibernate.Session.class).getSessionFactory();
        Assert.assertNotNull(sessionFactory);
    }

    @Test
    public void whenSaved_thenFindAll()
    {
        UserProfile user1 = new UserProfile("pass123", "Kemendran", "Moodley", "moodleyk@gmail.com", LocalDate.of(1982, 6, 22), true);
        UserProfile user2 = new UserProfile("pass123", "Neressa", "Moodley", "someone@company.com", LocalDate.of(1982, 6, 22), true);
        UserProfile user3 = new UserProfile("pass123", "Saroj", "Moodley", "phil@abc.com", LocalDate.of(1982, 6, 22), true);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        List<UserProfile> actualList = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        actualList.forEach(user -> System.out.println("user : " + user.getFirstname()));
        Assert.assertEquals(3, actualList.size());
    }

    @Test
    public void whenSaved_thenFindsByName()
    {
        Assert.assertNotNull(userRepository.findByFirstname("Kemendran"));
        System.out.println("whenSaved_thenFindsByName ==>> " + userRepository.findByFirstname("Kemendran").getEmailAddress());
    }

    @Test
    public void whenSaved_thenFindsByEmailAddress()
    {
        List<Roles> rolesList = StreamSupport
                .stream(rolesRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Assert.assertEquals(2, rolesList.size());

        Assert.assertNotNull(userRepository.findAll());
        List<UserProfile> actualList = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        actualList.forEach(user -> System.out.println("user : " + user.getFirstname()));

        String searchEmailAddress = "moodleyk@gmail.com";

        UserProfile userFoundByEmail = userRepository.findByEmailAddress(searchEmailAddress);
        Assert.assertNotNull(userFoundByEmail);
        System.out.println("Found user by email = " + userFoundByEmail.getEmailAddress());
        Assert.assertEquals(searchEmailAddress, userFoundByEmail.getEmailAddress());

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserProfile persistentUser = session.find(UserProfile.class, userFoundByEmail.getId());

        Assert.assertEquals(2, persistentUser.getRoles().size());

        session.getTransaction().commit();
        session.close();

    }
}
