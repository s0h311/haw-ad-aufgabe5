package application;

import supportStuff.applicationSupport.Coordinate;

import java.util.Stack;

public class PathFinderDfs {
  private Stack<Coordinate> shortestPath = null;

  public Stack<Coordinate> findShortestPath(int[][] maze, Coordinate start, Coordinate destination) {
    int width = maze.length;
    int height = maze[0].length;
    boolean[][] visited = new boolean[width][height];
    Stack<Coordinate> path = new Stack<>();

    path.push(start);
    dfs(maze, start, destination, visited, path);
    return shortestPath; // Return the shortest path found
  }

  private void dfs(
      int[][] maze,
      Coordinate currentField,
      Coordinate destination,
      boolean[][] visited,
      Stack<Coordinate> path
  ) {
    if (currentField.getX() == destination.getX() && currentField.getY() == destination.getY()) {
      if (shortestPath == null || path.size() < shortestPath.size()) {
        shortestPath = (Stack<Coordinate>) path.clone();
      }
      return;
    }

    int[][] possibleDirections = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    // oben, rechts, unten, links

    for (int[] direction : possibleDirections) {
      int newX = currentField.getX() + direction[0];
      int newY = currentField.getY() + direction[1];
      Coordinate nextField = new Coordinate(newX, newY);

      if (isValid(maze, new Coordinate(newX, newY)) && !visited[newX][newY]) {
        path.push(nextField);
        visited[newX][newY] = true;
        dfs(maze, nextField, destination, visited, path);
        visited[newX][newY] = false;
        path.pop();
      }
    }
  }

  private boolean isValid(int[][] maze, Coordinate coord) {
    return coord.getX() >= 0 && coord.getX() < maze.length &&
        coord.getY() >= 0 && coord.getY() < maze[0].length &&
        maze[coord.getX()][coord.getY()] != Integer.MIN_VALUE;
  }

}
