package org.vogt.telegram.bot.logger;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Log {

    public void info(Message msg);

}
