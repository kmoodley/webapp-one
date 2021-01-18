package co.za.entelect.relationship.repository;

import co.za.entelect.relationship.domain.RegisteredUser;
import org.springframework.data.repository.CrudRepository;

public interface RegisteredUserRepository extends CrudRepository<RegisteredUser,Long>
{

}
