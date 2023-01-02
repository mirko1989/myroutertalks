package org.vogt.telegram.bot.command;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.vogt.telegram.bot.message.Sendable;
import org.vogt.telegram.bot.message.Text;
import org.vogt.telegram.bot.util.FritzBoxHTTPClient;
import org.vogt.telegram.bot.util.FritzBoxWifi;

public class WifiStatusCommand implements TelegramCommand {

    private Message _msg;

    public WifiStatusCommand(Message msg) {
        this._msg = msg;
    }

    @Override
    public void execute(TelegramLongPollingBot bot) {
        FritzBoxWifi wifi = new FritzBoxWifi(new FritzBoxHTTPClient());
        String status = "WiFi is " + (wifi.isOn() ? "ON" : "OFF");
        Sendable text = new Text(bot, _msg, status);
        text.send();
    }

}
