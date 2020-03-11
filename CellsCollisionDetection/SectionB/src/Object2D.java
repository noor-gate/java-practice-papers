public class Object2D implements Comparable<Object2D> {

  private Point2D center;  //it includes the coordinates of the 2D-object
  private double size;     //size of the 2D-object, used as key in the PriorityQueue

  public Object2D(double x, double y, double s) {
    center = new Point2D(x, y);
    size = s;
  }


  public boolean equals(Object obj) {
    if (!(obj instanceof Object2D)) {
      return false;
    } else {
      Object2D o = (Object2D) obj;
      return o.center == o.center && o.size == size;
    }
  }


  public int compareTo(Object2D o) {
    if (size < o.size) {
      return -1;
    } else if (size > o.size) {
      return 1;
    } else {
      return 0;
    }
  }

  public Point2D getCenter() {
    return center;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double s) {
    size = s;
  }
}
