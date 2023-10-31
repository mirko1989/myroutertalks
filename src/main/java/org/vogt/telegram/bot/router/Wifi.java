package org.vogt.telegram.bot.router;

public class Wifi implements Switchable {

    private RouterClient client;

    public Wifi(RouterClient client) {
        this.client = client;
    }

    public void on() {
        client.wifiOn();
    }

    public void off() {
        client.wifiOff();
    }

    @Override
    public boolean isOn() {
        return client.isWifiOn();
    }

}
