package org.vogt.telegram.bot.command;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.vogt.telegram.bot.message.Sendable;
import org.vogt.telegram.bot.message.Text;

public class InvalidCommand implements TelegramCommand {

    private Message _msg;

    public InvalidCommand(Message msg) {
        this._msg = msg;
    }

    @Override
    public void execute(TelegramLongPollingBot bot) {
        Sendable text = new Text(bot, _msg, "I don't understand.");
        text.send();
    }

}
