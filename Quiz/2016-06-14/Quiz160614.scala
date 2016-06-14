import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * Created by hyungsoklee on 2016. 6. 14..
  */
object Quiz160614 {

  def smallSolution(count: Int, inputList: Array[String]): Unit = {
    if(inputList.length < 4)
      return;

    val su1 = inputList(0)
    val symbol = inputList(1)
    val su2 = inputList(2)
    val resultSymbol = inputList(3)
    val result = inputList(4)

    val su1Array = su1.toCharArray.reverse
    val su2Array = su2.toCharArray.reverse
    val isPlus = "+".equals(symbol)
    val resultArray = result.toCharArray.reverse

    var listResult = List()
    val index = if(su1Array.length < su2Array.length) su1Array.length else su2Array.length
    for(i <- 0 to index) {
      // ? 3개
      if("?".equals(su1Array(i)) && "?".equals(su2Array(i)) && "?".equals(resultArray(i))) {
      } else

      if("?".equals(su1Array(i)) || "?".equals(su2Array(i))) {

      } else if("?".equals(su1Array(i)) && !"?".equals(su2Array(i))) {

      }
    }

    println("Case #" + count + ": " + su1 + symbol + su2 + resultSymbol + result)
  }

  def main(args: Array[String]) {
    val filename = "files/B-small-practice.in"
    val line = Source.fromFile(filename).getLines.toList

    val inputs = line.tail
    var count = 1

    inputs.foreach(input => {
      smallSolution(count, input.split(" "))
      count += 1
    })
  }
}
