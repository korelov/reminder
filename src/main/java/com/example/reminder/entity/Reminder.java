package com.example.reminder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reminders_seq")
    @SequenceGenerator(name = "reminders_seq", sequenceName = "reminders_seq", allocationSize = 1)
    private Long id;
    private String title;
    @Column(length = 4096)
    private String description;
    private LocalDateTime remind;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
