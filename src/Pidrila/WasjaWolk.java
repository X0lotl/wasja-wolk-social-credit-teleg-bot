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
  String _username;
  long _socialCredits;

  public WasjaWolk() {
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

    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element eElement = (Element) node;
        _id = Long.parseLong(eElement.getElementsByTagName("id").item(0).getTextContent());
        _username = eElement.getElementsByTagName("username").item(0).getTextContent();
        _socialCredits = Long.parseLong(eElement.getElementsByTagName("social_credits").item(0).getTextContent());
      }
    }
  }

  public long getId() {
    return _id;
  }

  public String getUserName() {
    return _username;
  }

  public long get_socialCredits() {
    return _socialCredits;
  }

  public void set_socialCredits(long newSocialCredits) {
    _socialCredits = newSocialCredits;

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

    Node node = nodeList.item(0);
    if (node.getNodeType() == Node.ELEMENT_NODE) {
      Element eElement = (Element) node;

      eElement.getElementsByTagName("social_credits").item(0).setTextContent(String.valueOf(_socialCredits));
      //eElement.getElementsByTagName("social_credits").item(0).setAttribute("social_credits", String.valueOf(_socialCredits));
      System.out.println(Long.parseLong(eElement.getElementsByTagName("social_credits").item(0).getTextContent()));



      //setValue("social_credits", eElement , _socialCredits.toString());
    }

  }

  public void updateUserInfo(long id, String username) {
    _id = id;
    _username = username;

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

    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element eElement = (Element) node;
        eElement.getElementsByTagName("id").item(0).setTextContent(String.valueOf(_id));
        eElement.getElementsByTagName("username").item(0).setTextContent(String.valueOf(_username));
      }
    }

  }
}
