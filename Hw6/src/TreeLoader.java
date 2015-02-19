/**
 * Load a tree from a text file.  Format is line based, with each line
 * representing one node in the tree, in level order.  The first line
 * contains a String representing the root data item.  Every other line
 * contains a String (data item in the node), an L or R to indicate
 * whether this node is the left or right child of its parent, and an
 * integer index to indicate its parent node.
 *
 * @author John Donaldson
 * @author Benjamin Kuperman (Spring 2007)
 * @author Alexa Sharp (Fall 2012)
 * @author John Donaldson (Fall 2014)
 */

/**
 * Class that loads tree into GUI from given file.
 * File edited by Jack Flaherty
 */

import java.io.*;
import java.util.*;

public class TreeLoader {

    public static BinaryTree<String> loadTreeFromFile(String fname) throws FileNotFoundException
    {
        File treeFile = new File(fname);
        Scanner scan = new Scanner(treeFile);

        if (!scan.hasNext()) {
            return new BinaryTree<String>();
        }

        ArrayList<BinaryTree<String>> treeList = new ArrayList<BinaryTree<String>>();
        String data = scan.nextLine();
        BinaryTree<String> workTree = new BinaryTree<String>(data, new BinaryTree<String>(), new BinaryTree<String>());
        treeList.add(workTree);

        while (scan.hasNextLine()) {
            String nLine = scan.nextLine();
            String[] currentLine = nLine.split(" ");
            String position = currentLine[1];
            String datum = currentLine[0];
            BinaryTree<String> parent = treeList.get(Integer.parseInt(currentLine[2]));

            BinaryTree<String> newChild = new BinaryTree<String>(datum);
            if (position.equals("L")) {
                parent.setLeft(newChild);
            }
            else if (position.equals("R")) {
                parent.setRight(newChild);
            }
            treeList.add(newChild);
        }


       return treeList.get(0);
    }

    // So you can test your tree loader
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length!=1){
            System.out.println("Usage:  java TreeLoader filename");
        }
        else {
            BinaryTree<String> t = loadTreeFromFile(args[0]);
            System.out.println(t);
        }
    }
}
