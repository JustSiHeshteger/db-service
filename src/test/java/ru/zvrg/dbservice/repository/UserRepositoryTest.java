package ru.zvrg.dbservice.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.zvrg.dbservice.entity.User;
import ru.zvrg.dbservice.entity.UserInfo;

import java.util.List;

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
        assertEquals(name, findUser.get().getUserInfo().getName());
        assertEquals(secondName, findUser.get().getUserInfo().getSecondName());
        assertEquals(thirdName, findUser.get().getUserInfo().getThirdName());
        assertEquals(nickName, findUser.get().getUserInfo().getNickName());

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

    @Test
    public void deleteAllUsersTest() {
        final String nickName = "testNickName";
        final String name = "testName";

        final User user1 = new User();
        user1.setChatId(1L);

        final User user2 = new User();
        user2.setChatId(2L);

        final UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setNickName(nickName);

        user2.setUserInfo(userInfo);
        userInfo.setUser(user2);

        userRepository.saveAll(List.of(user1, user2));
        var users = userRepository.findAll();

        assertEquals(2, users.size());
        assertNotNull(users.get(1).getUserInfo());
        assertEquals(nickName, users.get(1).getUserInfo().getNickName());
        assertEquals(name, users.get(1).getUserInfo().getName());

        userRepository.deleteAll();
        users = userRepository.findAll();
        assertTrue(users.isEmpty());
    }
}
