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
  private final List<Coordinate> shortestPath;

  public PathFinder(final Maze maze) {
    int alternative = 2;

    switch (alternative) {
      case 1:
        this.shortestPath = new PathFinderBfs(maze).getShortestPath();
        break;
      default:
        this.shortestPath = new PathFinderDfs(maze).getShortestPath();
        break;
    }
  }

  @Override
  public List<Coordinate> getShortestPath() {
    return shortestPath;
  }
}
