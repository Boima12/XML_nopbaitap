import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class bai1 {
    public static void main(String[] args) {
        try {
            String cf = new File(".").getCanonicalPath();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("directory");
            rootElement.setAttribute("path", cf);
            doc.appendChild(rootElement);

            listDirectory(new File(cf), rootElement, doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("directory_tree.xml"));
            transformer.transform(source, result);

            System.out.println("ok");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listDirectory(File directory, Element parentElement, Document doc) {
        if (directory.isDirectory()) {
            Element dirElement = doc.createElement(directory.getName().replace(".", ""));
            parentElement.appendChild(dirElement);

            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        listDirectory(file, dirElement, doc);
                    } else {
                        Element fileElement = doc.createElement("file");
                        fileElement.setAttribute("name", file.getName());
                        dirElement.appendChild(fileElement);
                    }
                }
            }
        }
    }
}
