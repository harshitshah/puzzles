package puzzle.treebalance;

public class CheckBalance
{


    private static class Balance
    {
        private int maxDistToLeaf;
        private boolean balanced;

        public Balance(int maxDistToLeaf, boolean balanced)
        {
            this.maxDistToLeaf = maxDistToLeaf;
            this.balanced = balanced;
        }

        public boolean isBalanced()
        {
            return balanced;
        }

        public int getMaxDistToLeaf()
        {
            return maxDistToLeaf;
        }

        static Balance mergeBalances(Balance l, Balance r)
        {
            int leftDist = l.getMaxDistToLeaf();
            int rightDist = r.getMaxDistToLeaf();
            int maxD = Math.max(leftDist, rightDist) + 1;

            boolean balanced = (Math.abs(leftDist-rightDist) <= 1) && l.isBalanced() && r.isBalanced();
            return new Balance(maxD, balanced);
        }
    }



    public static boolean computeBalance(TreeNode<? extends Object> node)
    {
        Balance balance = computeBalanceRecursive(node, 0);
        return balance.isBalanced();
    }

    private static Balance computeBalanceRecursive(TreeNode<? extends  Object> node, int depthFromRoot)
    {
        if (node == null)
        {
            return new Balance(-1, true);
        }

        Balance leftBalance = computeBalanceRecursive(node.getLeftChild(), depthFromRoot+1);
        Balance rightBalance = computeBalanceRecursive(node.getRightChild(), depthFromRoot+1);
        return Balance.mergeBalances(leftBalance, rightBalance);
    }



    public static void main(String[] args) {
        // create a tree
        // root -> l1, r2
        // l1 -> r12
        // r2 -> l21, r22
        // r12 -> l121, r122
        // l21 -> l211, r212
        // r22 -> l221, r222
        TreeNode<Integer> r222 = new TreeNode<>(0);
        TreeNode<Integer> l221 = new TreeNode<>(0);
        TreeNode<Integer> r22 = new TreeNode<>(0, l221, r222);
        TreeNode<Integer> l211 = new TreeNode<>(0);
        TreeNode<Integer> r212 = new TreeNode<>(0);
        TreeNode<Integer> l21 = new TreeNode<>(0, l211, r212);
        TreeNode<Integer> l121 = new TreeNode<>(0);
        TreeNode<Integer> r122 = new TreeNode<>(0);
        TreeNode<Integer> r12 = new TreeNode<>(0, l121, r122);
        TreeNode<Integer> r2 = new TreeNode<>(0, l21, r22);
        TreeNode<Integer> l1 = new TreeNode<>(0, null, r12);
        //TreeNode<Integer> l1 = new TreeNode<>(0, null, null);
        TreeNode<Integer> root = new TreeNode<>(0, l1, r2);
        //TreeNode<Integer> root = new TreeNode<>(0, null, r2);


        Balance b1 = new Balance(-1, true);
        Balance b2 = Balance.mergeBalances(b1, b1);
        Balance b3 = Balance.mergeBalances(b2, b1);
        System.out.println("is b3 balanced: " + b3.isBalanced());
        Balance b4 = Balance.mergeBalances(b3, b1);
        System.out.println("is b4 balanced: " + b4.isBalanced());

        System.out.println("is r12 balanced: " + computeBalance(r12));
        System.out.println("is l1 balanced: " + computeBalance(l1));
        System.out.println("is root balanced: " + computeBalance(root));
    }
}
