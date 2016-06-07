import scala.annotation.tailrec
import scala.collection.immutable.ListMap
import scala.collection.mutable

/**
  * Created by n3956 on 2016-05-25.
  */
object Hello {
  def main(args: Array[String]) {

    val inputList = List(23, 3, 32, 38, 392, 382, 2, 9, 92, 263, 4, 2138)
    val result = getResult(inputList)
    println(result.mkString(""))
  }

  def getResult(inputList: List[Int]) = {
    val group = createGroup(inputList)
    val result = ListMap(group.toSeq.sortWith(_._1 > _._1):_*).flatMap( _._2)
    println(result)
    result
  }

  def isBigger(num: Int, head: Int): Boolean = {
    val numLength = getNumberLength(num)
    val headLength = getNumberLength(head)

    if(numLength == headLength){
      if(num >=  head)
        true
      else
        false
    } else {
      if(numLength > headLength){
        isBigger(num, createBasNumber(head, getKeyOfNum(head), numLength-headLength))
      } else {
        isBigger(createBasNumber(num, getKeyOfNum(num), headLength-numLength), head)
      }
    }
  }

  def sortList(num: Int, list: List[Int]): List[Int] = list match {
    case Nil => num :: Nil
    case x :: xs => {
      if (isBigger(num, x)) {
        num :: list
      } else {
        x :: sortList(num, xs)
      }
    }
  }

  def insertMap(num: Int, map: mutable.Map[Int, List[Int]]) = {
    val key: Int = getKeyOfNum(num)
    if (map.contains(key)) {
      map(key) = sortList(num, map(key))
    } else {
      map(key) = num :: Nil
    }
  }

  def createGroup(input: List[Int]): mutable.Map[Int, List[Int]] = {
    val map = scala.collection.mutable.Map.empty[Int, List[Int]]

    input.foreach(num => {
      insertMap(num, map)
    })
    map
  }

  @tailrec
  def getKeyOfNum(num: Int): Int = {
    num / 10 match {
      case 0 => num % 10
      case _ => getKeyOfNum(num / 10)
    }
  }

  def getNumberLength(num : Int): Int ={
    var count =1
    var baseNumber = num
    while (baseNumber/10 !=0 ){
      baseNumber /=10
      count+=1
    }
    count
  }

  def createBasNumber(num : Int, key : Int, length : Int) : Int = {
    var result = num
    var count = length
    while (count > 0) {
      result = result * 10 + key
      count -= 1
    }
    result
  }

}
