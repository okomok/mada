

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Single[+A](_1: A) extends Iterative[A] {
    override def begin = new _Iterator[A] {
        private[this] var ends = false

        override protected def _isEnd = ends
        override protected def _deref = _1
        override protected def _increment = ends = true
    }

    override def cycle = repeat(_1) // single.cycle fusion
    override def times(n: Int) = repeat(_1).take(n) // single.times fusion
}


private
case class LazySingle[+A](_1: eval.Lazy[A]) extends Forwarder[A] {
    override protected val delegate = `lazy`(single(_1()))
}
