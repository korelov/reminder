package com.example.reminder.repository;

import com.example.reminder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.reminders WHERE u.id = :id")
    Optional<User> findUserWithRemindersById(@Param("id") Long id);

    Optional<User> findByOauthId(String oauthId);
}
