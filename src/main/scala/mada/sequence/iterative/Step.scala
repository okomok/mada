

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Step[+A](_1: Iterative[A], _2: Int) extends Iterative[A] {
    precondition.nonnegative(_2, "step")

    override def begin = new Iterator[A] {
        private val it = _1.begin

        override def isEnd = !it
        override def deref = ~it
        override def increment = it.advance(_2)
    }

    override def step(n: Int) = _1.step(_2 * n) // step-step fusion
}
