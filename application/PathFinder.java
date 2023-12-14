package application;


import supportStuff.applicationSupport.Coordinate;

import java.io.Serializable;
import java.util.*;

/**
 * Task: For information see ReadMe.txt resp. task
 * This is just a demo. It shows an easy way to support multiple alternatives.
 *
 * @author Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 * Px@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class PathFinder implements PathFinder_I, Serializable {
  private final List<Coordinate> shortestPath;

  public PathFinder(final Maze maze) {
    PathFinderBfs pathFinderBfs = new PathFinderBfs();
    // shortestPath = pathFinderBfs.findShortestPath(maze.getMazeField(), maze.getStart(), maze.getDestination());
    PathFinderDfs pathFinderDfs = new PathFinderDfs();
    shortestPath = pathFinderDfs.findShortestPath(maze.getMazeField(), maze.getStart(), maze.getDestination());
  }//constructor()

  @Override
  public List<Coordinate> getShortestPath() {
    return shortestPath;
  }//method()

  private List<Coordinate> bfs(int[][] maze, Coordinate start, Coordinate destination) { // breadth-first search
    boolean[][] visited = new boolean[maze.length][maze[0].length];
    List<Coordinate> path = new ArrayList<>();
    path.add(start);
    visited[start.getX()][start.getY()] = true;

    while (!path.isEmpty()) {
      Coordinate currentField = path.get(path.size() - 1);

      if (currentField.getX() == destination.getX() && currentField.getY() == destination.getY()) {
        return path.stream().toList();
      }

      int[][] possibleDirections = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

      for (int[] direction : possibleDirections) {
        int newX = currentField.getX() + direction[0];
        int newY = currentField.getY() + direction[1];

        if (isValidField(maze, newX, newY) && !visited[newX][newY]) {
          path.add(new Coordinate(newX, newY));
          visited[newX][newY] = true;
        }
      }
    }
    return null;
  }


  int[][] visited;
  int[][] maze;
  Coordinate destination;
  List<Coordinate> path;
  Map<Integer, List<Coordinate>> paths = new HashMap<>();

  private List<Coordinate> dfs(int[][] maze, Coordinate start, Coordinate destination) { // depth-first search
    this.visited = new int[maze.length][maze[0].length];
    this.maze = maze;
    this.destination = destination;
    this.path = new ArrayList<>();

    if (!solveDfs(start)) return null;

    Collections.reverse(path);

    return path;
  }

  private boolean solveDfs(Coordinate currentStep) {
    if (currentStep.equals(destination)) {
      path.add(destination);

      paths.put(path.size(), path);

      return true;
    }

    int currentX = currentStep.getX();
    int currentY = currentStep.getY();

    if (visited[currentX][currentY] == 1) return false;
    if (maze[currentX][currentY] == Integer.MIN_VALUE) return false;

    visited[currentX][currentY] = 1;

    if (currentX > 0) {
      if (solveDfs(new Coordinate(currentX - 1, currentY))) {
        path.add(currentStep);
        return true;
      }
    }

    if (currentX < maze.length - 1) {
      if (solveDfs(new Coordinate(currentX + 1, currentY))) {
        path.add(currentStep);
        return true;
      }
    }

    if (currentY > 0) {
      if (solveDfs(new Coordinate(currentX, currentY - 1))) {
        path.add(currentStep);
        return true;
      }
    }

    if (currentY < maze[0].length - 1) {
      if (solveDfs(new Coordinate(currentX, currentY + 1))) {
        path.add(currentStep);
        return true;
      }
    }

    return false;
  }

  private boolean isValidField(int[][] maze, int x, int y) {
    return x >= 0 &&
        y >= 0 &&
        x < maze.length &&
        y < maze[0].length &&
        maze[x][y] > Integer.MIN_VALUE;
  }
}//class
