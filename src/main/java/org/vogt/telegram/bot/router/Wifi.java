package org.vogt.telegram.bot.router;

public class Wifi implements Switchable {

    private RouterClient _client;

    public Wifi(RouterClient client) {
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
