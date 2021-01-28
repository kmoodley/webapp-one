package co.za.entelect.relationship.repository;

import co.za.entelect.relationship.domain.Roles;
import co.za.entelect.relationship.domain.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import org.springframework.transaction.annotation.Transactional;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long>
{
    String USER_ROLES_QUERY = "select u.email_address, r.role from roles r " +
            "INNER JOIN user_roles ur on r.id=ur.role_id " +
            "INNER JOIN users u on u.id=ur.user_id where email_address = :email_address";

    UserProfile findByFirstname(String firstname);

    UserProfile findByEmailAddress(String emailAddress);

    @Query("SELECT u FROM UserProfile u WHERE u.termsAndConditions = 1")
    Collection<UserProfile> findAllActiveUsers();

    @Query(value = USER_ROLES_QUERY, nativeQuery = true)
    Collection<Roles> findAllUserRoles(@Param("email_address") String emailAddress);
}
