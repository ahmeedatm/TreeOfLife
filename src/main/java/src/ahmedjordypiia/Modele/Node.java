package src.ahmedjordypiia.Modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private String name;
    private int childNodes;
    private int leafNodes;
    private int tolorgLink;
    private int extinct;
    private int confidence;
    private int phylesis;
    private ArrayList<Node> children = new ArrayList<>();

    public Node(String[] fields) {
        this.id = Integer.parseInt(fields[0]);
        this.name = fields[1];
        this.childNodes = Integer.parseInt(fields[2]);
        this.leafNodes = Integer.parseInt(fields[3]);
        this.tolorgLink = Integer.parseInt(fields[4]);
        this.extinct = Integer.parseInt(fields[5]);
        this.confidence = Integer.parseInt(fields[6]);
        this.phylesis = Integer.parseInt(fields[7]);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addChildren(Node child) {
        children.add(child);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public static void main(String[] args) {
        Node node = new Node(new String[]{"1", "Root", "1", "0", "0", "0", "0", "0"});
        System.out.println(node.getId());
        System.out.println(node.getName());
    }
}
