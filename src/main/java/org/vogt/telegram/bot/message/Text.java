package org.vogt.telegram.bot.message;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Text implements Sendable {

    private TelegramLongPollingBot _bot;
    private Message _msg;
    private String _what;

    public Text(TelegramLongPollingBot bot, Message msg, String what) {
        this._bot = bot;
        this._msg = msg;
        this._what = what;
    }

    @Override
    public void send() {
        SendMessage sm = SendMessage.builder()
                .chatId(_msg.getChatId().toString())
                .text(_what).build();
        try {
            _bot.execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
