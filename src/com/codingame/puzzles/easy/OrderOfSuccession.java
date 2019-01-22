package com.codingame.puzzles.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.codingame.com/ide/puzzle/order-of-succession
 */
public class OrderOfSuccession {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        Tree tree = new Tree();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            String parent = in.next();
            int birth = in.nextInt();
            String death = in.next();
            String religion = in.next();
            String gender = in.next();

            tree.insert(name, parent, birth, death, religion, gender);
        }

        List<String> res = tree.traverse();
        res.forEach(System.out::println);
    }
    private static class Tree {
        private Node root;

        public void insert(String name, String parent, int birth, String death,
                           String religion, String gender) {
            if (parent.equals("-")) {
                root = new Node(name, birth, death, religion, gender);
            } else {
                Node child = new Node(name, birth, death, religion, gender);
                insertInternal(root, parent, child);
            }
        }

        private boolean insertInternal(Node curr, String parent, Node child) {
            if (curr.name.equals(parent)) {
                // System.out.println("Inserting " + parent + " -> "+  child.name);
                curr.insertChild(child);
                return true;
            } else if (curr.children == null) {
                return false;
            } else {
                for (Node c : curr.children) {
                    if (insertInternal(c, parent, child)) {
                        return true;
                    }
                }
                return false;
            }
        }

        private List<String> traverse() {
            List<String> res = new ArrayList<>();
            traverseInternal(root, res);
            return res;
        }

        private void traverseInternal(Node node, List<String> res) {
            if (!node.religion.equals("Catholic") && node.isAlive) {
                res.add(node.name);
            }
            if (node.children == null) {
                return;
            }

            for (Node c : node.children) {
                traverseInternal(c, res);
            }
        }

        private static class Node {
            String name;
            List<Node> children;
            int birth;
            boolean isAlive;
            String religion;
            String gender;

            Node(String name, int birth, String death, String religion,
                 String gender) {
                this.name = name;
                this.birth = birth;
                this.isAlive = death.equals("-");
                this.religion = religion;
                this.gender = gender;
                this.children = null;
            }

            void insertChild(Node child) {
                int index = 0;
                if (children == null) {
                    children = new LinkedList<>();
                } else {
                    if (child.gender.equals("F")) {
                        while(index < children.size() && children.get(index).gender.equals("M")) {
                            index++;
                        }

                        while(index < children.size() && children.get(index).birth < child.birth) {
                            index++;
                        }
                    } else {
                        while(index < children.size()
                                && children.get(index).gender.equals("M")
                                && children.get(index).birth < child.birth) {
                            index++;
                        }
                    }
                }
                children.add(index, child);
            }
        }
    }
}
