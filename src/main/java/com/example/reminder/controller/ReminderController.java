package com.example.reminder.controller;

import com.example.reminder.dto.ReminderDtoRs;
import com.example.reminder.dto.ReminderDtoRq;
import com.example.reminder.dto.ReminderUpdateDtoRq;
import com.example.reminder.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reminder")
public class ReminderController {
    private final ReminderService reminderService;

    @PostMapping("/create")
    public ReminderDtoRq createReminder(@RequestBody ReminderDtoRq reminderDtoRq) {
        return reminderService.createReminder(reminderDtoRq);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReminder(@PathVariable Long id,
                                            @RequestBody ReminderUpdateDtoRq ReminderUpdateDtoRq) {
        return reminderService.updateReminder(id, ReminderUpdateDtoRq) ? ResponseEntity.status(OK).build()
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @GetMapping("/sort")
    public List<ReminderDtoRs> sortReminders(@RequestParam Long user_id,
                                             @RequestParam(defaultValue = "title") String sortBy) {
        return reminderService.sortReminders(user_id, sortBy);
    }

    @GetMapping("/list")
    public Map<String, Object> listReminders(@RequestParam Long userId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reminderService.getRemindersByUser(userId, pageable);
    }
}
