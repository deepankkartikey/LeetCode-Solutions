// 987. Vertical Order Traversal of a Binary Tree

// Given a binary tree, return the vertical order traversal of its nodes values.
// For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

// Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
// If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

// Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

// Example 1:
// Input: [3,9,20,null,null,15,7]
// Output: [[9],[3,15],[20],[7]]
// Explanation: 
// Without loss of generality, we can assume the root node is at position (0, 0):
// Then, the node with value 9 occurs at position (-1, -1);
// The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
// The node with value 20 occurs at position (1, -1);
// The node with value 7 occurs at position (2, -2).

// Example 2:
// Input: [1,2,3,4,5,6,7]
// Output: [[4],[2],[1,5,6],[3],[7]]
// Explanation: 
// The node with value 5 and the node with value 6 have the same position according to the given scheme.
// However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 
// Note:
// The tree will have between 1 and 1000 nodes.
// Each node's value will be between 0 and 1000.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 
 
// Time Complexity: O(N)
// Space Complexity: O(N)
class Solution {
	class Point {
		int x;
		int y;
		int val;

		public Point(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

		public List<List<Integer>> verticalTraversal(TreeNode root) {
			if (root == null)
				return new ArrayList();

			List<Point> list = new ArrayList();
			vtUtil(root, 0, 0, list);
			Collections.sort(list, (p1, p2) -> p1.x == p2.x ? p1.y == p2.y ? p1.val - p2.val : p2.y - p1.y : p1.x - p2.x);
			Map<Integer, List<Integer>> map = new TreeMap();
			for (Point p : list) {
				List<Integer> line = map.getOrDefault(p.x, new ArrayList());
				line.add(p.val);
				map.put(p.x, line);
			}
			List<List<Integer>> result = new ArrayList();
			for (List<Integer> l : map.values())
				result.add(l);
			return result;
		}

		private void vtUtil(TreeNode root, int x, int y, List<Point> list) {
			if (root == null)
				return;
			list.add(new Point(x, y, root.val));
			vtUtil(root.left, x - 1, y - 1, list);
			vtUtil(root.right, x + 1, y - 1, list);
		}
}