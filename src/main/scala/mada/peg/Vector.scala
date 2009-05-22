

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class FromVector[A](_1: Vector[A]) extends Forwarder[A] {
    override protected val delegate = fromVectorBy(_1)(function.equal)
}

case class FromVectorBy[A](_1: Vector[A], _2: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val wsize = _1.size
        if (end - start < wsize) {
            FAILURE
        } else if (vector.stl.Equal(_1, _1.start, _1.end, v, start, _2)) {
            start + wsize
        } else {
            FAILURE
        }
    }

    override def width = _1.size
}
