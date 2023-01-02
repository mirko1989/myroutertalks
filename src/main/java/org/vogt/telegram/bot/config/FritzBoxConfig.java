package org.vogt.telegram.bot.config;

import java.io.File;
import java.io.IOException;

import org.ini4j.*;

public class FritzBoxConfig {

    private Wini _ini;

    public String getUser() {
        _loadIni();
        String name = _ini.get("FritzBox", "user", String.class);

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

    public String getPass() {
        _loadIni();
        String token = _ini.get("FritzBox", "pass", String.class);

        return token;
    }

    public String getUrl() {
        _loadIni();
        String url = _ini.get("FritzBox", "url", String.class);

        return url;
    }

    public String getWifiName() {
        _loadIni();
        String name = _ini.get("FritzBox", "wifiName", String.class);

        return name;
    }

}
