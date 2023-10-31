package org.vogt.telegram.bot.util;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.vogt.telegram.bot.config.BotConfig;

public class Authorizer {

    private BotConfig config;

    public Authorizer(BotConfig config) {
        this.config = config;
    }

    public boolean isValid(Message msg) {
        long user = msg.getFrom().getId();
        List<Long> allowedUsers = config.getAllowedUsers();

        return allowedUsers.contains(user);
    }

}
