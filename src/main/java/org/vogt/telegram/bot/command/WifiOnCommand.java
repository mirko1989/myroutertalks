package org.vogt.telegram.bot.command;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.vogt.telegram.bot.router.FritzBoxHTTPClient;
import org.vogt.telegram.bot.router.Wifi;

public class WifiOnCommand implements TelegramCommand {

    private Message _msg;

    public WifiOnCommand(Message msg) {
        this._msg = msg;
    }

    @Override
    public void execute(TelegramLongPollingBot bot) {
        Wifi wifi = new Wifi(new FritzBoxHTTPClient());
        wifi.on();
    }
    
}
