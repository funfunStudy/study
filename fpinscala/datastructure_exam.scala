object Main {
  def main(args: Array[String]) {

    val list = List(1, 2, 3, 4, 5)
    val list2 = List(1.0, 2.0, 3.0, 4.0, 5.0)
    val list3 = List(6, 7, 8, 9, 10)

    println(s"tail : ${List.getTail(list)}")
    println(s"head : ${List.setHead(9, list)}")
    println(s"drop : ${List.drop(2, list)}")
    println(s"dropWhile1 : ${List.dropWhile1(list, (x: Int) => x < 3)}")
    println(s"dropWhile2 : ${List.dropWhile2(list)(x => x < 3)}")
    println(s"init : ${List.init(list)}")
    println(s"length : ${List.length(list)}")
    println(s"sum2 : ${List.sum2(list)}")
    println(s"sum3 : ${List.sum3(list)}")
    println(s"product2 : ${List.product2(list2)}")
    println(s"product3 : ${List.product3(list2)}")
    println(s"plus1 : ${List.plus1(list)}")

    println(s"map1 : ${List.map(list)((x: Int) => x + 1)}")
    println(s"map2 : ${List.map(list2)((x: Double) => x.toString)}")

    println(s"filter : ${List.filter(list)((x: Int) => x % 2 == 0)}")
    println(s"foldRight : ${List.foldRight(List(1, 2, 3), List[Int]())(Cons(_, _))}")
    println(s"foldLeft : ${List.foldLeft(List[Int](), List(1, 2, 3))((x, y) => Cons(y, x))}")
    println(s"foldRightViaLeft : ${List.foldRightViaFoldLeft(List(1, 2, 3), List[Int]())(Cons(_, _))}")
    println(s"foldRightViaFoldLeft_1 : ${List.foldRightViaFoldLeft_1(List(1, 2, 3), List[Int]())(Cons(_, _))}")
    println(s"appendViaFoldRight : ${List.appendViaFoldRight(List(1, 2, 3), List(4, 5, 6))}")

    println(s"plus1ViaFoldRight : ${List.plus1ViaFoldRight(list)}")
    println(s"plus1ViaFoldLeft : ${List.plus1ViaFoldLeft(list)}")

    println(s"floatMap : ${List.flatMap(list)(x => Cons(x, Cons(x, Nil)))}")

    println(s"filterViaFlatMap : ${List.filterViaFlatMap(list)(_ % 2 == 1)}")

    println(s"zip : ${List.zip(list, list3)}")
    println(s"zipWidth : ${List.zipWidth(list, list3)((x, y) => x+ y)}")

    val subA = List(1,2)
    val subB = List(1,3)
    val subC = List(3,4,5)
    val subD = List(1,4,5)

    println(s"hasSubsequenceA : ${List.hasSubsequence(list, subA)}")
    println(s"hasSubsequenceB : ${List.hasSubsequence(list, subB)}")
    println(s"hasSubsequenceC : ${List.hasSubsequence(list, subC)}")
    println(s"hasSubsequenceD : ${List.hasSubsequence(list, subD)}")
  }

  sealed trait List[+A]

  // `List` data type, parameterized on a type, `A`
  case object Nil extends List[Nothing]

  // A `List` data constructor representing the empty list
  /* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
  which may be `Nil` or another `Cons`.
   */
  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  object List {
    // `List` companion object. Contains functions for creating and working with lists.
    def sum(ints: List[Int]): Int = ints match {
      // A function that uses pattern matching to add up a list of integers
      case Nil => 0 // The sum of the empty list is 0.
      case Cons(x, xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
    }

    def product(ds: List[Double]): Double = ds match {
      case Nil => 1.0
      case Cons(0.0, _) => 0.0
      case Cons(x, xs) => x * product(xs)
    }

    def apply[A](as: A*): List[A] = // Variadic function syntax
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))

    def getTail[A](list: List[A]): List[A] = list match {
      // Todo : List의 첫 원소를 제외한 나머지 List를 반환
    }

    def setHead[A](head: A, list: List[A]): List[A] = list match {
      // Todo : List의 첫 원소를 다른 값으로 교체
    }

    def drop[A](n: Int, list: List[A]): List[A] = list match {
      // Todo : List의 앞부분 부터 n 번째 까지를 제외한 리스트 반환
    }

    def dropWhile1[A](list: List[A], f: A => Boolean): List[A] = list match {
      // Todo : 함수 f에 부합하는 List의 앞 요소들을 제거하는 함수
    }

    def dropWhile2[A](list: List[A])(f: A => Boolean): List[A] = list match {
      // Todo : 함수 f에 부합하는 List의 앞 요소들을 제거하는 함수( 커링 사용 )
    }

    def init[A](list: List[A]): List[A] = list match {
      // Todo : List의 마지막 요소를 제외한 모든 요소를 반환하는 List
    }

    def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
      // Todo : foldRight를 구현
    }

    def sum2(ns: List[Int]) = {
      // Todo : foldRight를 이용해 sum을 재 구현
    }

    def product2(ns: List[Double]) = {
      // Todo : foldRight를 이용해 product를 재 구현
    }

    def length[A](as: List[A]): Int = {
      // Todo : foldRight를 이용해 List의 Length를 가져오는 함수 구현
    }

    def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
      // Todo : foldLeft를 구현
    }

    def sum3(ns: List[Int]) = {
    // Todo : foldLeft를 이용한 sum 구현
    }

    def product3(ns: List[Double]) = {
      // Todo : foldLeft를 이용한 product를 구현
    }

    def plus1 ... {
      // Todo : List의 각 요소값에 1을 더하는함수
    }

    def doubleToString ... {
      // Todo : List의 각 요소를 String으로 변환하는 함수
    }
    
    def reverse ... {
      // Todo : List를 역으로 반환하는 함수 (List(1,2,3) = List(3,2,1) )
    }
    
    def foldRightViaFoldLeft ... = {
      // Todo : foldLeft를 이용한 foldRight
    }
    
    def foldLeftViaFoldRight ... = {
      // Todo : foldRight를 이용한 foldLeft
    }
    
    def appendViaFoldRight ... = {
      // Todo : List 2개를 입력받아 이어주는 함수 (List(1,2,3) + List(4,5,6) = List(1,2,3,4,5,6) )
    }
    
    def concat ... = {
      // Todo : List의 List를 하나의 List로 반환하는 함수 ( List(LIst(1), List(2), List(3,4)) = List(1,2,3,4) )
    }

    def map[A, B](as: List[A])(f: A => B): List[B] = as match {
      // Todo : A를 B로 변환하는 함수 구현
    }

    def filter[A](as: List[A])(f: A => Boolean): List[A] = as match {
      // Todo : List 에서 f를 만족시키지 않는 요소를 제외한 List 를 반환하는 함수
    }

    def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
      // Todo : List A의 요소 하나하나를 List[B] 로 만들고 전체를 하나의 리스트로 만드는 함수
    }
    
    def filterViaFlatMap ... = {
      // Todo : flatMap 을 이용한 filter 함수 
    }

    def zip ... {
      // Todo : ListA 와 ListB를 입력받아 각 요소의 Index끼리의 합을 만드는 함수. 길이가 다를 경우 짧은 List를 기준.
      // List(1,2,3) List(4,5,6) = List(5,7,9)
    }

    @tailrec
    def zipWidth ... {
      //Todo : Zip 을 일반화한 함수.
    }

    @tailrec
    def hasSubsequence ... {
      // Todo : List B가 ListA 의 서브 시퀀스인지 검사하는 함수.
    }

  }


}

