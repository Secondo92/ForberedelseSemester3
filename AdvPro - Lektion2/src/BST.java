
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


    @Override
    /** Postorder traversal from the root */
    public void postorder() {
        postorderHelper(root);
    }

    public void postorderHelper(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        postorderHelper(node.left);
        postorderHelper(node.right);
        System.out.print(node.element + " ");
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
