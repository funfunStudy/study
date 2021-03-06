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
      case Nil => Nil
      case Cons(x, xs) => xs
    }

    def setHead[A](head: A, list: List[A]): List[A] = list match {
      case Nil => Nil
      case Cons(x, xs) => Cons(head, xs)
    }

    def drop[A](n: Int, list: List[A]): List[A] = list match {
      case Nil => Nil
      case Cons(x, xs) => drop(n - 1, List.getTail(list))
    }

    def dropWhile1[A](list: List[A], f: A => Boolean): List[A] = list match {
      case Cons(x, xs) if f(x) => dropWhile1(xs, f)
      case _ => list
    }

    def dropWhile2[A](list: List[A])(f: A => Boolean): List[A] = list match {
      case Cons(x, xs) if f(x) => dropWhile2(xs)(f)
      case _ => list
    }

    def init[A](list: List[A]): List[A] = list match {
      case Cons(x, Nil) => Nil
      case Cons(x, xs) => Cons(x, init(xs))
    }

    def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

    def foldRightViaFoldLeft[A, B](as: List[A], z: B)(f: (A, B) => B): B =
      foldLeft(reverse(as), z)((b, a) => f(a, b))

    def foldRightViaFoldLeft_1[A, B](l: List[A], z: B)(f: (A, B) => B): B =
      foldLeft(l, (b: B) => b)((g, a) => b => g(f(a, b)))(z)

    def sum2(ns: List[Int]) =
      foldRight(ns, 0)((x, y) => x + y)

    def product2(ns: List[Double]) =
      foldRight(ns, 1.0)(_ * _)

    def length[A](as: List[A]): Int =
      foldRight(as, 0)((x, y) => 1 + y)

    def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    }

    def foldLeftViaFoldRight[A, B](as: List[A], z: B)(f: (B, A) => B): B =
      foldRight(as, (b: B) => b)((a, g) => b => g(f(b, a)))(z)

    def sum3(ns: List[Int]) =
      foldLeft(ns, 0)((y, x) => x + y)

    def product3(ns: List[Double]) =
      foldLeft(ns, 1.0)(_ * _)

    def reverse[A](ns: List[A]) =
      foldLeft(ns, List[A]())((list, head) => Cons(head, list))

    def appendViaFoldRight[A](listA: List[A], listB: List[A]) =
      foldRight(listA, listB)((x, y) => Cons(x, y))

    def plus1(ns: List[Int]): List[Int] = ns match {
      case Nil => Nil
      case Cons(x, xs) => Cons(x + 1, plus1(xs))
    }

    def plus1ViaFoldRight(ns: List[Int]): List[Int] =
      foldRight(ns, Nil: List[Int])((x, y) => Cons(x + 1, y))

    def plus1ViaFoldLeft(ns: List[Int]): List[Int] =
      foldLeft(ns, Nil: List[Int])((y, x) => Cons(x + 1, y))

    def doubleToString(ns: List[Double]): List[String] = ns match {
      case Nil => Nil
      case Cons(x, xs) => Cons(x.toString, doubleToString(xs))
    }

    def map[A, B](as: List[A])(f: A => B): List[B] = as match {
      case Nil => Nil
      case Cons(x, xs) => Cons(f(x), map(xs)(f))
    }

    def filter[A](as: List[A])(f: A => Boolean): List[A] = as match {
      case Nil => Nil
      case Cons(x, xs) => if (f(x)) Cons(x, filter(xs)(f)) else filter(xs)(f)
    }

    def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] =
      foldRight(map(as)(f), List[B]())((listA, listB) => appendViaFoldRight(listA, listB))

    def filterViaFlatMap[A](as: List[A])(f: A => Boolean): List[A] =
      flatMap(as)(a => if (f(a)) List(a) else Nil)

    def zip(listA: List[Int], listB: List[Int]): List[Int] = (listA, listB) match {
      case (_, Nil) => Nil
      case (Nil, _) => Nil
      case (Cons(aHead, aTail), Cons(bHead, bTail)) => Cons(aHead + bHead, zip(aTail, bTail))
    }

    def zipWidth[A](listA: List[A], listB: List[A])(f:(A, A) => A): List[A] = (listA, listB) match {
      case (_, Nil) => Nil
      case (Nil, _) => Nil
      case (Cons(aHead, aTail), Cons(bHead, bTail)) => Cons(f(aHead, bHead), zipWidth(aTail, bTail)(f))
    }

    def hasSubsequence[A](sup: List[A], sub: List[A], hasFirst : Boolean = false) : Boolean = (sup, sub, hasFirst) match {
      case (Nil, Nil, true) => true
      case (Nil, _, _) => false
      case (_, Nil, true) => true
      case (Cons(supHead, supTail), Cons(subHead, subTail), true) =>
        if(supHead == subHead) hasSubsequence(supTail, subTail, true)
        else false
      case (Cons(supHead, supTail), Cons(subHead, subTail), false) => {
        if(supHead == subHead) hasSubsequence(supTail, subTail, true)
        else hasSubsequence(supTail, Cons(subHead, subTail), false)
      }
    }

  }


}
