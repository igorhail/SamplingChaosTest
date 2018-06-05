package utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Utils {

  public static String getNodeValueFromXml(final String xmlUrl, final String nodeName) {
    if (xmlUrl.isEmpty() || nodeName.isEmpty()) {
      System.out.println("\nInput parameters 'xmlUrl' or 'nodeName' can't be empty, please enter valid parameters\n");
      return null;
    }

    Document doc = null;
    try {
      InputStream stream = new URL(xmlUrl).openStream();
      doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
      stream.close();
    } catch (IOException | ParserConfigurationException | SAXException e) {
      e.printStackTrace();
    }

    if (doc == null) {
      System.out.println("\nCan't get document from specified URL\n");
      return null;
    }

    NodeList nodes = doc.getElementsByTagName(nodeName);

    if (nodes.getLength() < 1) {
      System.out.println("\nThere is no '" + nodeName + "' node\n");
      return null;
    }

    return nodes.item(0).getTextContent();
  }

  public static void printCharOccurrences(final String str) {
    int count[] = new int[256];

    int len = str.length();

    for (int i = 0; i < len; i++) {
      count[str.charAt(i)]++;
    }

    char ch[] = new char[len];
    for (int i = 0; i < len; i++) {
      ch[i] = str.charAt(i);

      int find = 0;

      for (int j = 0; j <= i; j++) {
        if (str.charAt(i) == ch[j])
          find++;
      }

      if (find == 1)
        System.out.println(str.charAt(i) + "," + count[str.charAt(i)]);
    }
  }

}
