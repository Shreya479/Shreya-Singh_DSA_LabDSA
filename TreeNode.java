class TreeNode {
    int val;
    TreeNode left, right;
  
    public TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class BSTPairWithSum {
    TreeNode root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.val)
            root.left = insertRec(root.left, key);
        else if (key > root.val)
            root.right = insertRec(root.right, key);

        return root;
    }

    public void findPair(TreeNode root, int sum) {
        if (root == null)
            return;

        TreeNode left = root, right = root;
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();

        // Initialize left pointer to the leftmost node
        while (left != null) {
            leftStack.push(left);
            left = left.left;
        }

        // Initialize right pointer to the rightmost node
        while (right != null) {
            rightStack.push(right);
            right = right.right;
        }

        // Traverse the tree with two pointers
        while (!leftStack.isEmpty() && !rightStack.isEmpty() && leftStack.peek().val < rightStack.peek().val) {
            int currSum = leftStack.peek().val + rightStack.peek().val;
            if (currSum == sum) {
                System.out.println("Pair is (" + leftStack.peek().val + "," + rightStack.peek().val + ")");
                return;
            } else if (currSum < sum) {
                // Move left pointer to the next greater node
                TreeNode node = leftStack.pop().right;
                while (node != null) {
                    leftStack.push(node);
                    node = node.left;
                }
            } else {
                // Move right pointer to the next smaller node
                TreeNode node = rightStack.pop().left;
                while (node != null) {
                    rightStack.push(node);
                    node = node.right;
                }
            }
        }

        System.out.println("Nodes are not found.");
    }

    public static void main(String[] args) {
        BSTPairWithSum tree = new BSTPairWithSum();
        tree.insert(10);
        tree.insert(20);
        tree.insert(40);
        tree.insert(50);
        tree.insert(70);
        tree.insert(60);
        tree.insert(30);

        int sum = 130;
        System.out.println("Sum = " + sum);
        tree.findPair(tree.root, sum);
    }
}
