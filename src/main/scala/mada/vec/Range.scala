

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// Ranges

case class IntRange(a1: Int, a2: Int) extends Tuple2[Int, Int](a1, a2)
case class LongRange(a1: Long, a2: Long) extends Tuple2[Long, Long](a1, a2)


//  Range -> Expr[Vector[A]]

object RangeCompatible extends RangeCompatible; trait RangeCompatible {
    implicit def madaVec_IntRange2ExprOfVec(from: IntRange): Expr.Of[Vector[Int]] = FromIntRangeExpr(Expr(from)).expr
    implicit def madaVec_LongIterval2ExprOfVec(from: LongRange): Expr.Of[Vector[Long]] = FromLongRangeExpr(Expr(from)).expr
}

object FromRange extends FromRange; trait FromRange {
    def from(_1: Int, _2: Int) = FromIntRangeExpr(Expr(IntRange(_1, _2))).expr
    def from(_1: Long, _2: Long) = FromLongRangeExpr(Expr(LongRange(_1, _2))).expr
}


// toVector

object RangeToVec extends RangeToVec; trait RangeToVec extends Predefs {
    // Int
    class MadaVecIntRangeToVec(_1: Expr.Of[IntRange]) {
        def toVector = FromIntRangeExpr(_1).expr
    }
    implicit def toMadaVecIntRangeToVec(_1: Expr.Of[IntRange]): MadaVecIntRangeToVec = new MadaVecIntRangeToVec(_1)
    // Long
    class MadaVecLongRangeToVec(_1: Expr.Of[LongRange]) {
        def toVector = FromLongRangeExpr(_1).expr
    }
    implicit def toMadaVecLongRangeToVec(_1: Expr.Of[LongRange]): MadaVecLongRangeToVec = new MadaVecLongRangeToVec(_1)
}


// Int

case class FromIntRangeExpr(override val _1: Expr.Of[IntRange]) extends Expr.Method[IntRange, Vector[Int]] {
    override protected def _default = {
        val r = _1.eval
        new IntRangeVector(r._1, r._2)
    }
}

class IntRangeVector(n: Int, m: Int) extends Vector[Int] with Vector.NotWritable[Int] {
    override def size = m - n
    override def apply(i: Long) = (n + i).toInt
}


// Long

case class FromLongRangeExpr(override val _1: Expr.Of[LongRange]) extends Expr.Method[LongRange, Vector[Long]] {
    override protected def _default = {
        val r = _1.eval
        new LongRangeVector(r._1, r._2)
    }
}

class LongRangeVector(n: Long, m: Long) extends Vector[Long] with Vector.NotWritable[Long] {
    override def size = m - n
    override def apply(i: Long) = n + i
}
