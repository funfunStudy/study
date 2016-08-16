object Main {
  def main(args: Array[String]) {

    val list = List(1, 2, 3, 4, 5)
    val list2 = List(1.0, 2.0, 3.0, 4.0, 5.0)

    println(s"dropWhile2 : ${List.dropWhile2(list)(x => x < 3)}")
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

    println(s"map1 : ${List.map(list)((x:Int) => x+1)}")
    println(s"map2 : ${List.map(list2)((x:Double) => x.toString)}")

    println(s"filter : ${List.filter(list)((x:Int) => x %2 ==0)}")
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

    def sum2(ns: List[Int]) =
      foldRight(ns, 0)((x, y) => x + y)

    def product2(ns: List[Double]) =
      foldRight(ns, 1.0)(_ * _)

    def length[A](as : List[A]): Int =
      foldRight(as, 0)((x, y) => 1 + y)

    def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
      case Nil => z
      case Cons(x, xs) => f(foldLeft(xs, z)(f), x)
    }

    def sum3(ns: List[Int]) =
      foldLeft(ns, 0)((y, x) => x + y)

    def product3(ns: List[Double]) =
      foldLeft(ns, 1.0)(_ * _)

    def plus1(ns: List[Int]) : List[Int] = ns match {
      case Nil => Nil
      case Cons(x, xs) => Cons(x+1, plus1(xs))
    }

    def doubleToString(ns: List[Double]) : List[String] = ns match {
      case Nil => Nil
      case Cons(x, xs) => Cons(x.toString, doubleToString(xs))
    }

    def map[A,B](as : List[A])(f: A => B): List[B] = as match {
      case Nil => Nil
      case Cons(x, xs) => Cons(f(x), map(xs)(f))
    }

    def filter[A](as : List[A])(f: A => Boolean): List[A] = as match {
      case Nil => Nil
      case Cons(x, xs) => if(f(x)) Cons(x, filter(xs)(f)) else filter(xs)(f)
    }

    def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] =
      foldRight(map(as)(f), Nil)((x, y) => Cons(x, y))
  }


}

