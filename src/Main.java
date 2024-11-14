
import implementations.XMLParser;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Akshar
 */
public class Main {
    public static void main(String[] args) {
        XMLParser parser = new XMLParser();

        // Example XML string with errors
        String xml = "<root><tag1></tag1><tag2></tag2></root>";

        // Parse the XML
        parser.parse(xml);

        // Print errors
        parser.printErrors();
    }
}
