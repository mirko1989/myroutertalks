import org.vogt.telegram.bot.config.FritzBoxConfig;

public class FritzBoxConfigFake extends FritzBoxConfig {

    @Override
    public String getPass() {
        return "password";
    }

    @Override
    public String getUser() {
        return "user";
    }

    @Override
    public String getUrl() {
        return "192.168.178.1";
    }

    @Override
    public String getWifiName() {
        return "MyWifi";
    }

}