object solution12 {
  def main(args: Array[String]): Unit = {
    val inputString = scala.io.StdIn.readLine
    println("input : " + inputString)

    println("solution1 : " + solution1(inputString))
    println("solution2 : " + solution2(inputString))
  }

  def solution1(inputString: String) = {
    inputString.split(" ").reverse.mkString(" ")
  }

  def solution2(inputString: String): String = {
    val result = for (s <- inputString.split(" ").reverse)
      yield s.reverse
    result.mkString(" ")
  }
}

object solution3 {
  def main(args: Array[String]) {
    val a = List("A", "B", "C", "D")
    val b = List("C", "D", "E", "F")

    var map = scala.collection.mutable.Map.empty[String, Int]
    for(s <- a) {
      map(s) = 1
    }
    for(s <- b) {
      if(map.contains(s))
        map(s) += 1;
    }

    println(map.filter(_._2 > 1).keys.mkString(" "))


  }
}
