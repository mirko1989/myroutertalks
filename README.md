# MyRouterTalks
Control your AVM FritzBox router using Telegram chat messages.
(Tested with FRITZ!Box 6660 Cable running on FRITZ!OS: 07.29)

# How to setup
You have to provide a ```config.ini``` containing credentials for your Telegram bot and FritzBox account.
```
[TelegramBot]
name=MyBot
token=12345abc
authorizedUsers=123,789,456

[FritzBox]
url=http://192.168.178.1
user=admin
pass=12345
```

Compile and package into runnable jar using maven cli.
```
mvn clean compile assembly:single
```

Run it.
```
java -jar bot.jar
```