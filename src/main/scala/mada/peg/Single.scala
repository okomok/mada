

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Single {
    def apply[A](e: A): Peg[A] = new SinglePeg(e)
}

private[mada] class SinglePeg[A](e: A) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end || e != v(start)) {
            Pegs.FAILURE
        } else {
            start + 1
        }
    }

    override def length = 1
}
