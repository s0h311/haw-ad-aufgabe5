package application;

import supportStuff.applicationSupport.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathFinderBfs {
  private int[][] maze;
  private boolean[][] visited;

  public List<Coordinate> findShortestPath(int[][] maze, Coordinate start, Coordinate end) {
    this.maze = maze;
    this.visited = new boolean[maze.length][maze[0].length];

    Queue<List<Coordinate>> queue = new LinkedList<>();
    queue.add(List.of(start));

    while (!queue.isEmpty()) {
      List<Coordinate> path = queue.remove();
      Coordinate currentField = path.get(path.size() - 1);

      if (currentField.getX() == end.getX() && currentField.getY() == end.getY()) {
        return path;
      }

      int[][] possibleDirections = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
      // oben, rechts, unten, links

      for (int[] direction : possibleDirections) {
        Coordinate nextField = new Coordinate(currentField.getX() + direction[0], currentField.getY() + direction[1]);

        if (isValid(nextField) && !visited[nextField.getX()][nextField.getY()]) {
          List<Coordinate> newPath = new ArrayList<>(path);
          newPath.add(nextField);
          queue.add(newPath);
          visited[nextField.getX()][nextField.getY()] = true;
        }
      }
    }

    return null;
  }

  private boolean isValid(Coordinate coord) {
    return coord.getX() >= 0 && coord.getX() < maze.length &&
        coord.getY() >= 0 && coord.getY() < maze[0].length &&
        maze[coord.getX()][coord.getY()] != Integer.MIN_VALUE;
  }
}
