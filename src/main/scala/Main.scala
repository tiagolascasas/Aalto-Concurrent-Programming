import atomicInteger.SimpleAtomicInteger
import akka.actor._
import roundRobin.RoundRobin

class Child extends Actor{
  def receive = {
     case msg =>
       sender ! msg
  }  
}

object Main extends App {
    println("Running...")
    //put code for impromptu testing here...
    val numChildren = 5
    val rr = new RoundRobin[Child](numChildren)
    val children = rr.children
    println(children.length)

    println("Finished running. Hooray, no deadlocks!")
}