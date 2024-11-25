package TestTask.demo;

import TestTask.demo.model.UserModel;
import TestTask.demo.repository.UserRepository;
import TestTask.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserModel userModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userModel = new UserModel();
        userModel.setUsername("testUser");
        userModel.setPassword("password123");
    }

    @Test
    void testCreateNewAccount_WhenUsernameExists() {

        when(userRepository.existsByUsername(userModel.getUsername())).thenReturn(true);


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.createNewAccount(userModel);
        });

        assertEquals("Username already exists", exception.getMessage());
        verify(userRepository, times(1)).existsByUsername(userModel.getUsername());
        verify(userRepository, never()).save(any(UserModel.class));
    }

    @Test
    void testCreateNewAccount_SuccessfulAccountCreation() {

        String password = "password123";
        when(userRepository.existsByUsername(userModel.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");  // Mocked encoded password
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);


        UserModel createdUser = userService.createNewAccount(userModel);


        assertNotNull(createdUser);  // Check that the user is not null
        assertEquals("testUser", createdUser.getUsername());  // Check that the username is correct
        assertNotNull(createdUser.getPassword());  // Ensure that password is not null
        assertFalse(createdUser.getPassword().isEmpty());  // Ensure that the password is not empty
        verify(userRepository, times(1)).existsByUsername(userModel.getUsername());
        verify(passwordEncoder, times(1)).encode(userModel.getPassword());  // Verify that encoding was called
        verify(userRepository, times(1)).save(any(UserModel.class));  // Verify that saving the user was called
    }


    @Test
    void testFindByUsername_WhenUserNotFound() {

        when(userRepository.findByUsername(userModel.getUsername())).thenReturn(java.util.Optional.empty());


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.findByUsername(userModel.getUsername());
        });

        assertEquals("Username not found", exception.getMessage());
        verify(userRepository, times(1)).findByUsername(userModel.getUsername());
    }

    @Test
    void testFindByUsername_SuccessfulFind() {

        when(userRepository.findByUsername(userModel.getUsername())).thenReturn(java.util.Optional.of(userModel));


        UserModel foundUser = userService.findByUsername(userModel.getUsername());

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername(userModel.getUsername());
    }
}