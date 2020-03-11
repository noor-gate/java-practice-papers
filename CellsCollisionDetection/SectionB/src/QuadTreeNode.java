/**
 * A quadtree node has a <i>list</i> storing objects, an <i>AABB</i>
 * representing the region it covers, and four children nodes: NW, NE, SE and
 * SW.
 *
 * At any time, a quadtree node either (1) has no children (i.e., all of them
 * are null), or (2) has four children (i.e., none of them is null) and an empty
 * list of stored objects.
 */
class QuadTreeNode {

  ListInterface<Object2D> values;
  AABB region;

  QuadTreeNode NW = null;
  QuadTreeNode NE = null;
  QuadTreeNode SE = null;
  QuadTreeNode SW = null;

  QuadTreeNode(AABB region) {
    this.region = region;
    values = new ListArrayBased<Object2D>();
  }

  /**
   * A quad-tree node is a leaf if and only if all of children are null.
   * However, since all the children must be created at the same time, it
   * suffices to check any of them for null
   *
   * @return true if and only if this node has no children
   */
  boolean isLeaf() {
    return NW == null;
  }

  /**
   * Creates four children of the current node, each of which has the same
   * size of region. The union of the children's region should be equal to the
   * region of the parent node.
   */
  void subdivide() {
    NW = new QuadTreeNode(new AABB(region.topLeft(), region.center()));
    NE = new QuadTreeNode(new AABB(region.center(), region.topRight()));
    SE = new QuadTreeNode(
        new AABB(region.center(), region.bottomRight()));
    SW = new QuadTreeNode(new AABB(region.bottomLeft(), region.center()));
  }
}

