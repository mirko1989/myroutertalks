package org.vogt.telegram.bot.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.vogt.telegram.bot.message.Sendable;
import org.vogt.telegram.bot.message.Text;

public class TimeCommand implements TelegramCommand {

    private Message _msg;

    public TimeCommand(Message msg) {
        this._msg = msg;
    }

    @Override
    public void execute(TelegramLongPollingBot bot) {
        String formattedDate = _makeDateString();
        Sendable text = new Text(bot, _msg, formattedDate);
        text.send();
    }

    private String _makeDateString() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss a zzz");
        
        return formatter.format(now);
    }

}
