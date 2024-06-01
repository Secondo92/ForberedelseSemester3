public class App {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        BST.TreeNode<Integer> node1 = bst.createNewNode(45);
        BST.TreeNode<Integer> node2 = bst.createNewNode(22);
        BST.TreeNode<Integer> node3 = bst.createNewNode(11);
        BST.TreeNode<Integer> node4 = bst.createNewNode(15);
        BST.TreeNode<Integer> node5 = bst.createNewNode(30);
        BST.TreeNode<Integer> node6 = bst.createNewNode(25);
        BST.TreeNode<Integer> node7 = bst.createNewNode(77);
        BST.TreeNode<Integer> node8 = bst.createNewNode(90);
        BST.TreeNode<Integer> node9 = bst.createNewNode(88);
        bst.insert(node1.element);
        bst.insert(node2.element);
        bst.insert(node3.element);
        bst.insert(node4.element);
        bst.insert(node5.element);
        bst.insert(node6.element);
        bst.insert(node7.element);
        bst.insert(node8.element);
        bst.insert(node9.element);

        /*
        //bst.inorder();
        //bst.preorder();

        bst.postorder();


        System.out.println();
        System.out.println(bst.isLeaf(node1));
        System.out.println(bst.height());

         */

        /*
        System.out.println("Removing min...");
        System.out.println(bst.removeMin());
        System.out.println("Min removed");

        System.out.println("Removing max...");
        System.out.println(bst.removeMax());
        System.out.println("Max removed");

         */
/*
        System.out.println("Greater than...");
        System.out.println(bst.greaterThan(25));
        System.out.println("Greater than..");

        System.out.println("Sum of elements: " + bst.sumOfElements());
        System.out.println(bst.findMax());
        System.out.println(bst.findMin());

 */
        System.out.println("Number of leaves: " + bst.numberOfLeaves());
        System.out.println(bst.heightNodeCount(1));

    }
}
