package org.vogt.telegram.bot.router;

import org.vogt.telegram.bot.config.FritzBoxConfig;

public class FritzBoxSid {

    public static String getFor(FritzBoxConfig config) {
        String loginUrl = config.getUrl() + "/login_sid.lua";
        String xml = HTTPClient.get(loginUrl);
        String challenge = XMLUtil.parseChallenge(xml);
        String token = Token.getFor(challenge, config.getPass());
        String solution = "response=" + challenge + "-" + token + "&username=" + config.getUser();
        String response = HTTPClient.post(loginUrl, solution);
        String sid = XMLUtil.parseSid(response);

        return sid;
    }
}
