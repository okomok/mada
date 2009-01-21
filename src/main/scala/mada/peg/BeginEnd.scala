

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Begin {
    def apply[A]: Peg[A] = new BeginPeg[A]
}

class BeginPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == 0) {
            start
        } else {
            Peg.FAILURE
        }
    }

    override def length = 0
}


object End {
    def apply[A]: Peg[A] = new EndPeg[A]
}

class EndPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end) {
            start
        } else {
            Peg.FAILURE
        }
    }

    override def length = 0
}
