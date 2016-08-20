{
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
      foldLeft(reverse(as), z)((b, a) => f(a,b))

    def foldRightViaFoldLeft_1[A,B](l: List[A], z: B)(f: (A,B) => B): B =
      foldLeft(l, (b:B) => b)((g,a) => b => g(f(a,b)))(z)

    def sum2(ns: List[Int]) =
      foldRight(ns, 0)((x, y) => x + y)

    def product2(ns: List[Double]) =
      foldRight(ns, 1.0)(_ * _)

    def length[A](as : List[A]): Int =
      foldRight(as, 0)((x, y) => 1 + y)

    def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    }

    def foldLeftViaFoldRight[A,B](as: List[A], z: B)(f: (B, A) => B): B =
      foldRight(as, (b: B) => b)((a, g) => b => g(f(b, a)))(z)

    def sum3(ns: List[Int]) =
      foldLeft(ns, 0)((y, x) => x + y)

    def product3(ns: List[Double]) =
      foldLeft(ns, 1.0)(_ * _)

    def reverse[A](ns: List[A]) =
      foldLeft(ns, List[A]())((list, head) => Cons(head, list))

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

//    def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] =
//      foldRight(map(as)(f), List[B]())((listA, listB) => listA + listB)
  }
