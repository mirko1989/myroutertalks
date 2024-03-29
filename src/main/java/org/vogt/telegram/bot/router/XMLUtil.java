package org.vogt.telegram.bot.router;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtil {

    public static String parseChallenge(String xml) {
        return getNodeText(xml, "Challenge");
    }

    private static String getNodeText(String xml, String nodeName) {
        String text = "";

        try {
            Document doc = loadXMLFromString(xml);
            NodeList list = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {
                Element elem = (Element) list.item(i);

                if (elem.getNodeName().equals(nodeName)) {
                    text = elem.getTextContent();
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return text;
    }

    private static Document loadXMLFromString(String xml)
            throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);

        return doc;
    }

    public static String parseSid(String xml) {
        return getNodeText(xml, "SID");
    }

}
