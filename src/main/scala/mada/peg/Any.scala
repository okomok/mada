

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Any_ {
    def apply[A]: Peg[A] = new AnyPeg[A]
}

class AnyPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end) {
            Peg.FAILURE
        } else {
            start + 1
        }
    }

    override def length = 1
}
