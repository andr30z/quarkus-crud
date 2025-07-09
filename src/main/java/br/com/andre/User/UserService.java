package br.com.andre.User;

import java.util.UUID;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(UserResourceDto userDto) {
        var user = new User(
                userDto.name(),
                userDto.email(),
                userDto.password());

        user.persist();

        LOG.infof("User saved with id: %s", user.getId());

        return user;
    }

    @Transactional
    public User update(UUID userId, UserResourceDto userDto) {

        LOG.infof("Updating user with id: {}", userId);
        var user = this.userRepository.findByIdOptional(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDto.name());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());

        user.persist();
        return user;
    }

    public User findUserById(UUID id) {
        return this.userRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }

    public void delete(UUID id) {
        var user = User.findById(id);
        user.delete();
    }

}
