package org.vogt.telegram.bot.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.ini4j.*;

public class BotConfig {

    private Wini _ini;

    public String getName() {
        _loadIni();
        String name = _ini.get("TelegramBot", "name", String.class);

        return name;
    }

    private void _loadIni() {
        if (this._ini == null) {
            try {
                this._ini = new Wini(new File("config.ini"));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    public String getToken() {
        _loadIni();
        String token = _ini.get("TelegramBot", "token", String.class);

        return token;
    }

    public List<Long> getAllowedUsers() {
        _loadIni();
        String userCSV = _ini.get("TelegramBot", "authorizedUsers", String.class);
        Long[] ids = userCSV.length() > 0 ? string2long(userCSV.split(",")) : new Long[] {};

        return new ArrayList<Long>(Arrays.asList(ids));
    }

    private Long[] string2long(String[] numbers) {
        Long[] result = new Long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            result[i] = Long.parseLong(numbers[i]);
        }

        return result;
    }

}
