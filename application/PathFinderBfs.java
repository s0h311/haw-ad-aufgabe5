package application;

import supportStuff.applicationSupport.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathFinderBfs implements PathFinder_I {
  private final int[][] maze;
  private final Coordinate start;
  private final Coordinate destination;
  private final List<Coordinate> shortestPath;
  private final Queue<Coordinate> queue;
  private int shortestPathLength;

  public PathFinderBfs(final Maze maze) {
    this.maze = maze.getMazeField();
    this.start = maze.getStart();
    this.destination = maze.getDestination();
    this.shortestPath = new ArrayList<>();
    this.queue = new LinkedList<>();
    this.shortestPathLength = Integer.MAX_VALUE;
  }

  @Override
  public List<Coordinate> getShortestPath() {
    maze[start.getX()][start.getY()] = 1;
    queue.add(start);

    while (!queue.isEmpty()) {
      final Coordinate currentField = queue.remove();

      if (currentField.equals(destination)) {
        this.updateShortestPath();
      }

      // The current cost is less than the length of shortest path
      else if (maze[currentField.getX()][currentField.getY()] < shortestPathLength) {
        this.addNextFieldToStack(currentField);
      }
    }

    return shortestPath.isEmpty() ? null : shortestPath;
  }

  private void updateShortestPath() {
    shortestPath.clear();

    int cost = maze[destination.getX()][destination.getY()];
    Coordinate field = destination;
    shortestPath.add(destination);

    // Backtracking
    while (!field.equals(start)) {
      final int currentX = field.getX();
      final int currentY = field.getY();

      // top
      if (currentY > 0 && maze[currentX][currentY - 1] == cost - 1) {
        field = new Coordinate(currentX, currentY - 1);
        shortestPath.add(0, field);
        cost--;
      }

      // right
      else if (currentX < maze.length - 1 && maze[currentX + 1][currentY] == cost - 1) {
        field = new Coordinate(currentX + 1, currentY);
        shortestPath.add(0, field);
        cost--;
      }

      // bottom
      else if (currentY < maze[0].length - 1 && maze[currentX][currentY + 1] == cost - 1) {
        field = new Coordinate(currentX, currentY + 1);
        shortestPath.add(0, field);
        cost--;
      }

      // left
      else if (currentX > 0 && maze[currentX - 1][currentY] == cost - 1) {
        field = new Coordinate(currentX - 1, currentY);
        shortestPath.add(0, field);
        cost--;
      }
    }
    shortestPathLength = shortestPath.size();
  }

  private void addNextFieldToStack(final Coordinate field) {
    final int currentX = field.getX();
    final int currentY = field.getY();

    final int minCostOfNextField = maze[currentX][currentY] + 1;

    // top
    if (currentY != 0 && minCostOfNextField < maze[currentX][currentY - 1]) {
      maze[currentX][currentY - 1] = minCostOfNextField;
      queue.add(new Coordinate(currentX, currentY - 1));
    }

    // right
    if (currentX != maze.length - 1 && minCostOfNextField < maze[currentX + 1][currentY]) {
      maze[currentX + 1][currentY] = minCostOfNextField;
      queue.add(new Coordinate(currentX + 1, currentY));
    }

    // bottom
    if (currentY != maze[0].length - 1 && minCostOfNextField < maze[currentX][currentY + 1]) {
      maze[currentX][currentY + 1] = minCostOfNextField;
      queue.add(new Coordinate(currentX, currentY + 1));
    }

    // left
    if (currentX != 0 && minCostOfNextField < maze[currentX - 1][currentY]) {
      maze[currentX - 1][currentY] = minCostOfNextField;
      queue.add(new Coordinate(currentX - 1, currentY));
    }
  }
}
