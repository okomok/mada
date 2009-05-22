

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Expr[+A](_1: Sequence[A]) extends Forwarder[A] {
    override protected lazy val delegate: Sequence[A] = new ExprWrap(_1)
    override protected def afterForward[B](that: Sequence[B]) = Expr(that)
}

private class ExprWrap[+A](_1: Sequence[A]) extends Sequence[A] {
    override def begin = _1.begin

    override def map[B](f: A => B): Sequence[B] = Map(_1, f)
    override def slice(from: Int, until: Int): Sequence[A] = Slice(_1, from, until)
    // etc...
}


object Eval {
    def apply[A](seq: Sequence[A]): Sequence[A] = seq match {
        case Expr(_1) => Eval(_1)
        case Map(_1, _2) => Eval(_1).map(_2)
        case Slice(_1, _2, _3) => Eval(_1).slice(_2, _3)
        // etc...
        case _ => seq
    }
}
