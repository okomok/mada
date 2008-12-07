
package mada.rng


//  RandomAccessSeq[A] <-> Expr[Rng[A]]

object RandomAccessSeqCompatible extends RandomAccessSeqCompatible; trait RandomAccessSeqCompatible {
    implicit def toMadaRandomAccessSeqRngExpr[A](from: RandomAccessSeq[A]): ExprV2.Of[Rng[A]] = FromRandomAccessSeqExpr(ExprV2.Constant(from)).expr
}


// toRng

object RandomAccessSeqToRng extends RandomAccessSeqToRng; trait RandomAccessSeqToRng extends Predefs {
    class MadaRngRandomAccessSeqToRng[A](_1: ExprV2.Of[RandomAccessSeq[A]]) {
        def toRng = FromRandomAccessSeqExpr(_1).expr
    }
    implicit def toMadaRngRandomAccessSeqToRng[A](_1: ExprV2.Of[RandomAccessSeq[A]]): MadaRngRandomAccessSeqToRng[A] = new MadaRngRandomAccessSeqToRng[A](_1)
}

case class FromRandomAccessSeqExpr[A](_1: ExprV2.Of[RandomAccessSeq[A]]) extends ExprV2.Alias[RandomAccessSeq[A], Rng[A]] {
    override protected def _alias = IndexAccessRngExpr(new RandomAccessSeqIndexAccess(_1.eval))
}

class RandomAccessSeqIndexAccess[A](val base: RandomAccessSeq[A]) extends IndexAccess[A] {
    override def _set(i: Long, e: A) = base match {
        case base: RandomAccessSeq.Mutable[_] => base.asInstanceOf[RandomAccessSeq.Mutable[A]](i.toInt) = e
        case _ => NotWritableIndexAccessError
    }
    override def _get(i: Long) = base(i.toInt)
    override def _size = base.length
    override def toString = "RandomAccessSeqIndexAccess"
}
