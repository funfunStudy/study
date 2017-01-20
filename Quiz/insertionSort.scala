/**
  * Created by kgkim on 2016. 4. 24..
  */
object insertion {
  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))

  def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)

  // 리스트 패턴 매칭
  def isort2(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xs1 => insert2(x, isort2(xs1))
  }

  def insert2(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs
                    else y :: insert2(x, ys)
  }

  def main(args: Array[String]) {
    println(isort(List(8, 10, 2, 4, 1, 9, 6, 3, 7, 5)))
    println(isort2(List(8, 10, 2, 4, 1, 9, 6, 3, 7, 5)))
  }
}
