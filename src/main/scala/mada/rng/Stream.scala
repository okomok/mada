
package mada.rng


import ToIterator._


//  Stream[A] <-> Expr[Rng[A]]

object StreamCompatible extends StreamCompatible; trait StreamCompatible {
    implicit def madaRng_Stream2ExprRng[A](from: Stream[A]): Expr.Of[Rng[A]] = FromStreamExpr(Expr.Constant(from)).expr
}


// toRng

object StreamToRng extends StreamToRng; trait StreamToRng extends Predefs {
    class MadaRngStreamToRng[A](_1: Expr.Of[Stream[A]]) {
        def toRng = FromStreamExpr(_1).expr
    }
    implicit def toMadaRngStreamToRng[A](_1: Expr.Of[Stream[A]]): MadaRngStreamToRng[A] = new MadaRngStreamToRng[A](_1)
}

case class FromStreamExpr[A](override val _1: Expr.Of[Stream[A]]) extends Expr.Method[Stream[A], Rng[A]] {
    override protected def _default = _1 match {
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
    override protected def _read = base.head
    override protected def _traversal = ForwardTraversal
    override protected def _equals(that: StreamPointer[A]) = base eq that.base
    override protected def _increment = { val tl = base.tail; base = if (tl.isEmpty) null else tl }
    override protected def _copy = new StreamPointer[A](base)
    override def hashCode = base.hashCode
}


// toStream

object ToStream extends ToStream; trait ToStream extends Predefs {
    class MadaRngToStream[A](_1: Expr.Of[Rng[A]]) {
        def toStream = ToStreamExpr(_1).expr
    }
    implicit def toMadaRngToStream[A](_1: Expr.Of[Rng[A]]): MadaRngToStream[A] = new MadaRngToStream[A](_1)
}

case class ToStreamExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Stream[A]] {
    override protected def _default = _1 match {
        case FromStreamExpr(x1) => x1.eval
        case _ => Stream.fromIterator(_1.toIterator.eval)
    }
}
