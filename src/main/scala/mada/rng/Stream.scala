
package mada.rng


import ToIterator._


//  Stream[A] <-> Expr[Rng[A]]

object StreamCompatible extends StreamCompatible; trait StreamCompatible {
    implicit def madaRng_Stream2ExprRng[A](from: Stream[A]): ExprV2.Of[Rng[A]] = FromStreamExpr(ExprV2.Constant(from)).expr
}


// toRng

object StreamToRng extends StreamToRng; trait StreamToRng extends Predefs {
    class MadaRngStreamToRng[A](_1: ExprV2.Of[Stream[A]]) {
        def toRng = FromStreamExpr(_1).expr
    }
    implicit def toMadaRngStreamToRng[A](_1: ExprV2.Of[Stream[A]]): MadaRngStreamToRng[A] = new MadaRngStreamToRng[A](_1)
}

case class FromStreamExpr[A](override val _1: ExprV2.Of[Stream[A]]) extends ExprV2.Method[Stream[A], Rng[A]] {
    override def _default = _1 match {
        case ToStreamExpr(x1) => x1.eval
        case _ => FromStreamImpl(_1.eval)
    }
}

object FromStreamImpl {
    def apply[A](s: Stream[A]): Rng[A] = {
        new StreamPointer(if (s.isEmpty) null else s) <=< new StreamPointer(null)
    }
}

class StreamPointer[A](var base: Stream[A]) extends PointerFacade[A, StreamPointer[A]] {
    override def _read = base.head
    override def _traversal = ForwardTraversal
    override def _equals(that: StreamPointer[A]) = base eq that.base
    override def _increment = { val tl = base.tail; base = if (tl.isEmpty) null else tl }
    override def _copy = new StreamPointer[A](base)
    override def hashCode = base.hashCode
}


// toStream

object ToStream extends ToStream; trait ToStream extends Predefs {
    class MadaRngToStream[A](_1: ExprV2.Of[Rng[A]]) {
        def toStream = ToStreamExpr(_1).expr
    }
    implicit def toMadaRngToStream[A](_1: ExprV2.Of[Rng[A]]): MadaRngToStream[A] = new MadaRngToStream[A](_1)
}

case class ToStreamExpr[A](override val _1: ExprV2.Of[Rng[A]]) extends ExprV2.Method[Rng[A], Stream[A]] {
    override def _default = _1 match {
        case FromStreamExpr(x1) => x1.eval
        case _ => Stream.fromIterator(_1.toIterator.eval)
    }
}
