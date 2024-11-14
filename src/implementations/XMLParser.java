/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

/**
 *
 * @author Akshar
 */

public class XMLParser {
    private MyStack<String> tagStack;
    private MyQueue<String> errorQueue;

    public XMLParser() {
        tagStack = new MyStack<>();
        errorQueue = new MyQueue<>();
    }

    public void parse(String xml) {
        int pos = 0;
        while (pos < xml.length()) {
            int openTagStart = xml.indexOf("<", pos);
            if (openTagStart == -1) break;
            int closeTagEnd = xml.indexOf(">", openTagStart);
            if (closeTagEnd == -1) {
                errorQueue.enqueue("Error: Missing closing '>' for a tag at position " + openTagStart);
                break;
            }
            String tag = xml.substring(openTagStart + 1, closeTagEnd).trim();
            pos = closeTagEnd + 1;

            // Check if it's a closing tag
            if (tag.startsWith("/")) {
                String tagName = tag.substring(1);
                if (tagStack.isEmpty()) {
                    errorQueue.enqueue("Error: Closing tag </" + tagName + "> without matching opening tag.");
                } else {
                    String openTag = tagStack.pop();
                    if (!openTag.equals(tagName)) {
                        errorQueue.enqueue("Error: Tag mismatch. Expected </" + openTag + ">, but found </" + tagName + ">.");
                    }
                }
            } else if (tag.endsWith("/")) {
                // Self-closing tag, do nothing
                continue;
            } else {
                // Opening tag
                tagStack.push(tag);
            }
        }

        // Check for any unclosed tags
        while (!tagStack.isEmpty()) {
            errorQueue.enqueue("Error: Unmatched opening tag <" + tagStack.pop() + ">.");
        }
    }

    public void printErrors() {
        if (errorQueue.isEmpty()) {
            System.out.println("No errors found. XML is well-formed.");
        } else {
            while (!errorQueue.isEmpty()) {
                System.out.println(errorQueue.dequeue());
            }
        }
    }
}