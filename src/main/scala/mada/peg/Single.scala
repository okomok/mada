

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Single {
    def apply[A](e: A): Peg[A] = new SinglePeg(e)
}

class SinglePeg[A](e: A) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end || e != v(start)) {
            Peg.FAILURE
        } else {
            start + 1
        }
    }

    override def length = 1
}
