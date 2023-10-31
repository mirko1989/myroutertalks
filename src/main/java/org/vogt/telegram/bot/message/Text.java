package org.vogt.telegram.bot.message;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Text implements Sendable {

    private TelegramLongPollingBot bot;
    private Message msg;
    private String what;

    public Text(TelegramLongPollingBot bot, Message msg, String what) {
        this.bot = bot;
        this.msg = msg;
        this.what = what;
    }

    @Override
    public void send() {
        SendMessage sm = SendMessage.builder()
                .chatId(msg.getChatId().toString())
                .text(what).build();
        try {
            bot.execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
