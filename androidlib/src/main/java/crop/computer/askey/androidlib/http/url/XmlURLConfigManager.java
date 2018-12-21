package crop.computer.askey.androidlib.http.url;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by weikai on 2018/12/19.
 */

public class XmlURLConfigManager
        implements URLConfigManager {

    private String[] filenames;
    private final List<URLInfo> urlList = new ArrayList<>();

    private Context context;

    public XmlURLConfigManager(Context context, String... filenames) {
        this.filenames = filenames;
        this.context = context;
    }

    @Override
    public URLInfo findURL(String findKey) {
        if(urlList == null || urlList.isEmpty()) {
            for(String filename: filenames) {
                fetchUrlDataFromXml(filename);
            }
        }

        for(URLInfo data: urlList) {
            if(findKey.equals(data.getKey())) {
                return data;
            }
        }
        return null;
    }

    public void fetchUrlDataFromXml(String filename) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = context.getAssets().open(filename);
            Document doc = builder.parse(is); // 以樹狀格式儲存在記憶體中

            Element root = doc.getDocumentElement();
            System.out.println("loading service's url: "+root.getAttribute("name") + " ...");

            Node server = root.getElementsByTagName("server").item(0);
            String scheme = server.getAttributes().getNamedItem("scheme").getNodeValue();
            String host = server.getAttributes().getNamedItem("host").getNodeValue();

            NodeList nList = root.getElementsByTagName("api");
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
    }
}
