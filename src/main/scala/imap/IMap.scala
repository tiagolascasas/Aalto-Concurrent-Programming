package imap
import scala.concurrent.{Future, Promise}
import java.util.concurrent.ConcurrentHashMap

/* 
 * In the previous exercises we have seen low-level abstractions such as threads that support 
 * asynchronous computation where executions can occur independently of the main program flow.
 * However, using these facilities directly for composing asynchronous computations is cumbersome
 * and error-prone. In this exercise, we will focus on two abstractions in Scala that are specifically
 * tailored for such tasks called futures and promises. In short, a promise and a future represent
 * two aspects of a single-assignment variable. The promise allows you to assign a value to 
 * the future object, whereas the future allows you to read that value. 
 *
 * Task: In this task, we implement a single-assignment map with a twist:
 * 
 * class IMap[K, V] {
 *  def update(k: K, v: V): Unit
 *  def apply(k: K): Future[V]
 *  def whenReady[T: scala.reflect.ClassTag](func: V => T , p: Promise[T], f: Future[V])
 * }
 *
 * Pairs of keys and values can be added to the IMap object, but they can neither be
 * removed or modified. A specific key can be assigned only once, and subsequent calls to 
 * update with that same key should result in an exception. Calling apply with a specific 
 * key should return a future, which is completed after that key is inserted into the map.
 * As an additional learning twist we will also implement a whenReady function which completes a promise 
 * p using a function func (i.e. p success func(v)) when the future f is completed with a value v (use a callback 
 * to check f's completion).       
 *
 * Hint: Use futures and promises. Consult lecture 4, https://docs.scala-lang.org/overviews/core/futures.html, 
 * and chapter 4 of the book: Learning concurrent programming in Scala by Aleksandar Prokopec.
*/
/*
class IMap[K, V] {
  import scala.collection.convert.decorateAsScala._
  private val m = new ConcurrentHashMap[K, Promise[V]]().asScala

  def update(k: K, v: V): Unit = {
    val p = Promise[V]
    if (m.putIfAbsent(k, p) != null) {
      throw new Exception("Cannot update value of pre-existing key")
    }
  }

  def apply(k: K): Future[V] = {
    val f: Future[V] = Future {
      m.get(k)
    }
    f
  }

  import scala.concurrent.ExecutionContext.Implicits.global
  def whenReady[T: scala.reflect.ClassTag](func: V => T , p: Promise[T], f: Future[V]) =  {

    f onComplete {
        p success func(v)
    }
  }
}*/
