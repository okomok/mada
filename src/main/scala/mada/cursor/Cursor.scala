
package mada.cursor


// Element-access traits

trait Readable[Cur, Ref] {
  def read: Ref
}

trait Writable[Cur, Ref] {
  def write(r: Ref): Unit
}


// Traversal traits

trait SinglePass[Cur] {
  def equal(that: Cur): Boolean
  def increment : Unit
}

trait Forward[Cur] extends SinglePass[Cur] {
  def copy : Cur
}

trait Bidirectional[Cur] extends Forward[Cur] {
  def decrement : Unit  
}

trait RandomAccess[Cur, Dif] extends Bidirectional[Cur] {
  def offset(n: Dif): Unit // += // std::advance works with single-pass, so badname, offset is better?
  def difference(that: Cur): Dif // can be negative, so distance_to is badname.
  // this - that == distance(that, this)
}

class TransformPointer[X, Y, Cur <: SinglePass[Cur] with Readable[Cur, X]](p: Cur, f: Function1[X, Y])
  extends SinglePass[TransformPointer[X, Y, Cur]] with Readable[TransformPointer[X, Y, Cur], Y] {
  private val from: Cur = p;
  def read: Y = f(from.read)
  def equal(that: TransformPointer[X, Y, Cur]): Boolean = this.from.equal(that.from)
  def increment: Unit = from.increment
}

// User interfaces

object equal {
  final def apply[Cur <% SinglePass[Cur]](p: Cur, q: Cur): Boolean = p.equal(q)
}

object increment {
  final def apply[Cur <% SinglePass[Cur]](p: Cur): Unit = p.increment
}

object copy {
  final def apply[Cur <% Forward[Cur]](p: Cur): Cur = p.copy
}

object decrement {
  final def apply[Cur <% Bidirectional[Cur]](p: Cur) : Unit = p.decrement
}

object offset {
  final def apply[Cur, Dif](p: Cur, n: Dif)
    (implicit c : Cur => RandomAccess[Cur, Dif]): Unit = c(p).offset(n)
}

object difference {
  final def apply[Cur, Dif](p: Cur, q: Cur)
    (implicit c : Cur => RandomAccess[Cur, Dif]): Dif = c(p).difference(q)
}


// The Range trait

trait Range[+Cur] {
  def begin: Cur
  def end: Cur
}


object begin {
  final def apply[Cur](rng: Range[Cur]): Cur = rng begin
}

object end {
  final def apply[Cur](rng: Range[Cur]): Cur = rng end
}


case class PointerRange[Cur](first: Cur, last: Cur) extends Range[Cur] {
  def begin: Cur = first
  def end: Cur = last
}

case class SubRange[Cur](rng: Range[Cur]) extends Range[Cur] {
  def begin: Cur = rng.begin
  def end: Cur = rng.end
}

case class MapRange[Cur, X, Y](rng: Range[Cur], f: Function1[X, Y]) extends Range[Cur] {
  def begin: Cur = rng.begin
  def end: Cur = rng.end
}

/*
case class Fill[Ref, Cur <: SinglePass[Cur]](rng: Range[Readable[Cur, Ref]], e: Ref) extends Range[Cur] {
  def begin: Readable[Cur, Ref] = rng.begin
  def end: Readable[Cur, Ref] = rng.end
}
*/

/*
object deforestation {
  def optimize[Cur](rng: Range[Cur]): Range[Cur] = {
    rng match {
      case SubRange(SubRange(r)) =>
        this.optimize(new SubRange(r))
      case SubRange(PointerRange(p, q)) =>
        this.optimize(new PointerRange(p, q))
      case MapRange(MapRange(r, f), g) =>
        this.optimize(new MapRange(r, g compose f))
      case Fill(Fill(rng, e1), e2) =>
        this.optimize(new Fill(rng, e2))
      case _ =>
        rng
    }
  }
  
  def evaluate[Cur](rng: Range[Cur]): Range[Cur] = {
    rng match {
      case _ =>
        rng
    }
  }
}
*/

object Conversions {
  
  class IteratorPointer[A](it: Iterator[A]) extends SinglePass[Iterator[A]] with Readable[Iterator[A], A] {
	  private var a: Option[A] = if (it.hasNext) new Some(it.next) else None // null can't be used.
	  def read: A = a.get
	  def equal(that: Iterator[A]): Boolean = it.hasNext == that.hasNext
	  def increment: Unit = { a = new Some(it.next) }
  }

  implicit def fromIterator[A](it: Iterator[A]): IteratorPointer[A] = new IteratorPointer[A](it)
  
  implicit def fromTuple2[T](tup: Tuple2[T, T]): Range[T] = new Range[T] {
    def begin: T = tup._1
    def end: T = tup._2
  }
}

object increment_ {
  def apply[Cur](p: Cur): Unit = {
      import Conversions._
      increment(Iterator.empty)
    }
}




object foo {
  def apply[Cur](rng: Range[Cur])
    (implicit c: Cur => Forward[Cur]): Cur = copy(rng begin)(c)

  def apply_[Cur <: Forward[Cur]](rng: Range[Cur]): Cur = copy(rng begin)

  //  def apply__[Cur](rng: Range[Forward[Cur]]): Forward[Cur] = copy(rng begin)
}


object Pointer {
  def main(args: Array[String]) = {
    
    Console.println("Hello, pointer!")
  }
}

/*


object foo {
  def apply[Cur <% SinglePass[Cur]](p: Cur): Unit = { p.increment; () }
}

object bar {
  import Convs._
//  def apply_[It](it: It): Unit = foo(it)
  def apply(it: Iterator[Int]): Unit = foo(it)
  def apply[A](i: A): Int = 3
}
*/