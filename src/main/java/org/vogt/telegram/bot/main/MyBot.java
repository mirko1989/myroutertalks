package org.vogt.telegram.bot.main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.vogt.telegram.bot.command.TelegramCommand;
import org.vogt.telegram.bot.command.TelegramCommandFactory;
import org.vogt.telegram.bot.config.BotConfig;

public class MyBot extends TelegramLongPollingBot {

	private BotConfig config;

	public MyBot(BotConfig config) {
		this.config = config;
	}

	@Override
	public String getBotUsername() {
		return config.getName();
	}

	@Override
	public String getBotToken() {
		return config.getToken();
	}

	@Override
	public void onUpdateReceived(Update update) {
		Message msg = update.getMessage();
		logMessage(msg);

		if (msg.isCommand()) {
			TelegramCommandFactory factory = new TelegramCommandFactory(config);
			TelegramCommand cmd = factory.createFromMsg(msg);
			cmd.execute(this);
		}
	}

	private void logMessage(Message msg) {
		String firstName = msg.getFrom().getFirstName();
		String text = msg.getText();

		System.out.println(String.format("%s says %s", firstName, text));
	}

}
