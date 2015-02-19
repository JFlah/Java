/**
 * The driver class for our TreeMethodFrame GUI.  Contains initialization
 * functions and the main method for users to run.
 *
 * @author John Donaldson
 * @author Benjamin Kuperman (Spring 2007)
 * @author Alexa Sharp (Fall 2012)
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TreeMethodApplication {
    boolean packFrame = false;
    private BinaryTree<String> tree = new BinaryTree<String>();

    //Construct the application
    public TreeMethodApplication() {
        final TreeMethodFrame frame = new TreeMethodFrame();
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        }
        else {
            frame.validate();
        }

        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);

        // -- Initialize the ActionListener for buttons ---------------------

        // Load File
        frame.addButtonListener(0, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.fd.setVisible(true);
                String filename = frame.fd.getFile();
                String dirname = frame.fd.getDirectory();
                if(filename!=null && dirname!=null){
                    try {
                        tree = TreeLoader.loadTreeFromFile(dirname + filename);
                        frame.setOutputArea("");
                    }
                    catch (IOException ex1){
                        frame.setOutputArea(ex1.toString());
                        tree = new BinaryTree<String>();
                    }
                    frame.setTreeDisplayArea(tree.toString());
                }
            }
        });

        // Count Nodes
        frame.addButtonListener(1, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea("Node Count: "+tree.nodeCount());
            }
        });

        // Height
        frame.addButtonListener(2, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea("Height: "+tree.height());
            }
        });

        // Mirror
        frame.addButtonListener(3, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(tree.mirrorImage().toString());
            }
        });

        // Count Leaves
        frame.addButtonListener(4, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int leafct = tree.leafCount();
                if(leafct==1)
            frame.setOutputArea("The tree has 1 leaf");
                else
            frame.setOutputArea("The tree has "+leafct+" leaves");
            }
        });

        // Level Count
        frame.addButtonListener(5, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int level=0;
                String output="";
                int lcount=tree.levelCount(level);
                if(lcount==0)
            output+="The tree is empty";
                else
            while(lcount>0 && level<=1000){
                if(lcount==1)
            output+=("There is 1 node at level "+level+"\n");
                else
            output+=("There are "+lcount+" nodes at level "+level+"\n");
        ++level;
        lcount=tree.levelCount(level);
            }
        frame.setOutputArea(output);
            }
        });


        // Weight Balance Factor
        frame.addButtonListener(6, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea("Weight balance factor:" 
                                       + tree.weightBalanceFactor());
            }
        });


        // Node Sum
        frame.addButtonListener(7, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    frame.setOutputArea("Sum of data values: "+tree.nodeSum());
                }
                catch (ClassCastException ex1){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (NumberFormatException ex2){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (Exception ex){
                    frame.setOutputArea(ex.toString());
                }
            }
        });

        // Double
        frame.addButtonListener(8, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    tree.doubles();
                    frame.setTreeDisplayArea(tree.toString());
                    frame.setOutputArea("");
                }
                catch (ClassCastException ex){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (NumberFormatException ex2){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (Exception ex){
                    frame.setOutputArea(ex.toString());
                }
            }
        });

        // Max Path Sum
        frame.addButtonListener(9, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    frame.setOutputArea("Maximum path sum: "+tree.maxPathSum());
                }
                catch (ClassCastException ex){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (NumberFormatException ex2){
                    frame.setOutputArea("Tree contains non-integer data");
                }
                catch (Exception ex){
                    frame.setOutputArea(ex.toString());
                }
            }
        });
        
        // preorder
        frame.addButtonListener(10, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(""+tree.preOrder());
            }
        });
        // inorder
        frame.addButtonListener(11, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(""+tree.inOrder());
            }
        });
        // postorder
        frame.addButtonListener(12, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(""+tree.postOrder());
            }
        });
        // level order
        frame.addButtonListener(13, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setOutputArea(""+tree.levelOrder());
            }
        });
        
        // Quit
        frame.addButtonListener(14, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(1);
            }
        });

    }

    //Main method
    public static void main(String[] args) {
        new TreeMethodApplication();
    }
}
