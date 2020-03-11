import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * You must implement the <code>checkObjects</code> method.
 */

public class CollisionDetection {

  public static void main(String[] args) throws Exception {
    String inputFile = args[0];

    // add and sort the 2D-objects according to the size in ascending order
    PriorityQueueInterface<Object2D> sortedPoints = new PriorityQueue<Object2D>();
    AABB region = readAndSortObjects(inputFile, sortedPoints);

    boolean collisionFree = checkObjects(sortedPoints, region);
    if (collisionFree) {
      System.out.println("Collision-free.");
    } else {
      System.out.println("Collision detected!");
    }
  }


  /**
   * <p> Implement this method for Question 4 </p>
   *
   * // collision detection:
   * // We create a quadTree.
   * // We try to add all the 2D-objects to the quadTree.
   * // At each step, we pick a 2D-object:
   * // 1. we get its safety region (i.e., a square centred at the current point and
   * // with width equals to twice of the point's size)
   * // 2. by querying the quadTree, we try to find if there is any existing 2D-object
   * // within the current point's safety region
   * //   a. if there is none, then we add the point to the quad tree
   * //   b. otherwise, a collision is detected and we halt and return false.
   * // 3. if we successfully add all the points to the quad-tree, then we are sure
   * // that there is no collision between the points, and we halt and return
   * // true.
   */
  private static boolean checkObjects(
      PriorityQueueInterface<Object2D> sortedPoints, AABB region) {
    QuadTree tree = new QuadTree(region, 4);
    while (!sortedPoints.isEmpty()) {
      Object2D nd = sortedPoints.peek();
      Point2D topLeft = new Point2D(nd.getCenter().x - nd.getSize(), nd.getCenter().y + nd.getSize());
      Point2D topRight = new Point2D(nd.getCenter().x + nd.getSize(), nd.getCenter().y + nd.getSize());
      AABB safetyRegion = new AABB(topLeft, topRight);
      if (!tree.queryRegion(safetyRegion).isEmpty()) {
        return false;
      }
      tree.add(nd);
      sortedPoints.remove();
    }
    return true;
  }


  /**
   * Reads 2D-Objects from a given input file and sort them in ascending order
   * with respect to their size using a PrioriyQueue
   */
  private static AABB readAndSortObjects(String inputFile,
      PriorityQueueInterface<Object2D> sortedPoints)
      throws FileNotFoundException, Exception, PQException {
    Scanner in = new Scanner(new File(inputFile));
    double minX, maxX, minY, maxY;
    minX = minY = Double.MAX_VALUE;
    maxX = maxY = Double.MIN_VALUE;
    while (in.hasNext()) {
      String[] line = in.nextLine().trim().split(",");
      if (line.length < 3) {
        in.close();
        throw new Exception(
            "Each point should have x-y coordinates and a size.");
      } else {
        double x = Double.parseDouble(line[0]);
        double y = Double.parseDouble(line[1]);
        double w = Double.parseDouble(line[2]);
        sortedPoints.add(new Object2D(x, y, w));
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
      }
    }
    in.close();
    return new AABB(new Point2D(minX, minY), new Point2D(maxX, maxY));
  }
}
