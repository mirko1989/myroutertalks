package org.vogt.telegram.bot.main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.vogt.telegram.bot.command.TelegramCommand;
import org.vogt.telegram.bot.command.TelegramCommandFactory;
import org.vogt.telegram.bot.config.BotConfig;

public class MyBot extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		BotConfig conf = new BotConfig();
		String name = conf.getName();

		return name;
	}

	@Override
	public String getBotToken() {
		BotConfig conf = new BotConfig();
		String token = conf.getToken();

		return token;
	}

	@Override
	public void onUpdateReceived(Update update) {
		Message msg = update.getMessage();
		_logMessage(msg);

		if (msg.isCommand()) {
			TelegramCommand cmd = TelegramCommandFactory.createFromMsg(msg);
			cmd.execute(this);
		}
	}

	private void _logMessage(Message msg) {
		String firstName = msg.getFrom().getFirstName();
		String text = msg.getText();

		System.out.println(String.format("%s says %s", firstName, text));
	}

}
