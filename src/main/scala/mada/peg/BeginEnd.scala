

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Begin {
    def apply[A]: Peg[A] = new BeginPeg[A]
}

private[mada] class BeginPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == v.start) {
            start
        } else {
            Peg.FAILURE
        }
    }

    override def width = 0
}


private[mada] object End {
    def apply[A]: Peg[A] = new EndPeg[A]
}

private[mada] class EndPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end) {
            start
        } else {
            Peg.FAILURE
        }
    }

    override def width = 0
}
