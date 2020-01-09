package parString
import org.scalatest._
import scala.collection.parallel._

class ParStringTest extends FlatSpec with BeforeAndAfterAll {

  override def beforeAll() {
  }

  override def afterAll() {
  }

  "ParString" should "extend the correct type(ParSeq)" in {
    val txt = "A custom txt" * 25
    val partxt = new ParString(txt)  
    assert(partxt.isInstanceOf[immutable.ParSeq[Char]])
  } 
  "ParString" should "give the correct count result" in {
    val txt = "A custom text " * 25
    val upperSeqCnt = txt.aggregate(0)((n, c) => if (Character.isUpperCase(c)) n + 1 else n, _ + _) 
    val partxt = new ParString(txt)
    val upperCnt = partxt.aggregate(0)((n, c) => if (Character.isUpperCase(c)) n + 1 else n, _ + _)
    assert(upperCnt == upperSeqCnt && partxt.isInstanceOf[immutable.ParSeq[Char]])
  }
  "ParString" should "give the correct filter result" in {
    val txt = "A custom txt" * 25
    val filteredSeqText = txt.filter(_ != ' ') 
    val partxt = new ParString(txt)
    val filteredText = partxt.filter(_ != ' ') 
    assert(filteredText.seq.toString == filteredSeqText && partxt.isInstanceOf[immutable.ParSeq[Char]]) 
  }
}