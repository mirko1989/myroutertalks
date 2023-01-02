package org.vogt.telegram.bot.util;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.vogt.telegram.bot.config.BotConfig;

public class Authorizer {

    private Message _msg;

    public Authorizer(Message msg) {
        this._msg = msg;
    }

    public boolean isValid() {
        long user = _msg.getFrom().getId();
        BotConfig config = new BotConfig();
        List<Long> allowedUsers = config.getAllowedUsers();

        return allowedUsers.contains(user);
    }

}
