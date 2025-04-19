package ma.enset.hopitalTP3.security.repo;

import ma.enset.hopitalTP3.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository  extends JpaRepository<AppRole, String> {

}
