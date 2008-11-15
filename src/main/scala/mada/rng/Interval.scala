
package mada.rng


// Note: "x.AsInstanceOf[N] to x.toN" seems compile-time translation.


//  Array[A] <-> Expr[Rng[A]]

object IntervalConversions extends IntervalConversions

trait IntervalConversions {
    // Int
    implicit def toMadaIntIntervalRngExpr(from: => (Int, Int)) = FromIntIntervalExpr(Expr(from)).expr
    // Long
    //implicit def toMadaLongIntervalRngExpr(from: => (Long, Long)) = FromLongIntervalExpr(Expr(from)).expr
}


object IntervalToRng extends IntervalToRng

trait IntervalToRng extends Predefs {
    // Int
    class MadaRngIntIntervalToRng(_1: Expr[(Int, Int)]) {
        def toRng = FromIntIntervalExpr(_1).expr
    }
    implicit def toMadaRngIntIntervalToRng(_1: Expr[(Int, Int)]) = new MadaRngIntIntervalToRng(_1)
    // Long
    class MadaRngLongIntervalToRng(_1: Expr[(Long, Long)]) {
        def toRng = FromLongIntervalExpr(_1).expr
    }
    implicit def toMadaRngLongIntervalToRng(_1: Expr[(Long, Long)]) = new MadaRngLongIntervalToRng(_1)
}


// Int

case class FromIntIntervalExpr(_1: Expr[(Int, Int)]) extends Expr[Rng[Int]] {
    override def _eval = new IntIntervalPointer(_1.eval._1) <=< new IntIntervalPointer(_1.eval._2)
}

class IntIntervalPointer(n: Int) extends IntervalPointer[Int](n) {
    override def _read = base.toInt
}


// Long

case class FromLongIntervalExpr(_1: Expr[(Long, Long)]) extends Expr[Rng[Long]] {
    override def _eval = new LongIntervalPointer(_1.eval._1) <=< new LongIntervalPointer(_1.eval._2)
}

class LongIntervalPointer(n: Long) extends IntervalPointer[Long](n) {
    override def _read = base
}


// IntervalPointer

class IntervalPointer[N](var base: Long) extends PointerFacade[N, IntervalPointer[N]] {
    override def _traversal = RandomAccessTraversal
    override def _equals(that: IntervalPointer[N]) = base == that.base
    override def _increment { base = base + 1 }
    override def _clone = new IntervalPointer[N](base)
    override def _hashCode = long2Long(base).hashCode
    override def _decrement { base = base - 1 }
    override def _offset(d: Long) { base = base + d }
    override def _difference(that: IntervalPointer[N]) = base - that.base
}
