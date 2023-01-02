package org.vogt.telegram.bot.util;

public class FritzBoxWifi implements Switchable {

    private FritzBoxHTTPClient _client;

    public FritzBoxWifi(FritzBoxHTTPClient client) {
        this._client = client;
    }

    public void on() {
        _client.enableWifi();
    }

    public void off() {
        _client.disableWifi();
    }

    @Override
    public boolean isOn() {
        return _client.isWifiOn();
    }

}
