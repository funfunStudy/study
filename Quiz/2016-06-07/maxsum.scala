object Main {

  def getMaxSum(maxSum : Int, currentSum : Int, input: List[Int]): Int = input match {
    case x :: xs => if( currentSum + x > 0 ) getMaxSum(Math.max(maxSum, currentSum + x), currentSum + x, xs) else getMaxSum(maxSum, 0, xs)
    case Nil => maxSum

  }

  def main(args: Array[String]) {

    val num = scala.io.StdIn.readLine().toInt
    for (i <- 0 until num) {
      val value = scala.io.StdIn.readLine
      val input = scala.io.StdIn.readLine.split(" ").map(_.toInt).toList
      println(getMaxSum(0, 0, input))
    }
  }
}
