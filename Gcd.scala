object gcd {
  def main(args: Array[String]): Unit = {
    // 인자를 받는다
    val input = readLine()
    val array = input.split(",")
    println(gcd(array(0).toInt, array(1).toInt))
  }

  def gcd(n: Int, n2: Int): Int = {
    println(s"$n $n2")
    if (n2 == 0) {
      n
    }
    else {
      gcd(n2, n % n2)
    }
  }
}
