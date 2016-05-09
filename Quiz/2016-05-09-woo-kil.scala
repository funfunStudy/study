/**
  * Created by myeongin on 2016. 5. 9..
  */
import java.io.PrintWriter
import java.io._
import scala.util.control.Breaks._
import scala.io.Source

object Main {

  def main(args: Array[String]) {
    val line = Source.fromFile("files/A-large-practice.in").getLines.toList

    val num = line.head.toInt
    val inputs = line.tail
    var count = 1

    //파일로드
    val writer = new PrintWriter(new File("files/A-large-practice.out"))

    inputs.foreach(input => {
      val result = s"Case #$count: ${largeSolution(input.split(" "))}\n"
      writer.write(result)
      count +=1
    })

    writer.close()
  }

  //라지 케이스를 풀면서 개선한 알고리즘 시간복잡도가 많이 단축 됬다
  def largeSolution(inputList : Array[String]): Long ={
    val month = inputList(0).toLong
    val days = inputList(1).toInt
    val weeks = inputList(2).toInt

    var sum = 0 :Long
    var remaind = 0

    val map = scala.collection.mutable.Map.empty[Long, Long]

    var count = 0
    breakable{
      while(remaind != 0||map.size != -1){
        sum += getSum(remaind, days, weeks)
        remaind = getRemaind(remaind, days, weeks)

        map(count) = sum

        if(remaind == 0)
          break()

        count += 1
      }
    }

    if(map.nonEmpty){
      val sumOfCycle = map(map.size-1)
      sum = sumOfCycle * (month / map.size)

      val a = month % map.size

      if(a != 0)
        sum += map(a-1)
    } else{
      sum = month*(days/weeks)
    }
    sum
  }

  //스몰 케이스 값을 구했을 때 했던 시간 복잡도가 높았던 알고리즘
  def smallSolution(inputList : Array[String]): Int ={

    val month = inputList(0).toLong
    val days = inputList(1).toInt
    val weeks = inputList(2).toInt


    var sum = 0
    var remaind = 0

    for(i <- 0L until month){
      sum+=     getSum(remaind, days, weeks)
      remaind = getRemaind(remaind, days, weeks)
    }
    sum
  }

  //이전 달의 나머지값과 현재 달의 일수를 더해서 달력의 줄을 계산한다
  def getSum(value : Int, days : Int, weeks : Int) :Int = if((days+value)%weeks == 0 ) (days+value)/weeks else (days+value)/weeks +1

  //이번 달의 마지막 줄의 나머지 값을 구한다
  def getRemaind(remaind :Int, days : Int , weeks:Int): Int = (days+remaind)%weeks


}
