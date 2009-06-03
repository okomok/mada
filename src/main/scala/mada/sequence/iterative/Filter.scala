

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Filter[A](_1: Iterative[A], _2: A => Boolean) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private val it = _1.begin
        ready

        override def isEnd = !it
        override def deref = ~it
        override def increment = {
            it.++
            ready
        }

        private def ready: Unit = {
            while (it && !_2(~it)) {
                it.++
            }
        }
    }

    override def filter(p: A => Boolean) = _1.filter{ e => _2(e) && p(e) } // filter-filter fusion
}


case class Remove[A](_1: Iterative[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = _1.filter(function.not(_2))
}
