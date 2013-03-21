package reactormonk.counter
import scala.collection.{GenMap,MapLike}

class Counter[A](counter: Map[A, Int]) extends Iterable[(A, Int)]{
  def +(key: A): Counter[A] = this.change(key, 1)
  def -(key: A): Counter[A] = this.change(key, -1)
  def change(key: A, by: Int): Counter[A] = Counter((counter + (key -> {by.+(apply(key)):Int})))
  def ++(other: Counter[A]): Counter[A] = other.foldLeft(this)({case (counter, (key, count)) => counter.change(key, count)})
  def get(key: A): Option[Int] = counter.get(key)
  def apply(key: A): Int = counter.getOrElse(key, 0)
  def toMap(): Map[A, Int] = counter
  override def iterator(): Iterator[Tuple2[A, Int]] = counter.iterator
  override def toList(): List[Tuple2[A, Int]] = counter.toList
  override def toSeq(): Seq[Tuple2[A, Int]] = counter.toSeq
  def empty(): Counter[A] = Counter[A]()
  override def size: Int = counter.size
}

object Counter {
  def apply[A](): Counter[A] = apply(Map[A, Int]())
  def apply[A](counter: Map[A, Int]): Counter[A] = { new Counter[A](counter) }
  def apply[A](count: Seq[A]): Counter[A] = count.foldLeft(apply[A]())({case (counter, element) => counter + element})
}
