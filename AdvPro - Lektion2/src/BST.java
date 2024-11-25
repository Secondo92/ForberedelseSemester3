import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BST<E> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;

    /**
     * Create a default BST with a natural order comparator
     */
    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Create a BST with a specified comparator
     */
    public BST(java.util.Comparator<E> c) {
        this.c = c;
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    @Override
    /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        boolean found = false;
        while (current != null && !found) {
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                found = true; // Element is found
        }

        return found;
    }

    public int sumOfElements(){
        return sumOfElementsHelper(root);
    }
    private int sumOfElementsHelper(TreeNode<E> node){
        if(node == null){
            return 0;
        }
        return (int)node.element + sumOfElementsHelper(node.left) + sumOfElementsHelper(node.right);
    }

    public int findMax(){
        return findMaxHelper(root);
    }
    private int findMaxHelper(TreeNode<E> node){
        if(node == null){
            return Integer.MIN_VALUE;
        }
        int leftMax = findMaxHelper(node.left);
        int rightMax = findMaxHelper(node.right);
        return Math.max((int)node.element, Math.max(leftMax, rightMax));
    }

    public int findMin(){
        return findMinHelper(root);
    }
    private int findMinHelper(TreeNode<E> node){
        if(node == null){
            return Integer.MAX_VALUE;
        }
        int leftMin = findMinHelper(node.left);
        int rightMin = findMinHelper(node.right);
        return Math.min((int)node.element, Math.min(leftMin, rightMin));
    }

    @Override
    /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        boolean inserted = true;
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null && inserted)
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    inserted = false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (c.compare(e, parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return inserted; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    /** Inorder traversal from the root */
    public void inorder() {
        inorderHelper(root);
    }

    public void inorderHelper(TreeNode<E> node){
        if(node == null){
            return;
        }
        inorderHelper(node.left);
        System.out.print(node.element + " ");
        inorderHelper(node.right);
    }


    public ArrayList<E> greaterThan(E element) {
        ArrayList<E> greaterList = new ArrayList<>();
        if (root == null) {
            return greaterList;
        }
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();

            if ((int) node.element > (int) element) {
                greaterList.add(node.element);
                if (node.right != null) {
                    node = node.right;
                } else {
                    node = null;
                }
            } else {
                node = node.right;
            }
        }
        return greaterList;
    }



    public int removeMin(){
        TreeNode<E> parent = null;
        TreeNode<E> node = root;
        while(node.left != null){
            parent = node;
            node = node.left;
        }
        int nodeToDelete = (int)node.element;
        if(node.right != null) {
            parent.left = node.right;
        } else {
            parent.left = null;
        }
        if(parent == null){
            root = node.right;
        }
        return nodeToDelete;
    }

    public int removeMax(){
        TreeNode<E> parent = null;
        TreeNode<E> node = root;
        while(node.right != null){
            parent = node;
            node = node.right;
        }
        int nodeToDelete = (int)node.element;
        if(node.left != null){
            parent.right = node.left;
        } else {
            parent.right = null;
        }
        return nodeToDelete;
    }

    public int numberOfLeaves() {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> current = root;
        int numberOfLeaves = 0;
        if(root == null) {
            return 0;
        }
        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                // Her går vi konstant til venstre indtil hvad der er til venstre er null. Og samtidigt smider vi
                // Det som ikke er null på stakken.
                stack.push(current);
                current = current.left;
            }
            // Noden til venstre var null, så derfor skal current være lig med det som den var for 1 iteration siden
            // Som heldigvis ligger på stakken
            current = stack.pop();

            // Vi tjekker om højre og venstre node er null. Hvis de er, har vi fundet et blad
            if(current.left == null && current.right == null) {
                numberOfLeaves++;
            }
            // Gå til højre og tilbage i det indre while-loop som smider det på stakken og går til venstre hvis den kan
            // Hvis ikke, så gå tilbage op i stakken
            current = current.right;
        }
        return numberOfLeaves;
    }

    public int heightNodeCount(int targetHeight) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode<E>> stack = new Stack<>();
        Stack<Integer> heights = new Stack<>();
        stack.push(root);
        heights.push(0);
        int numberOfNodes = 0;

        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();
            int height = heights.pop();

            if (height == targetHeight) {
                numberOfNodes++;
            } else if (height < targetHeight) {
                if (node.right != null) {
                    stack.push(node.right);
                    heights.push(height + 1);
                }
                if (node.left != null) {
                    stack.push(node.left);
                    heights.push(height + 1);
                }
            }
        }

        return numberOfNodes;
    }

    @Override
    /** Postorder traversal from the root */
    public void postorder() {
        postorderHelper(root);
    }

    public void postorderHelper(TreeNode<E> node) {
        if (node == null) {
            System.out.println("Den var null...");
            return;
        }
        System.out.println("Jeg står nu på " + node.element);
        System.out.println("Går til venstre...");
        postorderHelper(node.left);
        System.out.println("Går til højre...");
        postorderHelper(node.right);
        System.out.println("Bearbejder knuden " + node.element);
        if(node.left == null && node.right == null){
            System.out.println("Oof, I found a leaf: " + node.element);
        }
    }


    public boolean isLeaf(TreeNode<E> node){
        return node.left == null && node.right == null;
    }

    public boolean isInternal(TreeNode<E> node){
        return node.left != null || node.right != null;
    }

    private int heightHelper(TreeNode<E> node){
        if(node == null){
            return 0;
        }
        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
    public int height(){
        return heightHelper(root);
    }



    @Override
    /** Preorder traversal from the root */
    public void preorder() {
        preorderHelper(root);
    }

    public void preorderHelper(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.element + " ");
        preorderHelper(node.left);
        preorderHelper(node.right);
    }


    /**
     * This inner class is static, because it does not access
     * any instance members defined in its outer class
     */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }


    @Override
    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        boolean found = false;
        while (current != null && !found) {
            if (c.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                found = true; // Element is in the tree pointed at by current
        }

        if (found) {
            // Case 1: current has no left child
            if (current.left == null) {
                // Connect the parent with the right child of the current node
                if (parent == null) {
                    root = current.right;
                } else {
                    if (c.compare(e, parent.element) < 0)
                        parent.left = current.right;
                    else
                        parent.right = current.right;
                }
            } else {
                // Case 2: The current node has a left child
                // Locate the rightmost node in the left subtree of
                // the current node and also its parent
                TreeNode<E> parentOfRightMost = current;
                TreeNode<E> rightMost = current.left;

                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right; // Keep going to the right
                }

                // Replace the element in current by the element in rightMost
                current.element = rightMost.element;

                // Eliminate rightmost node
                if (parentOfRightMost.right == rightMost)
                    parentOfRightMost.right = rightMost.left;
                else
                    // Special case: parentOfRightMost == current
                    parentOfRightMost.left = rightMost.left;
            }
            size--; // Reduce the size of the tree
        }
        return found; // Element deleted successfully
    }

//
    //-------------------------------------------------------------------



}
