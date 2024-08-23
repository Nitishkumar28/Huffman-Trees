/**
 * Project 3
 * Author: Nitish Kumar Yarlagadda
 * HuffmanCodeTree class
 */
public class HuffmanCodeTree {

    /**
     * The HuffmanCodeTree class uses the node class build and maintain a binary tree that represents a collection of
     * Huffman codes for various letters.
     */

    private final HuffmanNode root;

    /**
     * This constructor is responsible to create a Huffman code tree using a provided Node as root.
     * @param root -- It represents the root node of the binary tree
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * This constructor is responsible to create a Huffman code tree based on the data stored in a Huffman code book.
     * @param codebook - It represents the codebook that will be used to construct the tree
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        root = new HuffmanNode( null, null); // Initializing the Huffman code tree with an empty node
        // looping over the characters in the Huffman code book, by getting the related sequences and looping over them
        // Implementing the interface method for customized looping of the characters
        codebook.characterLooper(new HuffmanCodeBook.CharacterEfficientLooper() {
            @Override
            public void charLoop(char character) {
                BinarySequence sequence = codebook.getSequence(character); // retrieving the binary sequence for the passed character using the getSequence method
                put(sequence,character); // calling the put method to update the tree with each code one-by-one.
            }
        }
        );
    }


    /**
     * This method is responsible to check if the tree formed by the root node and its descendants is a valid Huffman
     * code tree.
     * @return - returns a boolean value indicating if the tree formed is valid or not
     */
    public boolean isValid() {
        return root.isValidTree();
    }


    /**
     * This method is responsible to modify the binary tree structure so that the node addressed by the binary sequence
     * stores the given char.
     * @param seq -- It represents the binary sequence which will be added to the tree
     * @param letter -- It represents the letter that is associated with the binary sequence
     */
    public void put(BinarySequence seq, char letter) {
        HuffmanNode newNode = root; // Initializing newNode to root which will be used to traverse the tree based on the given sequence
        // looping though each boolean value in the seq (given binary sequence)
        for (boolean eachBit : seq) {
            // if eachBit is true then it indicates that the value is 1
            if (eachBit) {
                if (newNode.getOne() == null) {
                    HuffmanNode addNode = new HuffmanNode(null,null); // Creating a new node to traverse the tree since the newNode of child one is null
                    newNode.setOne(addNode); // setting the newly created empty node for child one
                    newNode=addNode; // updating the new node to the newly created empty node in order for the traversal to continue from this node
                }
                else {
                    newNode = newNode.getOne(); // Getting and moving onto the new node of child one
                }
            }
            // else represents false which indicates that the value is 0
            else {
                if (newNode.getZero() == null) {
                    HuffmanNode addNode = new HuffmanNode(null,null); // Creating a new node to traverse the tree since the newNode of child zero is null
                    newNode.setZero(addNode); // setting the newly created empty node for child zero
                    newNode=addNode; // updating the new node to the newly created empty node in order for the traversal to continue from this node
                }
                else {
                    newNode = newNode.getZero(); // Getting and moving onto the new node of child zero
                }
            }
        }
        newNode.setData(letter); // Setting the data of the new node to the given letter character indicating that it associates with the binary sequence in the tree
    }


    /**
     * This method is responsible to decode a BinarySequence into a string with the assumption that the tree is currently
     * valid, and that the binary sequence is of a correct length.
     * @param s -- It represents the BinarySequence that need to be decoded into a string
     * @return - returns the decoded string
     */
    public String decode(BinarySequence s) {
        StringBuilder outputString = new StringBuilder(); // Initializing a StringBuilder for this method to be efficient in storing the output string
        HuffmanNode node = root; // creating a variable “node” and having it store the root node of the tree
        // Looping through every boolean value in the given Binary Sequence
        for (boolean eachBit : s) {
            if (eachBit) {
                node = node.getOne(); // updating node to its child one since the boolean is true
            } else {
                node = node.getZero(); // updating node to its child zero since the boolean is false
            }
            if (node.isLeaf()) {
                outputString.append(node.getData()); // adding the data to the output string since it has arrived to a leaf
                node = root; // resetting the node to root
            }
        }
        return outputString.toString(); // returning the final output string by converting the StringBuilder to a string
    }
}
