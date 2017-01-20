object Main {
  def main(args: Array[String]): Unit = {
    val maxPower = scala.io.StdIn.readLine.toInt
    val powers = scala.io.StdIn.readLine.split(" ")

    println(checkPower(maxPower, powers))
  }

  def checkPower(maxPower: Int, powers: Array[String]): Boolean = {
//    var sum = 0
//    for (i <- powers) {
//      sum += i.toInt
//    }
    maxPower > powers.foldLeft(0)((m: Int, n: String) => m + n.toInt)
  }
}
