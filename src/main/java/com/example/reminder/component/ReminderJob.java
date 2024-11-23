package com.example.reminder.component;

import com.example.reminder.service.TelegramBot;
import lombok.AllArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReminderJob implements Job {
    private final TelegramBot telegramBot;

    @Override
    public void execute(JobExecutionContext context) {
        String title = context.getJobDetail().getJobDataMap().getString("title");
        String description = context.getJobDetail().getJobDataMap().getString("description");
        String chatId = context.getJobDetail().getJobDataMap().getString("chatId");

        telegramBot.sendMessage(chatId, title + "\n" + description);
    }
}
