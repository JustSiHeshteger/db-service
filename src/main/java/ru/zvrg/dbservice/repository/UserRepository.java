package ru.zvrg.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zvrg.dbservice.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Поиск по chatId
     */
    Optional<User> findByChatId(Long chatId);

    /**
     * Удаление по chatId
     */
    void deleteByChatId(Long chatId);
}
