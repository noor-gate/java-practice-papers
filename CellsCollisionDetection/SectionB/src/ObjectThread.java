import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ObjectThread extends Thread {

    private PriorityQueueInterface<Object2D> pq;
    private AtomicBoolean collisions;
    private ReentrantLock lock;
    private QuadTree tree;

    public ObjectThread(PriorityQueueInterface<Object2D> pq, AtomicBoolean collisions, ReentrantLock lock, QuadTree tree) {
        this.collisions = collisions;
        this.lock = lock;
        this.tree = tree;
        this.pq = pq;
    }

    @Override
    public void run() {
        while (!pq.isEmpty()) {
            lock.lock();
            Object2D nd = pq.peek();
            Point2D topLeft = new Point2D(nd.getCenter().x - nd.getSize(), nd.getCenter().y + nd.getSize());
            Point2D topRight = new Point2D(nd.getCenter().x + nd.getSize(), nd.getCenter().y + nd.getSize());
            AABB safetyRegion = new AABB(topLeft, topRight);
            if (!tree.queryRegion(safetyRegion).isEmpty()) {
                collisions.getAndSet(false);
            }
            tree.add(nd);
            pq.remove();
            lock.unlock();
        }
        collisions.getAndSet(true);
    }
}
