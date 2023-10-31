package org.vogt.telegram.bot.router;

import org.vogt.telegram.bot.config.FritzBoxConfig;

public class FritzBoxHTTPClient implements RouterClient {

    private FritzBoxConfig config;

    public FritzBoxHTTPClient(FritzBoxConfig config) {
        this.config = config;
    }

    @Override
    public void wifiOn() {
        String payload = wifiPayload(true);
        HTTPClient.post(commandUrl(), payload);
    }

    private String commandUrl() {
        return config.getUrl() + "/data.lua";
    }

    private String wifiPayload(boolean on) {
        String sid = FritzBoxSid.getFor(config);
        int active = on ? 1 : 0;

        return "sid=" + sid +
                "&ssid=" + config.getWifiName() + "&apActive=" + active + // 2.4 GHz
                "&ssidScnd=" + config.getWifiName() + "&apActiveScnd=" + active + // 5 GHz
                "&apply=&page=wSet";
    }

    @Override
    public void wifiOff() {
        String payload = wifiPayload(false);
        HTTPClient.post(commandUrl(), payload);
    }

    @Override
    public boolean isWifiOn() {
        String sid = FritzBoxSid.getFor(config);
        String payload = "sid=" + sid + "&page=wSet";
        String response = HTTPClient.post(commandUrl(), payload);
        String channelTwoPointFourGigahertz = "\"apActive\":\"1\"";
        String channelFiveGigahertz = "\"apActiveScnd\":\"1\"";
        boolean isOn = response.contains(channelTwoPointFourGigahertz) && response.contains(channelFiveGigahertz);

        return isOn;
    }

}
