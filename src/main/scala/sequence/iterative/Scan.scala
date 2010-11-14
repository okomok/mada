

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class ScanLeft[A, B](_1: Iterative[A], _2: B, _3: (B, A) => B) extends Forwarder[B] {
    override protected val delegate = single(_2) ++ new _ScanLeft(_1, _2, _3)
}

private class _ScanLeft[A, B](_1: Iterative[A], _2: B, _3: (B, A) => B) extends Iterative[B] {
    override def begin = new Iterator[B] {
        private[this] val it = _1.begin
        private[this] var z = _2

        override def isEnd = !it
        override def deref = _3(z, ~it)
        override def increment() {
            z = deref
            it.++
        }
    }
}


private
case class ScanLeft1[A, B >: A](_1: Iterative[A], _2: (B, A) => B) extends Iterative[B] {
    override def begin = {
        Precondition.notEmpty(_1, "scanLeft1")
        val it = _1.begin // needs a fresh iterator every time.
        val e = ~it
        it.++
        bind(it).scanLeft[B](e)(_2).begin
    }
}
