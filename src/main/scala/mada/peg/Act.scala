

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Act {
    def apply[A](p: Peg[A], f: (Vector[A], Long, Long) => Any): Peg[A] = new ActPeg(p, f)
}

class ActPeg[A](override val self: Peg[A], f: (Vector[A], Long, Long) => Any) extends PegProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long) = {
        val cur = self.parse(v, first, last)
        if (cur != Peg.FAILURE) {
            f(v, first, cur)
        }
        cur
    }
}
