package com.example.reminder.service;

import com.example.reminder.dto.ReminderDtoRq;
import com.example.reminder.dto.ReminderDtoRs;
import com.example.reminder.dto.ReminderUpdateDtoRq;
import com.example.reminder.entity.Reminder;
import com.example.reminder.entity.User;
import com.example.reminder.repository.ReminderRepository;
import com.example.reminder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final UserRepository userRepository;
    private final ReminderSchedulerService reminderSchedulerService;
    private final TelegramBot telegramBot;

    public ReminderDtoRq createReminder(ReminderDtoRq reminderDtoRq) {
        User byId = userRepository.getReferenceById(reminderDtoRq.getUserId());
        Reminder reminder = Reminder.builder()
                .title(reminderDtoRq.getTitle())
                .description(reminderDtoRq.getDescription())
                .remind(reminderDtoRq.getRemind())
                .user(byId)
                .build();
        reminderRepository.save(reminder);
        reminderSchedulerService.scheduleReminder(reminder, telegramBot.getChatId().toString());
        return reminderDtoRq;
    }

    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    public boolean updateReminder(Long id, ReminderUpdateDtoRq ReminderUpdateDtoRq) {
        Optional<Reminder> byId = reminderRepository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }
        byId.map(reminder -> {
            reminder.setTitle(ReminderUpdateDtoRq.getTitle());
            reminder.setDescription(ReminderUpdateDtoRq.getDescription());
            reminder.setRemind(ReminderUpdateDtoRq.getRemind());
            return reminderRepository.save(reminder);
        });
        return true;
    }

    public List<ReminderDtoRs> sortReminders(Long user_id, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        List<Reminder> allReminder = reminderRepository.findByUserId(user_id, sort);
        List<ReminderDtoRs> reminderDtoRsList = allReminder.stream().map(this::toDto
        ).toList();
        return reminderDtoRsList;
    }

    public Map<String, Object> getRemindersByUser(Long userId, Pageable pageable) {
        Page<Reminder> allByUserId = reminderRepository.findAllByUserId(userId, pageable);
        Page<ReminderDtoRs> allByUserIdDto = allByUserId.map(this::toDto);

        Map<String, Object> response = new HashMap<>();
        response.put("total", allByUserIdDto.getTotalElements());
        response.put("current", allByUserIdDto.getContent());
        response.put("page", allByUserIdDto.getNumber());
        response.put("totalPages", allByUserIdDto.getTotalPages());
        return response;
    }

    private ReminderDtoRs toDto(Reminder reminder) {
        return ReminderDtoRs.builder()
                .id(reminder.getId())
                .title(reminder.getTitle())
                .description(reminder.getDescription())
                .remind(reminder.getRemind())
                .build();
    }
}
