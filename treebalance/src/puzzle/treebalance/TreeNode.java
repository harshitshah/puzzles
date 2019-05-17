package puzzle.treebalance;

public class TreeNode<T>
{
	private T priv;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;

	TreeNode(T priv)
	{
		this.priv = priv;
		leftChild = rightChild = null;
	}

	public TreeNode(T priv, TreeNode<T> leftChild, TreeNode<T> rightChild)
	{
		this.priv = priv;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public TreeNode<T> getLeftChild()
	{
		return leftChild;
	}

	public void setLeftChild(TreeNode<T> leftChild)
	{
		this.leftChild = leftChild;
	}

	public TreeNode<T> getRightChild()
	{
		return rightChild;
	}

	public void setRightChild(TreeNode<T> rightChild)
	{
		this.rightChild = rightChild;
	}
}
