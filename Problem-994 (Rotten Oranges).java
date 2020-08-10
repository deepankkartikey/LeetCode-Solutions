// 994. Rotting Oranges

// In a given grid, each cell can have one of three values:
// the value 0 representing an empty cell;
// the value 1 representing a fresh orange;
// the value 2 representing a rotten orange.
// Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

// Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

// Example 1:
// Input: [[2,1,1],[1,1,0],[0,1,1]]
// Output: 4

// Example 2:
// Input: [[2,1,1],[0,1,1],[1,0,1]]
// Output: -1
// Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

// Example 3:
// Input: [[0,2]]
// Output: 0
// Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

// Note:
// 1 <= grid.length <= 10
// 1 <= grid[0].length <= 10
// grid[i][j] is only 0, 1, or 2.


// Time Complexity: O(N)
// Space Complexity: O(N)

class Solution {
	public int orangesRotting(int[][] grid) {

		Set<String> fresh = new HashSet();
		Set<String> rotten = new HashSet();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++)
				if (grid[i][j] == 1)
					fresh.add("" + i + j);
				else if (grid[i][j] == 2)
					rotten.add("" + i + j);
		}

		int minutes = 0;
		int directions[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		while (fresh.size() > 0) {
			Set<String> infected = new HashSet();
			for (String s : rotten) {
				int i = s.charAt(0) - '0';
				int j = s.charAt(1) - '0';
				for (int direction[] : directions) {
					int nextI = direction[0] + i;
					int nextJ = direction[1] + j;
					String nextOrange = "" + nextI + nextJ;
					if (fresh.contains(nextOrange)) {
						fresh.remove(nextOrange);
						infected.add(nextOrange);
					}
				}
			}
			if (infected.size() == 0)
				return -1;
			rotten = infected;
			minutes++;
		}
		return minutes;
	}
}