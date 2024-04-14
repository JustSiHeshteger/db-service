package ru.zvrg.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zvrg.dbservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
