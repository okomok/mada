

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Act {
    def apply[A](p: Peg[A], f: Vector.Func3[A, Any]): Peg[A] = new ActPeg(p, f)
}

class ActPeg[A](override val self: Peg[A], f: Vector.Func3[A, Any]) extends PegProxy[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = self.parse(v, start, end)
        if (cur != Peg.FAILURE) {
            f(v, start, cur)
        }
        cur
    }
}
