

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


case class UnfoldRight[A, +B](_1: A, _2: A => Option[(B, A)]) extends Iterative[B] {
    override def begin = new Iterator[B] {
        private var acc = _2(_1)

        override def isEnd = acc.isEmpty
        override def deref = { preDeref; acc.get._1 }
        override def increment = { preIncrement; acc = _2(acc.get._2) }
    }
}
