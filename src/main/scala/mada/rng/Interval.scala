

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


// Note: "x.AsInstanceOf[N] to x.toN" seems compile-time translation.


// Intervals

case class IntInterval(a1: Int, a2: Int) extends Tuple2[Int, Int](a1, a2)
case class LongInterval(a1: Long, a2: Long) extends Tuple2[Long, Long](a1, a2)


//  Interval -> Expr[Rng[A]]

object IntervalCompatible extends IntervalCompatible; trait IntervalCompatible {
    // Ideally, (N, N) should conform to Rng, but tuples are the same types after type-erasure.
    implicit def madaRng_IntInterval2ExprOfRng(from: IntInterval): Expr.Of[Rng[Int]] = FromIntIntervalExpr(Expr(from)).expr
    implicit def madaRng_LongIterval2ExprOfRng(from: LongInterval): Expr.Of[Rng[Long]] = FromLongIntervalExpr(Expr(from)).expr
}

object FromInterval extends FromInterval; trait FromInterval {
    // Note by-name-parameters also are the same types after type-erasure.
    def from(_1: Int, _2: Int) = FromIntIntervalExpr(Expr(IntInterval(_1, _2))).expr
    def from(_1: Long, _2: Long) = FromLongIntervalExpr(Expr(LongInterval(_1, _2))).expr
}


// toRng

object IntervalToRng extends IntervalToRng; trait IntervalToRng extends Predefs {
    // Int
    class MadaRngIntIntervalToRng(_1: Expr.Of[IntInterval]) {
        def toRng = FromIntIntervalExpr(_1).expr
    }
    implicit def toMadaRngIntIntervalToRng(_1: Expr.Of[IntInterval]): MadaRngIntIntervalToRng = new MadaRngIntIntervalToRng(_1)
    // Long
    class MadaRngLongIntervalToRng(_1: Expr.Of[LongInterval]) {
        def toRng = FromLongIntervalExpr(_1).expr
    }
    implicit def toMadaRngLongIntervalToRng(_1: Expr.Of[LongInterval]): MadaRngLongIntervalToRng = new MadaRngLongIntervalToRng(_1)
}


// Int

case class FromIntIntervalExpr(override val _1: Expr.Of[IntInterval]) extends Expr.Method[IntInterval, Rng[Int]] {
    override protected def _default = new IntIntervalPointer(_1.eval._1) <=< new IntIntervalPointer(_1.eval._2)
}

class IntIntervalPointer(n: Int) extends IntervalPointer[Int](n) {
    override protected def _read = base.toInt
    override protected def _copy = new IntIntervalPointer(base.toInt)
}


// Long

case class FromLongIntervalExpr(override val _1: Expr.Of[LongInterval]) extends Expr.Method[LongInterval, Rng[Long]] {
    override protected def _default = new LongIntervalPointer(_1.eval._1) <=< new LongIntervalPointer(_1.eval._2)
}

class LongIntervalPointer(n: Long) extends IntervalPointer[Long](n) {
    override protected def _read = base
    override protected def _copy = new LongIntervalPointer(base)
}


// IntervalPointer

abstract class IntervalPointer[N](var base: Long) extends PointerFacade[N, IntervalPointer[N]] {
    override protected def _traversal = RandomAccess
    override protected def _equals(that: IntervalPointer[N]) = base == that.base
    override protected def _increment = base += 1
    override protected def _decrement = base -= 1
    override protected def _offset(d: Long) = base += d
    override protected def _difference(that: IntervalPointer[N]) = base - that.base
    override def hashCode = long2Long(base).hashCode
}
