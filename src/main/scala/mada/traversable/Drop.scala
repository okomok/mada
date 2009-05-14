

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Drop[A](_1: Traversable[A], _2: Int) extends Traversable[A] { self =>
    override def start = {
        val t = _1.start
        var i = _2
        while (i != 0 && t) {
            t.++
            i -= 1
        }
        t
    }

    override def drop(n: Int) = _1.drop(_2 + n) // drop-drop fusion
}
