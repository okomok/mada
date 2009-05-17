

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Step[+A](_1: Traversable[A], _2: Int) extends Traversable[A] {
    override def begin = new Traverser[A] {
        if (_2 < 0) {
            throw new IllegalArgumentException("step" + Tuple1(_2))
        }
        private val t = _1.begin

        override def isEnd = !t
        override def deref = ~t
        override def increment = t.advance(_2)
    }

    override def step(n: Int) = _1.step(_2 * n) // step-step fusion
}
