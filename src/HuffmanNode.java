/**
 * Project 3
 * Author: Nitish Kumar Yarlagadda
 * HuffmanNode class
 */
public class HuffmanNode {

    /**
     * The HuffmanNode class represents a node and serves as a component of the HuffmanCodeTree.
     */

    private HuffmanNode zero;
    private HuffmanNode one;
    private Character data;

    /**
     * This constructor makes a non-leaf node by providing its two child nodes and also sets the data to null
     * @param zero -- It represents the left child node
     * @param one -- It represents the right child node
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
        this.data = null;
    }

    /**
     * This constructor makes a leaf node by specifying the data (character) and also sets the left child node and
     * right child node to null.
     * @param data -- It represents the character node that will be stored in the leaf node
     */
    public HuffmanNode(char data) {
        this.zero = null;
        this.one = null;
        this.data = data;
    }

    /**
     * This method is responsible to return the left child node.
     * @return - returns the left child node (zero)
     */
    public HuffmanNode getZero() {
        return zero;
    }

    /**
     * This method is responsible to return the right child node.
     * @return - returns the right child node (one)
     */
    public HuffmanNode getOne() {
        return one;
    }

    /**
     * This method is responsible to return the data stored in the node.
     * @return - return the character data stored in the node.
     */
    public Character getData() {
        return data;
    }

    /**
     * This method is responsible to set the new left child node.
     * @param zero -- It represents the left child node that needs to set
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * This method is responsible to set the new right child node.
     * @param one -- It represents the right child node that needs to set
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * This method is responsible to set the data stored in the node.
     * @param data - It represents the character data that needs to be set
     */
    public void setData(char data) {
        this.data = data;
    }

    /**
     * This method is responsible to check if node is a leaf by checking if it has no left or right children.
     * @return - returns boolean true if the both child nodes are null otherwise false
     */
    public boolean isLeaf() {
        return data != null && zero == null && one == null;
    }

    /**
     * This method is responsible to check if the current node is “valid” for a huffman code tree by checking if it is a
     * leaf node or an internal node.
     * @return - returns boolean true if the node is valid otherwise false
     */
    public boolean isValidNode() {
        boolean leafNode = isLeaf();
        boolean internalNode = data==null && zero!=null && one!=null;
        return leafNode || internalNode;
    }

    /**
     * This method is responsible to check if the current node and all its descendants nodes are valid for a huffman
     * code tree.
     * @return - returns boolean true if the node and all its descendants are valid for a huffman code tree otherwise false
     */
    public boolean isValidTree() {
        if (!isValidNode()) {
            return false; // returning false to indicate that the current node is not valid
        }
        if (isLeaf()){
            return true; // returning true indicating that the current node is a leaf node which means there are no more descendant to check
        }
        return zero.isValidTree() && one.isValidTree(); // Checking the validity of the descendant nodes using recursion
    }
}