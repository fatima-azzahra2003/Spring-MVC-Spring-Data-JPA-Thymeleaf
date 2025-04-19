package ma.enset.hopitalTP3.security.service;

import ma.enset.hopitalTP3.security.entities.AppRole;
import ma.enset.hopitalTP3.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String confirmedPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);

}
