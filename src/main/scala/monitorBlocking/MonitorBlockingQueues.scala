package monitorBlocking
import instrumentation.monitors.Monitor
import scala.collection.mutable._

/*
 * One example use of monitor locks is in implementing Monitor Blocking Queue.
 * A Monitor Blocking Queue is a queue that blocks when you try to take from it
 * and the queue is empty, or if you try to put items to it and the queue is
 * already full. A thread trying to take from an empty queue is blocked until
 * some other thread inserts an item into the queue. A thread trying to put an
 * item in a full queue is blocked until some other thread makes space in the queue.
 *
 * Task: In this exercise, we implement a simple thread-safe Blocking Monitor
 * Queue with take() and put() methods.
 *
 * Hint: Look into and use synchronized, notifyAll() and wait(). Read:
 * https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html
 *
 */

/* Use synchronized, wait and notifyAll to synchronize the operations. */
class MonitorBlockingQueue[E](capacity: Int) extends Monitor {
  if (capacity <= 0) {
    throw new IllegalArgumentException("capacity must be positive")
  }

  /* Holds the elements of this BlockingQueue. */
  val queue = Queue[E]()

  def put(e: E): Unit = synchronized {
    queue.enqueue(e)
    notifyAll()
  }

  def take(): E = {
    var x: E = null.asInstanceOf[E]
    var run = true
    while (run) {
      if (this.synchronized {queue.size <= 0})
        wait()
      this.synchronized {
        if (queue.size > 0) {
          x = queue.dequeue()
          run = false
          return x
        }
      }
    }
    x
  }
}
