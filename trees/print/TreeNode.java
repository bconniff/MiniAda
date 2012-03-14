package trees.print;

import java.util.*;

public class TreeNode extends AbstractTreeNode {

	private int field1;
	public float field2;
	public String field3;
	protected List<AbstractTreeNode> list;

	private int[] vals = {2, 4, 5, -1, 0};

	public TreeNode() {
		list = new ArrayList<AbstractTreeNode>();
	}

	public void add(AbstractTreeNode atn) {
		list.add(atn);
	}

	public static void main(String[] args) {
		TreeNode tn = new TreeNode();
		OtherNode on = new OtherNode();
		TreeNode tn2 = new TreeNode();
		on.setNode(tn2);
		tn.add(on);
		tn.add(tn2);
		tn.add(new OtherNode());
		tn.add(new TreeNode());
		System.out.println(tn);
	}
}
