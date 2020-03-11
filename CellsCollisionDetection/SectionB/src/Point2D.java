public class Point2D {

  public double x;
  public double y;

  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor.
   */
  public Point2D(Point2D p) {
    x = p.x;
    y = p.y;
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof Point2D)) {
      return false;
    } else {
      Point2D p = (Point2D) obj;
      return p.x == x && p.y == y;
    }
  }
}
