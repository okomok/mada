

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Drop[+A](_1: Iterative[A], _2: Int) extends Iterative[A] {
    throwIfNegative(_2, "drop")

    override def begin = {
        val it = _1.begin
        it.advance(_2)
        it
    }

    override def drop(n: Int) = _1.drop(_2 + n) // drop-drop fusion
}
