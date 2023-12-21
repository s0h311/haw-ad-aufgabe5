package application;


import supportStuff.applicationSupport.Coordinate;

import java.io.Serializable;
import java.util.List;

/**
 * Task: For information see ReadMe.txt resp. task
 * This is just a demo. It shows an easy way to support multiple alternatives.
 *
 * @author Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 * Px@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class PathFinder implements PathFinder_I, Serializable {

  private final Maze maze;

  public PathFinder(final Maze maze) {
    this.maze = maze;
  }

  @Override
  public List<Coordinate> getShortestPath() {
    int alternative = 2;

    return switch (alternative) {
      case 1 -> new PathFinderBfs(maze).getShortestPath();
      default -> new PathFinderDfs(maze).getShortestPath();
    };
  }
}
