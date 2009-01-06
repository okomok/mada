

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Single {
    def apply[A](e: A): Peg[A] = new SinglePeg(e)
}

class SinglePeg[A](e: A) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (first == last || e != v(first)) {
            FAILED
        } else {
            first + 1
        }
    }

    override def length = 1
}
