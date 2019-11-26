package semaphore
import instrumentation.monitors.Monitor

/*
* Semaphore is another useful tool to prevent race conditions and solve other 
* such critical section problems. A semaphore is an important abstract data type 
* used to control access to a common resource required by multiple execution 
* units(threads) in a concurrent system. Simply put, a semaphore is a variable 
* used to record how many units of a particular shared resource are available. 
* Of course, such a variable need to make sure the record is safely adjusted 
* avoiding any race conditions.
*
* Task: In this exercise, we implement a simple semaphore with acquire() 
* and release() methods. We will make use of Java Monitors to implement our 
* semaphore. 
*
* Hint:  Look into and use synchronized, notify() and wait(). Read:
* https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html
*
*/

class Semaphore(private val capacity: Int) extends Monitor {
  
  var permits = capacity
  
  def acquire(): Unit = synchronized {
    if (permits <= 0) {
      wait()
    }
    permits -= 1
  }

  def release(): Unit = synchronized {
    permits += 1
    notify()
  }

  def availablePermits(): Int = permits

}
