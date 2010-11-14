

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Filter[A](_1: Iterative[A], _2: A => Boolean) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private[this] val it = _1.begin
        ready()

        override def isEnd = !it
        override def deref = ~it
        override def increment() {
            it.++
            ready()
        }

        private def ready() {
            while (it && !_2(~it)) {
                it.++
            }
        }
    }

    override def filter(p: A => Boolean) = _1.filter{ e => _2(e) && p(e) } // filter.filter fusion

    override def foreach(f: A => Unit) { // filter.foreach fusion
        val it = _1.begin
        while (it) {
            val e = ~it
            if (_2(e)) {
                f(e)
            }
            it.++
        }
    }
}


private
case class Remove[A](_1: Iterative[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = _1.filter(function.not(_2))
}
