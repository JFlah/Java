/**
 * Created by Alyssa on 11/2/14.
 */
import java.util.*;

public class MyTreeMap<K extends Comparable<? super K>,V> implements MapADT<K,V> {


    K key;      // The index by which information is stored
    V value;    // Value associated with the key above
    MyTreeMap<K,V> left,right;  // The children of this node
    int size;   // The number of nodes in this subtree
    int height; // The height of this subtree

    // change this value to true and I'll print the rotations
    private boolean debug = false;

    //--------------------------------------------------------------------------
    // Things TODO - I've moved all the methods you need to change up to here
    //--------------------------------------------------------------------------

    public V get(K searchKey) {
        // If the searchKey does not exist
        if (this.isEmpty()) {
            return null;
        }

        // If the searchKey is the current node
        if (this.key.compareTo(searchKey)==0) {
            return this.value;
        }

        // Otherwise (recurse)
        if (key.compareTo(searchKey) < 0) {
            return this.right.get(searchKey);
        }
        else {
            return this.left.get(searchKey);
        }
    }

    // TODO - implement me too! - add some comments too!
    public V put(K key, V value){

        // insert node or update value
        if (this.key == key) {
            V returnValue = this.value;
            this.value = value;
            return returnValue;
        }
        if (this.isEmpty()) {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.height = 0;
            this.left = new MyTreeMap<K, V>();
            this.right = new MyTreeMap<K, V>();
            return null;
        }

        //recurse - only happens when other if statements fail
        int recL = this.key.compareTo(key);
        if (recL < 0) {
            this.right.put(key, value);
        }
        else {
            this.left.put(key, value);
        }
        //On way back up tree

        // now check to see if I am unbalanced
        if (Math.abs(this.left.getHeight() - this.right.getHeight()) > 1) {
            //Restructure
            restructure();
        }

        // fix my height
        this.setHeight();

        // fix my size
        this.size = 1 + this.right.size() + this.left.size();
        // return old value
        return null;
    }


    /**
     * Rebalances an AVL internal node using the restructure algorithm from
     * class. Suppose Z is the node that fails the height balance property,
     * and let Y be Z's tallest child, and X be Y's tallest child.
     * Given these labels, let a, b, and c be the value of X,Y,Z in sorted order,
     * whatever that may be (so, if the tree looks like
     * <pre>
     *                  Z
     *                /   \
     *               Y     o
     *              / \
     *             o   X
     *                / \
     *               o   o
     * </pre>
     * then the sorted order is Y,X,Z, and therefore a is Y's value,
     * b is X's value, and c is Z's value.)
     * Let t0, t1, t2, and t3 be the subtrees from left to right (so in
     * the example above, t0 is Y's left subtree, t1 is X's left subtree,
     * t2 is X's right subtree, and t3 is Z's right subtree.)
     * You will be setting these variables according to the definitions
     * above, and depending on which subtrees are causing the imbalance.
     * Once you've made these assignments, the provided code will return the
     * tree with the final arrangement of
     * <pre>
     *                  b
     *                /   \
     *               a     c
     *              / \   / \
     *             t0 t1 t2 t3
     * </pre>
     */
    private void restructure() {
        // fill in these values such that
        //      a < b < c
        // and
        //      t0->t3 are the left to right trees
        MyTreeMap<K,V> a, b, c, t0, t1, t2, t3;

        if (left.height > right.height) {
            // left side taller, right subtree is last
            if (left.left.height > left.right.height) {
                // left->left
                if (debug) System.err.println("Single right rotation at: "+key);

                c = this;
                b = this.left;
                a = this.left.left;
                t0 = a.left;
                t1 = a.right;
                t2 = b.right;
                t3 = c.right;

            } else {
                // left->right turn
                if (debug) System.err.println("Double right rotation at: "+key);

                // TODO - replace the following line
                a= this.left;
                b= this.left.right;
                c= this;
                t0= a.left;
                t1= b.left;
                t2=b.right;
                t3=c.right;

            }
        } else {
            if (right.right.height > right.left.height) {
                // right->right
                if (debug) System.err.println("Single left rotation at: "+key);

                // TODO - replace the following line
                a = this;
                b = this.right;
                c = this.right.right;
                t0 = a.left;
                t1 = b.left;
                t2 = c.left;
                t3 = c.right;
            } else {
                // right->left turn
                if (debug) System.err.println("Double left rotation at: "+key);

                // TODO - replace the following line
                a= this;
                b = this.right.left;
                c = this.right;
                t0 = a.left;
                t1 = b. left;
                t2 = b. right;
                t3 = c.right;

            }
        }


        // If you've done the above correctly, this should fix your current node
        MyTreeMap<K, V> newLeft = new MyTreeMap<K, V>(a.key,a.value,t0,t1);
        MyTreeMap<K, V> newRight = new MyTreeMap<K, V>(c.key,c.value,t2,t3);
        // now fix myself
        this.key = b.key;
        this.value = b.value;
        this.left = newLeft;
        this.right = newRight;
        setHeight();
    }

    //--------------------------------------------------------------------------
    // ** NO CHANGES NEEDED BELOW - BUT YOU MIGHT WANT TO READ AND UNDERSTAND **
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // Constructors - outside users can only make an empty tree
    //--------------------------------------------------------------------------

    /**
     * Creates an empty tree.
     */
    public MyTreeMap(){
        this(null,null,null,null);
        this.size = 0;
    }

    /**
     * Build a tree from existing components.  To be used only within MyTreeMap
     * and subchildren.
     * @param key Key to store at this location
     * @param value Value to store at this location (associated with Key)
     * @param left An existing subtree, all Key values should be smaller than key
     * @param right An existing subtree, all Key values should be greater than key
     */

    protected MyTreeMap(K key, V value, MyTreeMap<K, V> left, MyTreeMap<K, V> right) {
        super();
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        // fix the height
        setHeight();
        // and the size
        this.size = 1;
        if (left!=null) this.size+=left.size;
        if (right!=null) this.size+=right.size;
    }


    /**
     * Used to construct a single leaf element.  Empty subtrees are put in place of
     * children to allow for recursive methods to not have to worry about null
     * pointers.
     * @param key Key to store at this location
     * @param value Value to store at this location (associated with Key)
     */

    protected MyTreeMap(K key, V value) {
        this(key,value,new MyTreeMap<K,V>(), new MyTreeMap<K,V>());
        this.size = 1;
    }

    /**
     * Determine if this is a placeholder subtree for an empty leaf branch.
     * @return true only if this is a placeholder node, false otherwise
     */
    public boolean isEmpty(){
        return left==null && right==null;
    }

    /**
     * Determine if this is a leaf node (not an empty node).
     * @return true if this node is not a placeholder and has no children
     */
    public boolean isLeaf() {
        return !isEmpty() && left.isEmpty() && right.isEmpty();
    }

    /**
     * Creates a StringBuffer (String takes too long) and then starts the
     * recursive traversal of nodes.
     * @return String representation of the (Key:Value) pairs
     */
    private String toStringHelper(){
        StringBuilder sbuf = new StringBuilder();
        toStringHelper(sbuf);
        return sbuf.toString();
    }

    /**
     * Adds (Key:Value) pairs inorder into the StringBuffer.
     * @param sbuf where to store the string version of the tree
     */
    private void toStringHelper(StringBuilder sbuf) {
        if(isEmpty())
            return;
        else {
            if(!left.isEmpty()) {
                left.toStringHelper(sbuf);
                sbuf.append(",");
            }
            sbuf.append( "("+key+":"+value+")" );
            if(!right.isEmpty()) {
                sbuf.append(",");
                right.toStringHelper(sbuf);
            }
        }
    }

    public String toString(){
        return "[" + toStringHelper() + "]";
    }

    // helper methods for trees
    private void setHeight() {
        if (isEmpty())
            this.height = -1;
        else
            this.height = 1+Math.max(left.getHeight(),right.getHeight());
    }

    public int getHeight() {
        return height;
    }

    public int size() {
        return this.size;
    }


    //--------------------------------------------------------------------------
    // Some iterators that you might find useful
    //--------------------------------------------------------------------------

    /**
     * Create an inorder iterator of the Keys in the tree.
     * @return inorder iterator of the Keys
     */

    public Iterator<K> keys() {
        List<K> list = new LinkedList<K>();
        addKeysToList(list);
        return list.iterator();
    }

    private void addKeysToList(List<K> l) {
        if (!isEmpty()){
            this.left.addKeysToList(l);
            l.add(this.key);
            this.right.addKeysToList(l);
        }
    }

    /**
     * Create an inorder iterator of the entries in the tree.
     * @return inorder iterator of the entries
     */

    public Iterator<Map.Entry<K, V>> entries() {
        List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>();
        addEntriesToList(list);
        return list.iterator();
    }

    private void addEntriesToList(List<Map.Entry<K, V>> list) {
        if (!isEmpty()){
            this.left.addEntriesToList(list);
            list.add(new AbstractMap.SimpleEntry<K,V>(key,value));
            this.right.addEntriesToList(list);
        }
    }

    public static void main(String[] args) {
        MyTreeMap<String, List<Integer>> map = new MyTreeMap<String, List<Integer>>();
        String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m"};
        for (int i = 0; i < 10; i++) {
            List<Integer> loc = new LinkedList<Integer>();
            loc.add(i);
            map.put(letters[i]+String.valueOf(i),loc);
        }
        System.out.println(map.toString());

        System.out.println(map.get("a0"));

    }


}

