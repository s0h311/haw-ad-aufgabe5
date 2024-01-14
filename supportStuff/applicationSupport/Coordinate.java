package supportStuff.applicationSupport;


import java.io.Serializable;


/**
 * Task: For information see ReadMe.txt resp. task
 *
 * @author Michael Schaefers ([UTF-8]:"Michael Schäfers");  P2@Hamburg-UAS.eu
 * @version 1__0001000__2021_11_11__001
 */
public class Coordinate implements Cloneable, Serializable {
  final private int x;

  public int getX() {
    return x;
  }

  final private int y;

  public int getY() {
    return y;
  }


  public Coordinate(final int x, final int y) {
    this.x = x;
    this.y = y;
  }//constructor()

  @Override
  public Object clone() {
    return new Coordinate(x, y);
  }//method()

  @Override
  public boolean equals(final Object otherObject) {
    if (otherObject == null) return false;

    if (otherObject instanceof Coordinate otherCoordinate) {
      return this.x == otherCoordinate.x && this.y == otherCoordinate.y;
    }

    return false;
  }//method()

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }//method()


  @Override
  public String toString() {
    return String.format(
        "(x:%02d,y:%02d)",
        x,
        y
    );//String.format()
  }//method()

}//class
