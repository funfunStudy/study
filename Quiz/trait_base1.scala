trait Philosophical {
  def philosophize() {
    println("I consume memory, therefore I am!")
  }
}

//class Frog extends Philosophical {
//  override def toString = "green"
//}

class Frog extends Animal with Philosophical {
  override def toString = "green"
  override def philosophize() {
    println("It ain't easy being "+ toString +"!")
  }
}


object trait_base1 {
  def main(args: Array[String]) {
    // 일반적인 사용
    val f = new Frog
    println(f)
    f.philosophize()

    // trait 형으로 형변환
    val phrog: Philosophical = new Frog
    phrog.philosophize()

    // 간단하게 응용해서 사용
    val x = new AnyRef with Philosophical
    x.philosophize()
  }
}
