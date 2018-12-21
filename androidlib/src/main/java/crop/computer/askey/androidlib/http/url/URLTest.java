package crop.computer.askey.androidlib.http.url;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class URLTest {

	public static void main(String[] args) {
		List<URLInfo> urlList = new ArrayList<>();
		String scheme;
		String host;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("url_test.xml")); // 以樹狀格式儲存在記憶體中
            
            Element root = doc.getDocumentElement();
            System.out.println(root.getNodeName());
            System.out.println(root.getAttribute("name"));

            Node server = root.getElementsByTagName("server").item(0);
            scheme = server.getAttributes().getNamedItem("scheme").getNodeValue();
            host = server.getAttributes().getNamedItem("host").getNodeValue();
            
            NodeList nList = doc.getElementsByTagName("api");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                NamedNodeMap attr = node.getAttributes();
                if (attr != null) {
                    String key = attr.getNamedItem("key").getNodeValue();
                    long expires = Long.parseLong(attr.getNamedItem("expires").getNodeValue());
                    String method = attr.getNamedItem("method").getNodeValue();
                    String path = attr.getNamedItem("path").getNodeValue();
                    urlList.add(new URLInfo(key, expires, method, scheme, host, path));
                }
            }
            
        } catch (ParserConfigurationException e) {
        	System.out.println(e.toString());
        } catch (SAXException e) {
        	System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        System.out.println(urlList);
        
	}

}
