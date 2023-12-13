package application;


import java.io.Serializable;
import java.util.*;

import supportStuff.applicationSupport.Coordinate;

/**
 * Task: For information see ReadMe.txt resp. task
 * This is just a demo. It shows an easy way to support multiple alternatives.
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu 
 */
public class PathFinder implements PathFinder_I, Serializable {
    private final List<Coordinate> shortestPath;

    public PathFinder(final Maze maze){
        // shortestPath = bfs(maze.getMazeField(), maze.getStart(), maze.getDestination());
        shortestPath = dfs(maze.getMazeField(), maze.getStart(), maze.getDestination());
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

            int[][] possibleDirections = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

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

    private List<Coordinate> dfs(int[][] maze, Coordinate start, Coordinate destination) { // depth-first search
        List<Coordinate> path = new ArrayList<>();

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Stack<Coordinate> stack = new Stack<>();
        stack.push(start);

        int[][] possibleDirections = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // unten, rechts, oben, links
        // Eine Koordinate ist immer 0. Denn diagonale Bewegungen sind unerlaubt.

        int i = 0;

        while (!stack.isEmpty()) {
            Coordinate currentField = stack.pop();
            visited[currentField.getY()][currentField.getY()] = true;

            System.err.println("\n" + currentField);
            i++;

            if (i == 20) System.exit(0);

            for (int[] direction : possibleDirections) {
                int newX = currentField.getX() + direction[0];
                int newY = currentField.getY() + direction[1];

                if (isValidField(maze, newX, newY) && !visited[newX][newY]) {
                    stack.push(new Coordinate(newX, newY));
                    path.add(new Coordinate(newX, newY));

                    if (newX == destination.getX() && newY == destination.getY()) {
                        return path;
                    }
                }
            }
        }

        return null;
    }

    public static boolean isValidField(int[][] maze, int x, int y) {
        return x >= 0 &&
            y >= 0 &&
            x < maze.length &&
            y < maze[0].length &&
            maze[x][y] > Integer.MIN_VALUE;
    }
}//class
