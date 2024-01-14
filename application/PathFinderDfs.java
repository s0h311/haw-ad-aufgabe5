package application;

import supportStuff.applicationSupport.Coordinate;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PathFinderDfs implements PathFinder_I {
  private final int[][] maze;
  private final Coordinate start;
  private final Coordinate destination;
  private final List<Coordinate> shortestPath;
  private final Deque<Coordinate> stack;
  private int shortestPathLength;

  public PathFinderDfs(final Maze maze) {
    this.maze = maze.getMazeField();
    this.start = maze.getStart();
    this.destination = maze.getDestination();
    this.shortestPath = new ArrayList<>();
    this.stack = new LinkedList<>();
    this.shortestPathLength = Integer.MAX_VALUE;
  }

  @Override
  public List<Coordinate> getShortestPath() {
    maze[start.getX()][start.getY()] = 1;
    stack.push(start);

    while (!stack.isEmpty()) {
      final Coordinate currentField = stack.pop();

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

    // PRIO: TOP LEFT BOTTOM RIGHT

    // right
    if (currentX < maze.length - 1 && minCostOfNextField < maze[currentX + 1][currentY]) {
      maze[currentX + 1][currentY] = minCostOfNextField;
      stack.push(new Coordinate(currentX + 1, currentY));
    }

    // bottom
    if (currentY < maze[0].length - 1 && minCostOfNextField < maze[currentX][currentY + 1]) {
      maze[currentX][currentY + 1] = minCostOfNextField;
      stack.push(new Coordinate(currentX, currentY + 1));
    }

    // left
    if (currentX > 0 && minCostOfNextField < maze[currentX - 1][currentY]) {
      maze[currentX - 1][currentY] = minCostOfNextField;
      stack.push(new Coordinate(currentX - 1, currentY));
    }

    // top
    if (currentY > 0 && minCostOfNextField < maze[currentX][currentY - 1]) {
      maze[currentX][currentY - 1] = minCostOfNextField;
      stack.push(new Coordinate(currentX, currentY - 1));
    }
  }
}
