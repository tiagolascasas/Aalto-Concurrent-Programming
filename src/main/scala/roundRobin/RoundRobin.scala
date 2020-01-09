package roundRobin
import akka.actor._

/* In this exercise, we implement an actor called RoundRobin declared as  
class RoundRobin[A <: Actor : scala.reflect.ClassTag](numChildren: Int) extends Actor. 
This actor should be able to create a number of children (numChildren) and enqueue them 
in an internal queue called children. For each message it receives, the RoundRobin actor 
should forward the message to the child found at the head of the children queue and 
move (enqueue) that child to the tail of the queue. The exercise teaches about 
creating children and forwarding messages.
*/

class RoundRobin[A <: Actor: scala.reflect.ClassTag](numChildren: Int) extends Actor {
  if (numChildren <= 0)
    throw new IllegalArgumentException("numChildren must be positive")
  
  var children = collection.immutable.Queue[ActorRef]()
  var last: Any = null.asInstanceOf[Any]

  override def preStart = {
    for (i <- 1 to numChildren) {
      var c = context.actorOf(Props[A], "Child_" + i);
      children = children.enqueue(c)
    }

    for (i <- 0 until numChildren) {
      self ! i
    }
  }

  def receive = {
    case _ => print("")
  }
}
