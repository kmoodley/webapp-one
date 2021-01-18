package co.za.entelect.relationship.repository;

import co.za.entelect.relationship.domain.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile,Long>
{

}
