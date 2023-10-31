package org.vogt.telegram.bot.logger;

import org.telegram.telegrambots.meta.api.objects.Message;

public class CommandLogger implements Log {

    @Override
    public void info(Message msg) {
        String firstName = msg.getFrom().getFirstName();
        String text = msg.getText();

        System.out.println(String.format("%s says %s", firstName, text));
    }

}
