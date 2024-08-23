/**
 * Project 3
 * Author: Nitish Kumar Yarlagadda
 * HuffmanCodeBook class
 */
public class HuffmanCodeBook {

    /**
     * The HuffmanCodeBook class represents a codebook which is part of the Huffman coding process which tells the correct
     * binary sequence for each letter.
     */

    private BinaryTreeNode root;

    /**
     * This constructor is a 0 argument constructor that is responsible to create a new HuffmanCodeBook objects that
     * contains no letters/sequences.
     */
    public HuffmanCodeBook(){
        this.root = null; // This constructor initializes an empty HuffmanCodeBook having no letters/sequences
    }

    /**
     * This method is responsible to add a given character/letter and binary sequence into the codebook.
     * @param c -- It represents the character that needs to be added in the codebook
     * @param seq -- It represents the binary sequence associated with the character that needs to be added in the codebook
     */
    public void addSequence(char c, BinarySequence seq){
        root = addSequenceHelper(c,seq,root);
    }

    /**
     * This method is a recursive helper method for addSequence that is responsible to add a character and its associated
     * binary sequence to the binary tree.
     * @param c -- It represents the character that needs to be added in the codebook
     * @param seq -- It represents the binary sequence associated with the character that needs to be added in the codebook
     * @param rootNode -- It represents the current root node
     * @return - returns the updated root node after adding to the binary tree
     */
    private BinaryTreeNode addSequenceHelper(char c, BinarySequence seq, BinaryTreeNode rootNode){
        if(rootNode == null){
            return new BinaryTreeNode(c,seq); // Creating a new node with the provided character and binary sequence if the node is null
        }
        else if(c < rootNode.storeChar) {
            // Adding the character to the left child subtree if the provided character is smaller than the present root node character
            rootNode.leftChild = addSequenceHelper(c,seq,rootNode.leftChild);
        }
        else if(c > rootNode.storeChar){
            // Adding the character to the right child subtree if the provided character is greater than the present root node character
            rootNode.rightChild = addSequenceHelper(c,seq,rootNode.rightChild);
        }
        return rootNode;
    }

    /**
     * This method is responsible to return a boolean value to indicate if the codebook contains a given letter.
     * @param letter -- It represents the letter that needs to be checked in the codebook
     * @return - returns true if the codebook contains the given letter otherwise false
     */
    public boolean contains(char letter){
         return containsHelper(letter,root);
    }

    /**
     * This method is a recursive helper method for contains function that is responsible to check if the given letter
     * is present in the binary tree or not.
     * @param letter -- It represents the letters that needs to be checked in the codebook
     * @param rootNode -- It represents the current root node
     * @return - returns true if the letter is present in the binary tree otherwise false
     */
    private boolean containsHelper(char letter,BinaryTreeNode rootNode){
        if(rootNode == null){
            return false; // returning false if the root node is null indicating that given letter is not present in the tree
        }
        if(letter < rootNode.storeChar){
            // searching the letter in the left child subtree if the provided letter is smaller than the present root node character
            return containsHelper(letter,rootNode.leftChild);
        }
        else if(letter > rootNode.storeChar){
            // searching the letter in the right child subtree if the provided letter is greater than the present root node character
            return containsHelper(letter,rootNode.rightChild);
        }
        else{
            return true; // returning true if the letter is equal to the root node character indicating that the given letter is found in the tree
        }
    }

    /**
     * This method is responsible to see if a codebook can handle a given piece of text such that it should return true
     * if and only if every letter in the input string is contained in the codebook.
     * @param letters -- It represents the letters which the string needs to check
     * @return - returns true if the codebook contains all the letters given by the input string otherwise false
     */
    public boolean containsAll(String letters){
        for(char charIterate : letters.toCharArray()){
            if(!contains(charIterate)){
                return false; // returning false if the letters are not found in the codebook
            }
        }
        return true; // returning true indicating that all the letters are found in the codebook
    }

    /**
     * This method is responsible to get the binary sequence associated with the given letter such that if addSequence
     * was previously called with this letter as a parameter then the BinarySequence added with this letter should be
     * returned otherwise null should be returned.
     * @param c -- It represents the letter for which the binary sequence is returned
     * @return - returns the binary sequence associated with the given letter otherwise returns null
     */
    public BinarySequence getSequence(char c){
        return getSequenceHelper(c,root);
    }

    /**
     * This method is a recursive helper method for getSequence that is responsible to retrieve the binary sequence
     * associated with the given letter in the binary tree.
     * @param c -- It represents the letter for which the binary sequence is returned
     * @param rootNode --  It represents the current root node
     * @return - returns the binary sequence associated with the given letter otherwise returns null
     */
    private BinarySequence getSequenceHelper(char c,BinaryTreeNode rootNode){
        if(rootNode == null){
            return null; // returning null if root node is null indicating that provided character is not present in the tree
        }
        if(c < rootNode.storeChar){
            // searching the character in the left child subtree if the provided character (c) is smaller than the present root node character
            return getSequenceHelper(c,rootNode.leftChild);
        }
        else if(c > rootNode.storeChar){
            // searching the character in the right child subtree if the provided character (c) is greater than the present root node character
            return getSequenceHelper(c,rootNode.rightChild);
        }
        else{
            return rootNode.binSequence; // returning the binary sequence of the given character if the character (c) matches the root node character
        }
    }

    /**
     * This method is responsible to encode the input string into a binary sequence by combining in order, the binary
     * sequence that is associated with each letter in the string.
     * @param s -- It represents the input string that needs to be encoded into a binary sequence
     * @return - returns the encoded binary sequence
     */
    public BinarySequence encode(String s){
        BinarySequence finalEncode = new BinarySequence();
        for(char charIterate : s.toCharArray()){
            BinarySequence newSequence = getSequence(charIterate); // retrieving the binary sequence for the current character
            if(newSequence != null){
                finalEncode.append(newSequence); // Appending the encoded sequence if the binary sequence is found
            }
        }
        return finalEncode; // returning the completed encoded binary sequence
    }




    // The below design is an interface which is written in order to loop the character in an efficient manner

    /**
     * This represents an interface which is designed to loop over the characters in the codebook through an efficient
     * manner
     */
    public interface CharacterEfficientLooper{
       void charLoop(char storeChar); // defining an abstract method within the interface that a custom implementation for the looping of the characters
    }


    /**
     * This method is responsible to loop through the characters in the huffman codebook and perform a specified action
     * according to the implementation defined in the interface.
     * @param looper - It represents the action that needs to be performed for every character in the codebook
     */
    public void characterLooper(CharacterEfficientLooper looper){
        characterLooper(looper,root); // Taking an instance of the interface looper and calling the private helper function
    }


    /**
     * This is a recursive helper method for characterLooper that is responsible to perform a specified action on every
     * character in the binary tree
     * @param looper -- It represents the action that needs to be performed for every character in the codebook
     * @param rootNode -- It represents the current root node
     */
    private void characterLooper(CharacterEfficientLooper looper,BinaryTreeNode rootNode){
        // Check if the root node is not null inorder to initiate the traversal of looping
        if(rootNode != null){
            characterLooper(looper,rootNode.leftChild); // Recursive traversing through the left subtree
            looper.charLoop(rootNode.storeChar); // implementing the interface method to perform the action done in huffman code tree
            characterLooper(looper,rootNode.rightChild); // Recursive traversing through the right subtree
        }
    }



    // The below code represents the implementation of the Binary Tree Node that will be implemented in the binary search tree

    /**
     * This class represents the node in the binary tree for storing the characters and their associated binary sequence
     * in the HuffmanCodeBook
     */
    private static class BinaryTreeNode{
        private final char storeChar; // represents the character in the node
        private final BinarySequence binSequence; // represents the binary sequence associated with the character
        private BinaryTreeNode leftChild; // represents the left child node in the binary tree
        private BinaryTreeNode rightChild; // represents the right child node in the binary tree

        /**
         * This constructor is responsible to construct a new BinaryTreeNode with the given character and its associated
         * binary sequence and initializes the left and right child nodes to be null.
         * @param storeChar -- It represents the character to be stored in the tree node
         * @param sequence -- It represents the binary sequence associated with the given character
         */
        private BinaryTreeNode(char storeChar,BinarySequence sequence){
            this.storeChar=storeChar;
            this.binSequence =sequence;
            this.leftChild=null;
            this.rightChild=null;
        }

    }

}