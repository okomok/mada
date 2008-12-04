
package mada.rng


//  RandomAccessSeq[A] <-> Expr[Rng[A]]

object RandomAccessSeqCompatible extends RandomAccessSeqCompatible; trait RandomAccessSeqCompatible {
    implicit def toMadaRandomAccessSeqRngExpr[A](from: RandomAccessSeq[A]): Expr[Rng[A]] = FromRandomAccessSeqExpr(Expr(from)).expr
}


// toRng

object RandomAccessSeqToRng extends RandomAccessSeqToRng; trait RandomAccessSeqToRng extends Predefs {
    class MadaRngRandomAccessSeqToRng[A](_1: Expr[RandomAccessSeq[A]]) {
        def toRng = FromRandomAccessSeqExpr(_1).expr
    }
    implicit def toMadaRngRandomAccessSeqToRng[A](_1: Expr[RandomAccessSeq[A]]): MadaRngRandomAccessSeqToRng[A] = new MadaRngRandomAccessSeqToRng[A](_1)
}

case class FromRandomAccessSeqExpr[A](_1: Expr[RandomAccessSeq[A]]) extends Expr[Rng[A]] {
    override def _eval[U](c: Context[Rng[A], U]): U = {
        IndexAccessRngExpr(new RandomAccessSeqIndexAccess(_1.eval)).eval(c)
    }
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
