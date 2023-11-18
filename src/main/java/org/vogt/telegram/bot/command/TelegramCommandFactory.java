package org.vogt.telegram.bot.command;

import org.telegram.telegrambots.meta.api.objects.Message;

public class TelegramCommandFactory {

    private Authorizer authorizer;

    public TelegramCommandFactory(Authorizer authorizer) {
        this.authorizer = authorizer;
    }

    public TelegramCommand createFromMsg(Message msg) {
        TelegramCommand cmd;

        if (!authorizer.isValid(msg)) {
            cmd = new UnauthorizedCommand(msg);
            return cmd;
        }

        switch (msg.getText()) {
            case TelegramCommandType.TIME:
                cmd = new TimeCommand(msg);
                break;
            case TelegramCommandType.WIFI_ON:
                cmd = new WifiOnCommand(msg);
                break;
            case TelegramCommandType.WIFI_OFF:
                cmd = new WifiOffCommand(msg);
                break;
            case TelegramCommandType.WIFI_STATUS:
                cmd = new WifiStatusCommand(msg);
                break;
            default:
                cmd = new InvalidCommand(msg);
        }

        return cmd;
    }

}
