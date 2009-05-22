

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Act[A](_1: Peg[A], _2: Action[A]) extends Forwarder[A] {
    override protected val delegate = _1.act3(vector.triplify(_2))
}

case class Act3[A](_1: Peg[A], _2: Action3[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = _1.parse(v, start, end)
        if (cur != FAILURE) {
            _2(v, start, cur)
        }
        cur
    }

    override def width = _1.width
}
