package com.example.reminder.service;

import com.example.reminder.entity.Reminder;
import com.example.reminder.component.ReminderJob;
import lombok.AllArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class ReminderSchedulerService {
    private final Scheduler scheduler;

    public void scheduleReminder(Reminder reminder, String chatId) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(ReminderJob.class)
                    .withIdentity("reminderJob" + reminder.getId(), "reminders")
                    .usingJobData("title", reminder.getTitle())
                    .usingJobData("description", reminder.getDescription())
                    .usingJobData("chatId", chatId)
                    .build();

            ZonedDateTime zonedDateTime = reminder.getRemind().atZone(ZonedDateTime.now().getZone());
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("reminderTrigger" + reminder.getId(), "reminders")
                    .startAt(Date.from(zonedDateTime.toInstant()))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withMisfireHandlingInstructionFireNow())
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("Ошибка при планировании напоминания", e);
        }
    }
}
