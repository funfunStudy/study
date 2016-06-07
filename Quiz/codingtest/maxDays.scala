import scala.annotation.tailrec

object Main {

  val days = List(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  val weeks = List("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

  def main(args: Array[String]): Unit = {
    val num = scala.io.StdIn.readLine.toInt
    for (i <- 0 until num) {
      val value = scala.io.StdIn.readLine
      solve(value)
    }
  }

  def solve(value : String): Unit ={
    val values = value.split(" ")
    val m = values(0).toInt
    val d = values(1).toInt
    val w = values(2)
    println(parse(m, d, w))
  }

  def parse(month : Int, day : Int, week : String) : String = {
    val preMaxDay = days(if(month-2 < 0) 11 else  month-2)
    val maxDay = days(month-1)
    val pos = weeks.indexOf(week)
    val result1 = leftSolve(List(), day-1, pos-1, preMaxDay)
    val result2 = rightSolve(List(), day+1, pos+1, maxDay)

    val result = result1 ::: (day :: result2.reverse)

    result.mkString(" ")
  }

  @tailrec
  def leftSolve(result : List[Int], curr : Int, pos : Int, preMax : Int) : List[Int]= {
    pos match {
      case -1 => result
      case _ =>
        val value = if(curr <= 0) preMax else curr
        leftSolve(value :: result , value -1, pos -1, preMax)
    }
  }

  @tailrec
  def rightSolve(result : List[Int], currDay : Int, pos : Int, max : Int) : List[Int]= {
    pos match {
      case 7 => result
      case _ =>
        val value = if(currDay > max)  1 else currDay
        rightSolve(value :: result , value +1, pos +1, max)
    }
}


}
