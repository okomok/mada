

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class Split[A](_1: Peg[A], _2: sequence.Vector[A]) extends sequence.Iterative[sequence.Vector[A]] {
    Precondition.zeroWidth(_1, "split")

    override def begin = new sequence._Iterator[sequence.Vector[A]] {
        private[this] val u = new RepeatAtMostUntil(dot, java.lang.Integer.MAX_VALUE, end | _1)
        private[this] var (k, b, l) = u.parseImpl(_2, _2.start, _2.end)

        override protected def _isEnd = k == l
        override protected def _deref = new sequence.vector.Region(_2, k, b)
        override protected def _increment() = k_b_l_assign(u.parseImpl(_2, l, _2.end))

        private def k_b_l_assign(r: (Int, Int, Int)) {
            k = r._1; b = r._2; l = r._3;
        }
    }
}
