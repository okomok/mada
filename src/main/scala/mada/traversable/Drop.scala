

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Drop[A](_1: Traversable[A], _2: Int) extends Traversable[A] {
    override def start = {
        val t = _1.start
        t.advance(_2)
        t
    }

    override def drop(n: Int) = _1.drop(_2 + n) // drop-drop fusion
}
