package com.example.reminder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoRs {
    private Long id;
    private String oauthId;
    private String name;
    private String email;
    private List<ReminderDtoRs> reminderDtoRs;
}
