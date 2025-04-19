package ma.enset.hopitalTP3.security.service;

import jakarta.transaction.Transactional;
import ma.enset.hopitalTP3.security.entities.AppRole;
import ma.enset.hopitalTP3.security.entities.AppUser;
import ma.enset.hopitalTP3.security.repo.AppRoleRepository;
import ma.enset.hopitalTP3.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AppRoleRepository appRoleRepository;
    private AppUserRepository userRepository;
    private AppRoleRepository roleRepository;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, AppRoleRepository appRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public AppUser addNewUser(String username, String password, String confirmedPassword) {
        // Implementation here
        AppUser existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }
        if (!password.equals(confirmedPassword)) {
            throw new RuntimeException("Password mismatch");
        }
        AppUser newUser = AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        AppUser savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        // Implementation here
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if (appRole==null) throw new RuntimeException("Role not found");{
            AppRole newRole = AppRole.builder()
                    .role(role)
                    .build();
            AppRole savedRole = appRoleRepository.save(newRole);
            return savedRole;

        }
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        // Implementation here
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        AppRole role = appRoleRepository.findById(roleName).orElse(null);
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        user.getRoles().add(role);

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        // Implementation here
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        AppRole role = appRoleRepository.findById(roleName).orElse(null);
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        user.getRoles().remove(role);

    }
    @Override
    public AppUser loadUserByUsername(String username) {
        // Implementation here
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
}
