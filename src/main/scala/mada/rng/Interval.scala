
package mada.rng


// Note: "x.AsInstanceOf[N] to x.toN" seems compile-time translation.


// Intervals

object IntInterval {
    def apply(_1: => Int, _2: => Int) = new IntInterval(_1, _2)
}
class IntInterval(a1: => Int, a2: => Int) {
    def _1 = a1
    def _2 = a2
}

object LongInterval {
    def apply(_1: => Long, _2: => Long) = new LongInterval(_1, _2)
}
class LongInterval(a1: => Long, a2: => Long) {
    def _1 = a1
    def _2 = a2
}


//  Interval <-> Expr[Rng[A]]

object IntervalCompatible extends IntervalCompatible

trait IntervalCompatible {
    // Ideally, (N, N) should conform to Rng, but tuples are the same types after type-erasure.
    implicit def toMadaIntIntervalRngExpr(from: => IntInterval): Expr[Rng[Int]] = FromIntIntervalExpr(Expr(from)).expr
    implicit def toMadaLongIntervalRngExpr(from: => LongInterval): Expr[Rng[Long]] = FromLongIntervalExpr(Expr(from)).expr
}

trait FromIntervalImpl {
    // Note by-name-parameters also are the same types after type-erasure.
    def from(_1: Int, _2: Int) = FromIntIntervalExpr(Expr(IntInterval(_1, _2))).expr
    def from(_1: Long, _2: Long) = FromLongIntervalExpr(Expr(LongInterval(_1, _2))).expr
}


// toRng

object IntervalToRng extends IntervalToRng

trait IntervalToRng extends Predefs {
    // Int
    class MadaRngIntIntervalToRng(_1: Expr[IntInterval]) {
        def toRng = FromIntIntervalExpr(_1).expr
    }
    implicit def toMadaRngIntIntervalToRng(_1: Expr[IntInterval]): MadaRngIntIntervalToRng = new MadaRngIntIntervalToRng(_1)
    // Long
    class MadaRngLongIntervalToRng(_1: Expr[LongInterval]) {
        def toRng = FromLongIntervalExpr(_1).expr
    }
    implicit def toMadaRngLongIntervalToRng(_1: Expr[LongInterval]): MadaRngLongIntervalToRng = new MadaRngLongIntervalToRng(_1)
}


// Int

case class FromIntIntervalExpr(_1: Expr[IntInterval]) extends Expr[Rng[Int]] {
    override def _eval = new IntIntervalPointer(_1.eval._1) <=< new IntIntervalPointer(_1.eval._2)
}

class IntIntervalPointer(n: Int) extends IntervalPointer[Int](n) {
    override def _read = base.toInt
    override def _copy = new IntIntervalPointer(base.toInt)
}


// Long

case class FromLongIntervalExpr(_1: Expr[LongInterval]) extends Expr[Rng[Long]] {
    override def _eval = new LongIntervalPointer(_1.eval._1) <=< new LongIntervalPointer(_1.eval._2)
}

class LongIntervalPointer(n: Long) extends IntervalPointer[Long](n) {
    override def _read = base
    override def _copy = new LongIntervalPointer(base)
}


// IntervalPointer

abstract class IntervalPointer[N](var base: Long) extends PointerFacade[N, IntervalPointer[N]] {
    override def _traversal = RandomAccessTraversal
    override def _equals(that: IntervalPointer[N]) = base == that.base
    override def _increment { base = base + 1 }
    override def _decrement { base = base - 1 }
    override def _offset(d: Long) { base = base + d }
    override def _difference(that: IntervalPointer[N]) = base - that.base
    override def hashCode = long2Long(base).hashCode
}
