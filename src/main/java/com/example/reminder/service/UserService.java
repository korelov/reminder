package com.example.reminder.service;

import com.example.reminder.dto.ReminderDtoRs;
import com.example.reminder.dto.UserDtoRq;
import com.example.reminder.dto.UserDtoRs;
import com.example.reminder.entity.Reminder;
import com.example.reminder.entity.User;
import com.example.reminder.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDtoRs saveOrUpdateUser(UserDtoRq oAuth2User) {
        String oauthId = oAuth2User.getOauthId();
        String name = oAuth2User.getName();
        String email = oAuth2User.getEmail();
        Optional<User> existingUser = userRepository.findByOauthId(oauthId);

        if (existingUser.isPresent()) {
            return toDto(existingUser.get());
        } else {
            User newUser = User.builder()
                    .oauthId(oauthId)
                    .name(name)
                    .email(email)
                    .build();
            userRepository.save(newUser);
            return toDto(newUser);
        }
    }

    @Transactional
    public UserDtoRs getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return toDto(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDtoRs toDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDtoRs.builder()
                .id(user.getId())
                .oauthId(user.getOauthId())
                .name(user.getName())
                .email(user.getEmail())
                .reminderDtoRs(
                        user.getReminders() != null ?
                                user.getReminders().stream()
                                        .map(this::toReminderDto)
                                        .toList()
                                : Collections.emptyList()
                )
                .build();
    }

    private ReminderDtoRs toReminderDto(Reminder reminder) {
        if (reminder == null) {
            return null;
        }
        return ReminderDtoRs.builder()
                .id(reminder.getId())
                .title(reminder.getTitle())
                .description(reminder.getDescription())
                .remind(reminder.getRemind())
                .build();
    }
}
