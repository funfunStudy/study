object Main {

  val baseArr : List[Int] = List(0,1)

  def main(args: Array[String]): Unit = {
    val num = scala.io.StdIn.readLine.toInt

    println(solve(num))
  }

//노가다
  def solve(value : Int) : Int  = value match {
    case 1 => 0
    case 2 => 1
    case _ => fibonach(baseArr, value).last

  }

  def fibonach(arr : List[Int], num : Int) : List[Int] = {

    val preNum1 = arr(arr.length-2)
    val preNum2 = arr(arr.length-1)
    val result : List[Int] = arr ::: preNum1 + preNum2 :: Nil

    println(result)

    if(result.length == num)
        result
    else
      fibonach(result, num)
  }

  //좋은 해답
  def fib1( n : Int) : Int = n match {
    case 0 | 1 => n
    case _ => fib1( n-1 ) + fib1( n-2 )
  }

  /**
   *
   * int fibo(int num) {
        if(num == 0) return 0;
        else if(num == 1) return 1;
        else return fibo(num-1) + fibo(num-2);
     }
   * /
}
