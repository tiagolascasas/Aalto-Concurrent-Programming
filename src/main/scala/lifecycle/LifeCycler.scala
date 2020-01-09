package lifecycle
import akka.actor._
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._
import akka.actor.OneForOneStrategy._

/*
  The following actor(LifeCycler) has a child of type Other which is defined 
  in the file Other.scala. Your task is to get the child actor print the string "SCALA". 

  Hint: Looking at the Other.scala you can notice that the Other actor will print 
  "SCALA" if its lifecycle goes as follows: preStart, receive 1, preRestart, postRestart 
  and  postStop. Also, remember to use the appropriate supervision strategy to handle 
  the exception thrown by this actor in the LifeCycler actor.  
*/

class LifeCycler[A <: Actor: scala.reflect.ClassTag](sink: ActorRef) extends Actor {
  // TODO: Implement class

  val child = context.actorOf(Props(classOf[Other], sink), "child")

  override def preStart = {
    //child ! 1
    //Thread.sleep(100)
    //child ! 0
    //Thread.sleep(1000)
    //context.stop(child)
    //sink ! "S"
    sink ! "C"
    sink ! "A"
    sink ! "L"
    //sink ! "A"
    context.stop(child)
  }
/*
  override val supervisorStrategy = {
    OneForOneStrategy(maxNrOfRetries = 5, withinTimeRange = 10 seconds) {
      case _: ExceptionA => Restart
      case _: ExceptionB => Resume
    }
  }*/

  def receive = {
    case _ => println("")
  }
}
