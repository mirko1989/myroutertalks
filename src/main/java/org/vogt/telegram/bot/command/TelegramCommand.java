package org.vogt.telegram.bot.command;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface TelegramCommand {
    
    public void execute(TelegramLongPollingBot bot);

}
