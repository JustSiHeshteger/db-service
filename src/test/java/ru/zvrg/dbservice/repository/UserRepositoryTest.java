package ru.zvrg.dbservice.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.zvrg.dbservice.entity.User;
import ru.zvrg.dbservice.entity.UserInfo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllUsersTest() {
        final var users = userRepository.findAll();
        assertTrue(users.isEmpty());
    }

    @Test
    public void createUserWithoutUserInfoTest() {
        final var chatId = 1L;

        final User user = new User();
        user.setChatId(chatId);
        userRepository.save(user);

        var findUser = userRepository.findByChatId(chatId);
        assertNotNull(findUser);
        assertTrue(findUser.isPresent());
        assertNull(findUser.get().getUserInfo());

        userRepository.deleteByChatId(chatId);
        findUser = userRepository.findByChatId(chatId);
        assertFalse(findUser.isPresent());
    }

    @Test
    public void createUserWithUserInfoTest() {
        final var chatId = 1L;
        final String name = "Vasya";
        final String secondName = "Pupkin";
        final String thirdName = "Nullovich";
        final String nickName = "VPupkin";

        final UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setSecondName(secondName);
        userInfo.setThirdName(thirdName);
        userInfo.setNickName(nickName);

        final User user = new User();
        user.setChatId(chatId);
        user.setUserInfo(userInfo);

        userInfo.setUser(user);

        userRepository.save(user);

        var findUser = userRepository.findByChatId(chatId);
        assertNotNull(findUser);
        assertTrue(findUser.isPresent());
        assertNotNull(findUser.get().getUserInfo());
        assertEquals(findUser.get().getUserInfo().getName(), name);
        assertEquals(findUser.get().getUserInfo().getSecondName(), secondName);
        assertEquals(findUser.get().getUserInfo().getThirdName(), thirdName);
        assertEquals(findUser.get().getUserInfo().getNickName(), nickName);

        userRepository.deleteByChatId(chatId);
        findUser = userRepository.findByChatId(chatId);
        assertFalse(findUser.isPresent());
    }

    @Test
    public void createUserWithNullValuesUserInfoTest() {
        final var chatId = 1L;

        final User user = new User();
        user.setChatId(chatId);
        user.setUserInfo(new UserInfo());

        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void findNotExistedUserByChatIdTest() {
        final var findUser = userRepository.findByChatId(1L);
        assertFalse(findUser.isPresent());
    }

    @Test
    public void deleteNotExistedUserByChatIdTest() {
        userRepository.deleteByChatId(1L);
        final var findUser = userRepository.findById(1L);
        assertFalse(findUser.isPresent());
    }
}
