import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class bai2 {
    public static void main(String[] args) {
        try {
            String cf = new File(".").getCanonicalPath();

            ArrayList<Student> students = new ArrayList<>();
            students.add(new Student("Nguyen Van A", 18, 9));
            students.add(new Student("Nguyen Van B", 19, 9));
            students.add(new Student("Nguyen Van C", 18, 9));

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("students");
            doc.appendChild(rootElement);

            for (Student student : students) {
                Element studentElement = doc.createElement("student");
                rootElement.appendChild(studentElement);

                studentElement.setAttribute("name", student.getName());
                studentElement.setAttribute("age", String.valueOf(student.getAge()));
                studentElement.setAttribute("gpa", String.valueOf(student.getGpa()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("students.xml"));
            transformer.transform(source, result);

            System.out.println("ok2");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Student {
        private String name;
        private int age;
        private double gpa;

        public Student(String name, int age, double gpa) {
            this.name = name;
            this.age = age;
            this.gpa = gpa;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getGpa() {
            return gpa;
        }
    }
}
