

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Take[+A](_1: Iterative[A], _2: Int) extends Iterative[A] {
    precondition.nonnegative(_2, "take")

    override def begin = new Iterator[A] {
        private var it = _1.begin
        private var i = _2
        ready

        override def isEnd = !it
        override def deref = ~it
        override def increment = {
            it.++
            i -= 1
            ready
        }

        private def ready: Unit = {
            if (i == 0) {
                it = iterator.theEnd
            }
        }
    }

    override def take(n: Int) = _1.take(Math.max(_2, n)) // take-take fusion
}
