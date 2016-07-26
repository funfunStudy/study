/**
  * Created by com on 2016-07-27.
  */

object main {

  var primeList = List[Int]()

  def main(args: Array[String]) {
    val min = scala.io.StdIn.readLine().toInt
    val max = scala.io.StdIn.readLine().toInt
    println("================================")
    println(">> min : " + min)
    println(">> max : " + max)
    println(">> result : " + getPrimes((2 to max).toList, min))
  }

  def getPrimes(sus: List[Int], min: Int): List[Int] = {
    sus.filter(isPrime).filter(min < _)
  }

  def isPrime(su: Int): Boolean = su match {
    case 2 =>
      primeList = List(2)
      true
    case _ =>
      if (primeList.find(su % _ == 0).size == 1) {
        false
      } else {
        primeList = su +: primeList
        true
      }
  }
}
