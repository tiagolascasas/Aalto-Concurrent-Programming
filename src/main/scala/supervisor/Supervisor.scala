package supervisor

import akka.actor._
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._

/* 
  Implement the missing parts of the actor Supervisor below that 
  creates a child of type A called child, implements a sane 
  supervisor strategy and terminates if the child actor terminates. 
*/

/** An exception thrown by the child for a temporary problem. */
class TransientException extends Exception {}
/** An exception thrown by the child to indicate a corrupted state (serious problem). */
class CorruptedException extends Exception {}

class Supervisor[A <: Actor: scala.reflect.ClassTag] extends Actor {
  // TODO: Implement class

  // TODO: Create a child actor of type A called child
  val child: ActorRef = context.actorOf(Props[A], "child"); 

  // TODO: Start watching the child in the preStart 
  override def preStart = {
    context.watch(child)
  }

  // TODO: Implement a sane supervisor strategy.
  // Resume if child throws TransientException (is having a temporary problem)
  // Restart if child throws CorruptedException (is having a serious problem) 
  override val supervisorStrategy = {
    OneForOneStrategy(maxNrOfRetries = 5, withinTimeRange = 10 seconds) {
      case _: CorruptedException => Restart
      case _: TransientException => Resume
    }
  }


  // TODO: Terminate if child terminates 
  def receive = {
    case "restarted" => context.stop(self)
  }

}
