package Pidrila;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class WasjaWolk {
  long _id;
  String _user_name;
  long _socialCredit;

  public WasjaWolk(){
    File file = new File("wasja_wolk.xml");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = null;
    try {
      db = dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
    Document doc = null;
    try {
      doc = db.parse(file);
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    doc.getDocumentElement().normalize();
    NodeList nodeList = doc.getElementsByTagName("wasja_wolk");

    for(int i = 0; i< nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE)
      {
        Element eElement = (Element) node;
        _id = Long.parseLong(eElement.getElementsByTagName("id").item(0).getTextContent());
        _user_name = eElement.getElementsByTagName("username").item(0).getTextContent();
        _socialCredit = Long.parseLong(eElement.getElementsByTagName("social_credit").item(0).getTextContent());
      }
    }
  }
  public long getId() {
    return _id;
  }

  public String getUserName() {
    return _user_name;
  }

  public long get_socialCredit() {
    return _socialCredit;
  }

  public void set_socialCredit(){

  }
}
