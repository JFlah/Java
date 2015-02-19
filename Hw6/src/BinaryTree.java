/**
 * Class contains several method which manipulate tree loaded into GUI
 * Author: Jack Flaherty
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class BinaryTree<E> {

    protected E data;
    protected BinaryTree<E> left, right;

    BinaryTree() {
        data = null;
        left = right = null;
    }

    BinaryTree(E item) {
        data = item;
        left = new BinaryTree<E>();
        right = new BinaryTree<E>();
    }

    BinaryTree(E item, BinaryTree<E> left, BinaryTree<E> right) {
        data = item;
        this.left = left;
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setData(E obj) {
        data = obj;
    }

    public void setLeft(BinaryTree<E> tree) {
        left = tree;
    }

    public void setRight(BinaryTree<E> tree) {
        right = tree;
    }

    public boolean isEmpty() {
        return left == null && right == null;
    }

    public boolean isLeaf() {
        return !isEmpty() && left.isEmpty() && right.isEmpty();
    }

    public int nodeCount() {
        if (isEmpty())
            return 0;
        else
            return 1 + left.nodeCount() + right.nodeCount();
    }

    public int leafCount() {
        if (isEmpty()) {
            return 0;
        }
        if (isLeaf()) {
            return 1;
        }
        int left = 0, right = 0;
        if (!getRight().isEmpty()) {
            right = getRight().leafCount();
        }
        if (!getLeft().isEmpty()) {
            left = getLeft().leafCount();
        }

        return left + right;

    }

    public BinaryTree<E> mirrorImage() {
        if (isEmpty()) {
            return new BinaryTree<E>();
        }
        if (isLeaf()) {
            return this;
        }
        BinaryTree<E> mirror = new BinaryTree<E>(this.data);
        mirror.setLeft(this.right.mirrorImage());
        mirror.setRight(this.left.mirrorImage());

        return mirror;
    }

    public int height() {
        if (isEmpty()) {
            return -1;
        }
        if (isLeaf()) {
            return 0;
        }
        int left = 0, right = 0;
        if (!getRight().isEmpty()) {
            right = 1 + getRight().height();
        }
        if (!getLeft().isEmpty()) {
            left = 1 + getLeft().height();
        }

        return left > right ? left : right;
    }

    public int levelCount(int level) {
        if (isEmpty()) {
            return 0;
        }

        if (level == 0) {
            return 1;
        }

        int left = 0, right = 0;
        if (!this.getLeft().isEmpty()) {
            left = this.getLeft().levelCount(level - 1);
        }
        if (!this.getRight().isEmpty()) {
            right = this.getRight().levelCount(level - 1);
        }

        return left + right;
    }

    public int weightBalanceFactor() {
        if (this.isEmpty()) {
            return -1;
        }
        if (this.isLeaf()) {
            return 0;
        }

        int left = getLeft().nodeCount();
        int right = getRight().nodeCount();

        return Math.abs(left - right);
    }

    public int nodeSum() {
        if (isEmpty()) {
            return 0;
        }
        if (isLeaf()) {
            return Integer.parseInt(getData().toString());
        }
        int sum = 0;
        sum = Integer.parseInt(getData().toString()) + getRight().nodeSum() + getLeft().nodeSum();

        return sum;
    }

    @SuppressWarnings("unchecked")
    public void doubles() {
        int leaf;
        if (isEmpty()) {
            return;
        }
        if (isLeaf()) {
            leaf = Integer.parseInt(getData().toString()) * 2;
            setData((E) Integer.toString(leaf));
        }
        int doubling = Integer.parseInt(this.getData().toString()) * 2;
        setData((E) Integer.toString(doubling));

        if (!getRight().isEmpty()) {
            getRight().doubles();
        }
        if (!getLeft().isEmpty()) {
            getLeft().doubles();
        }
    }

    public int maxPathSum() {
        if (isEmpty()) {
            return 0;
        }
        if (isLeaf()) {
            return Integer.parseInt(getData().toString());
        }
        int right = 0, left = 0;
        if (!getRight().isEmpty()) {
            right = getRight().maxPathSum();

        }
        if (!getLeft().isEmpty()) {
            left = getLeft().maxPathSum();

        }
        int me = Integer.parseInt(getData().toString());
        return right > left ? right + me : left + me;
    }

    public String preOrder() {
        if (isEmpty()) {
            return "";
        }

        return this.getData() + " " + getLeft().preOrder() + getRight().preOrder();
    }

    public String inOrder() {
        if (isEmpty()) {
            return "";
        }
        return getLeft().inOrder() + this.getData().toString() + " " + getRight().inOrder();
    }

    public String postOrder() {
        if (isEmpty()) {
            return "";
        }

        return getLeft().postOrder() + getRight().postOrder() + this.getData() + " ";
    }

    public String levelOrder() {
        Queue<BinaryTree<E>> sq = new LinkedList<BinaryTree<E>>();
        String levelOrder = "";
        if (!this.isEmpty()) {
            sq.add(this);
        }
        while (!sq.isEmpty()) {
            BinaryTree<E> cur = sq.remove();
            levelOrder += cur.getData().toString() + " ";
            if (!cur.getLeft().isEmpty()) {
                sq.add(cur.getLeft());
            }
            if (!cur.getRight().isEmpty()) {
                sq.add(cur.getRight());
            }
        }
        return levelOrder;
    }


    public String toString(String indent) {
        if (isEmpty())
            return "";
        else
            return right.toString(indent + "   ") + "\n" +
                    indent + "/\n" +
                    indent + data + "\n" +
                    indent + "\\" +
                    left.toString(indent + "   ");
    }

    public String toString() {
        return toString("");
    }
}