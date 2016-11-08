import scala.annotation.tailrec

object Main {

  def main(args: Array[String]) {
    //    val line = Source.fromFile("files/A-large-practice.in").getLines
    val inputNum = scala.io.StdIn.readLine().toInt

    for (i <- 0 until inputNum) {
      val num = scala.io.StdIn.readLine().toInt
      val numList = createNumList()
      println(solution(numList.sorted, 0))
    }
  }

  def createNumList(): List[Int] = {
    scala.io.StdIn.readLine().split(" ")
      .toList
      .map(_.toInt)
  }

  @tailrec
  def solution(numList: List[Int], accm: Int): Int = numList match {
    case head :: List() => {
      accm
    }
    case head :: tail => {
      val newList = head + tail.head :: tail.tail
      solution(newList.sorted, accm + head + tail.head)
    }
  }

}
