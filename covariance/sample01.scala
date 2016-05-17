class Flower() {}
class Rose() extends Flower() {}

class Covariant[-T]

object VariancesTest extends App {
//  def addFlower(flower: Array[Flower])
  def addFlower[T <: Flower](flower: Array[T]) {}

  val r = Array(new Rose())
  addFlower(r)


  //val cv1: Covariant[AnyRef] = new Covariant[String]
  // Error
   val cv2: Covariant[String] = new Covariant[AnyRef]
}
