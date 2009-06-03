

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Expr[+A](_1: Iterative[A]) extends Forwarder[A] {
    override protected lazy val delegate: Iterative[A] = new ExprWrap(_1)
    override protected def afterForward[B](that: Iterative[B]) = Expr(that)
}

private class ExprWrap[+A](_1: Iterative[A]) extends Iterative[A] {
    override def begin = _1.begin

    override def map[B](f: A => B): Iterative[B] = Map(_1, f)
    override def slice(from: Int, until: Int): Iterative[A] = Slice(_1, from, until)
    // etc...
}


object Eval {
    def apply[A](it: Iterative[A]): Iterative[A] = it match {
        case Expr(_1) => Eval(_1)
        case Map(_1, _2) => Eval(_1).map(_2)
        case Slice(_1, _2, _3) => Eval(_1).slice(_2, _3)
        // etc...
        case _ => it
    }
}
