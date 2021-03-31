/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

The order of returned grid coordinates does not matter.
Both m and n are less than 150.
 

Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 

*/
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0 ||
            matrix == null) return new ArrayList<List<Integer>>();

        boolean[][] pacif = new boolean[matrix.length][matrix[0].length];
        boolean[][] atlan = new boolean[matrix.length][matrix[0].length];
        List<List<Integer>> lst = new ArrayList<List<Integer>>();

        for (int row = 0; row < matrix.length; row++) {
            DFS(matrix, pacif, 0, row, 0);
            DFS(matrix, atlan, 0, row, matrix[0].length - 1);
        }
        for (int col = 0; col < matrix[0].length; col++) {
            DFS(matrix, pacif, 0, 0, col);
            DFS(matrix, atlan, 0, matrix.length - 1, col);
        }
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (pacif[row][col] && atlan[row][col]) {
                    lst.add(new ArrayList<Integer>()); // Last list
                    lst.get(lst.size() - 1).add(row); // Get last list
                    lst.get(lst.size() - 1).add(col); // Get last list
                }
            }
        }

        return lst;
    }

    public void DFS(int[][] grid, boolean[][] visited, int limit, int row, int col) {
        if (row >= grid.length    || row < 0 ||
            col >= grid[0].length || col < 0 ||
            visited[row][col] || grid[row][col] < limit) { // Limit must be less than curr value
            return;
        }

        visited[row][col] = true;
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for (int[] d : directions) {
            DFS(grid, visited, grid[row][col], row + d[0], col + d[1]);
        }
    }
}