

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Single[+A](_1: A) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private var ends = false

        override def isEnd = ends
        override def deref = { preDeref; _1 }
        override def increment = { preIncrement; ends = true }
    }

    override def cycle = repeat(_1) // cycle-single fusion
    override def times(n: Int) = repeat(_1).take(n) // times-single fusion
}
