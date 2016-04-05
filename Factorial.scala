object Factorial {
  def main(args: Array[String]): Unit = {
    // 인자를 받는다
    val input = Integer.parseInt(readLine())
    println("result = " + factorial(input, input))
  }

  def factorial(v: Int, sum: Int): Int = {
    println("v : " + v + ", sum : " + sum)
    if (v > 1) {
      factorial(v - 1, sum * (v - 1));
    } else {
      sum
    }
  }
}
