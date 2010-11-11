

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * A Reactive which has the single element
 */
final case class Val[+A](_1: A) extends ReactiveSingle[A] {
    override def foreach(f: A => Unit) = f(_1)
    override def toIterative: Iterative[A] = iterative.single(_1)
}


/**
 * A Reactive which has the lazy single element
 */
final case class LazyVal[+A](_1: eval.ByLazy[A]) extends ReactiveSingle[A] {
    override def foreach(f: A => Unit) = f(_1())
    override def toIterative: Iterative[A] = iterative.byLazy(iterative.single(_1()))
}

object LazyVal {
    def apply[A](v: => A, o: util.Overload = ()): LazyVal[A] = new LazyVal(v)
}
