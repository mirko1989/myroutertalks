package org.vogt.telegram.bot.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.vogt.telegram.bot.config.FritzBoxConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class FritzBoxHTTPClient {

    private String _base;
    private String _login = "/login_sid.lua";
    private String _command = "/data.lua";
    private String _wifiName;
    private String _user;
    private String _pw;

    public void enableWifi() {
        String payload = _makeWifiSettingsPayload(true);
        _post(_command, payload);
    }

    private String _makeWifiSettingsPayload(boolean on) {
        String sid = _getSid();
        int active = on ? 1 : 0;

        return "sid=" + sid +
                "&ssid=" + _wifiName + "&apActive=" + active + // 2.4 GHz
                "&ssidScnd=" + _wifiName + "&apActiveScnd=" + active + // 5 GHz
                "&apply=&page=wSet";
    }

    public void disableWifi() {
        String payload = _makeWifiSettingsPayload(false);
        _post(_command, payload);
    }

    private String _getSid() {
        if (!_isInitialized()) {
            _init();
        }

        String challenge = _getChallenge();
        String token = _getTokenFor(challenge, _pw);
        String payload = _makeAuthPayload(challenge, token);
        String response = _post(_login, payload);
        String sid = _parseSidFromXML(response);

        return sid;
    }

    private boolean _isInitialized() {
        return _base != null && _user != null && _pw != null && _wifiName != null;
    }

    private void _init() {
        FritzBoxConfig conf = new FritzBoxConfig();
        this._base = conf.getUrl();
        this._login = _base + _login;
        this._command = _base + _command;
        this._wifiName = conf.getWifiName();
        this._user = conf.getUser();
        this._pw = conf.getPass();
    }

    private String _post(String endpoint, String payload) {
        String responseBody = "";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(endpoint))
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString(payload))
                    .build();
            HttpClient http = HttpClient.newBuilder().build();
            HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
            responseBody = response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return responseBody;
    }

    private String _parseSidFromXML(String xml) {
        String sid = "";

        try {
            Document doc = _loadXMLFromString(xml);
            NodeList list = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {
                Element elem = (Element) list.item(i);

                if (elem.getNodeName().equals("SID")) {
                    sid = elem.getTextContent();
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return sid;
    }

    private String _getChallenge() {
        String challenge = "";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(_login))
                    .GET()
                    .build();
            HttpClient http = HttpClient.newBuilder().build();
            HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
            challenge = _parseChallengeFromXML(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {

        }

        return challenge;
    }

    private String _parseChallengeFromXML(String xml) {
        String challenge = "";

        try {
            Document doc = _loadXMLFromString(xml);
            NodeList list = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {
                Element elem = (Element) list.item(i);

                if (elem.getNodeName().equals("Challenge")) {
                    challenge = elem.getTextContent();
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return challenge;
    }

    private Document _loadXMLFromString(String xml) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);

        return doc;
    }

    private String _getTokenFor(String challenge, String pw) {
        String in = challenge + "-" + pw;
        String token = "";

        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(in.getBytes("UTF-16LE"));
            byte[] digest = md5.digest();
            token = DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

        }

        return token;
    }

    private String _makeAuthPayload(String challenge, String token) {
        return "response=" + challenge + "-" + token + "&username=" + _user;
    }

    public boolean isWifiOn() {
        String sid = _getSid();
        String payload = "sid=" + sid + "&page=wSet";
        String response = _post(_command, payload);
        String channelTwoPointFourGigahertz = "\"apActive\":\"1\"";
        String channelFiveGigahertz = "\"apActiveScnd\":\"1\"";
        boolean isOn = response.contains(channelTwoPointFourGigahertz) && response.contains(channelFiveGigahertz);

        return isOn;
    }

}
