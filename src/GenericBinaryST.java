package src;

import java.util.ArrayList;

public class GenericBinaryST {
    TreeNode root;

    /**
     *insert method for BST
     * @param data into BST
     */
    void insert(String data) {
        root = insertLine(root, data);

    }

    /**
     *recursive helper for inserting
     * @param root start node in binary search tree
     * @param data stored data in BST
     * @return returns inserted node
     */
    private TreeNode insertLine(TreeNode root, String data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }
        if (data.compareTo(root.data)<0){
            root.left= insertLine(root.left,data);
        } else if (data.compareTo(root.data)>0) {
            root.right=insertLine(root.right, data);
        }
        return root;
    }

    /**
     * traverses through BST
     * @param root node in BST
     */
    public void Trav(TreeNode root){
        if (root!=null){
            Trav(root.left);
            System.out.println(root.data);
            Trav(root.right);
        }
    }

    public ArrayList<String> sim(String term){
        ArrayList<String> simt= new ArrayList<>();
        simS(root, term, simt);
        return simt;
    }
    private void simS(TreeNode root, String term, ArrayList<String> simt){
        if (root == null) {
            return ;
        }
        simS(root.left, term, simt);
        String[] spl = root.data.split("\t");
        if (spl[0].contains(term)) {
            simt.add(spl[0]);

        }
        simS(root.right, term, simt);


    }

    /**
     * searches for String data stored in node
     * @param term term to search for in BST
     * @return returns string data stored in BST node
     */
    public String search(String term){
        return searchTerm(root, term);
    }

    /**
     * recursive hepler for BST
     * @param root start node in BST
     * @param term string to search for in BST
     * @return returns a nodes data
     */
    private String searchTerm(TreeNode root, String term){
        if (root==null){
            return null;
        }
        String[] sp= root.data.split("\t");
        if (term.equals(sp[0])){
            return root.data;
        }
        else if (term.compareTo(sp[0])<0){
            return searchTerm(root.left,term);
        }
        else {
            return searchTerm(root.right, term);
        }

    }

    /**
     * function to delete specified node in BST
     * @param term string to search for and delete
     */
    public void delete(String term){
        root=deleteLine(root, term);
    }

    /**
     *recursive helper for delete
     * @param root node in BST
     * @param term string to search for
     * @return deletes node from BST
     */

    private TreeNode deleteLine(TreeNode root, String term){
        if (root==null) {
            return null;
        }
        String[] sp = root.data.split("\t");
        if (term.equals(sp[0])) {
            if (root.left == null && root.right == null) {
                return null;
            }
             else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                root.data = min(root.right);
                root.right = deleteLine(root.right, root.data);
            }
        } else if (term.compareTo(sp[0])<0){
            root.left= deleteLine(root.left, term);

        }
        else{
            root.right= deleteLine(root.right, term);
        }
        return root;

    }

    /**
     * finds min value in right subtree, helps delete function
     * @param node in BST
     * @return returns value stored in min node in right subtree
     */
    private String min(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    /**
     *search function for BST
     * @param term string to search for
     * @param stat string to search for
     * @return returns whole string that has been searched for
     */
    public String searchFull(String term, String stat){
        return searchFullTerm(root, term, stat);
    }

    /**
     * recursive helper for search
     * @param root node in BST
     * @param term string to search for
     * @param state string to search for
     * @return returns string value in node
     */
    private String searchFullTerm(TreeNode root, String term, String state){
        if (root==null){
            return null;
        }
        String[] sp= root.data.split("\t");
        if (term.equals(sp[0])){
            if (sp[1].equals(state)){
                return root.data;}
            else return null;
        }
        else if (term.compareTo(sp[0])<0){
            return searchFullTerm(root.left,term, state);
        }
        else {
            return searchFullTerm(root.right, term, state);
        }
    }
}