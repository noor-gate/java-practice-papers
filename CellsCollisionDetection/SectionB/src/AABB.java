/**
 * Axis-Aligned Bounding Box. A region can be constructed
 * from two given points.
 */
public class AABB {

  private double left, right, top, bottom;

  public AABB(Point2D p1, Point2D p2) {
    left = Math.min(p1.x, p2.x);  // with smallest of the two x coordinates
    right = Math.max(p1.x, p2.x); // the biggest of the two x coordinates
    top = Math.max(p1.y, p2.y);   // the  biggest of the two y coordinates
    bottom = Math.min(p1.y, p2.y); // the smallest of the two y coordinates
  }

  /*
   * Returns the width of the region
   *
   */
  public double width() {
    return right - left;
  }

  /*
   * Returns the height of the region
   */
  public double height() {
    return top - bottom;
  }

  /*
   * Returns the value of the left attribute.
   */
  public double left() {
    return left;
  }

  /*
   * Returns the value of the right attribute.
   */
  public double right() {
    return right;
  }

  /*
   * Returns the value of the top attribute.
   */
  public double top() {
    return top;
  }

  /*
   * Returns the value of the bottom attribute.
   */
  public double bottom() {
    return bottom;
  }

  /*
   * Returns the coordinates of the center of a region.
   */
  public Point2D center() {
    return new Point2D((right + left) / 2, (top + bottom) / 2);
  }

  /*
   * Returns the coordinates of top left vertex of a region.
   */
  public Point2D topLeft() {
    return new Point2D(left, top);
  }

  /*
   * Returns the coordinates of bottom right vertex of a region.
   */
  public Point2D bottomRight() {
    return new Point2D(right, bottom);
  }

  /*
   * Returns the coordinates of top right vertex of a region.
   */
  public Point2D topRight() {
    return new Point2D(right, top);
  }

  /*
   * Returns the coordinates of bottom left vertex of a region.
   */
  public Point2D bottomLeft() {
    return new Point2D(left, bottom);
  }

  /*
   * Returns true if and only if the given point is in the region.
   * @param p
   *		the given point to look for in the region
   */
  public boolean covers(Point2D p) {
    return p.x >= left && p.x <= right && p.y >= bottom && p.y <= top;
  }
}
