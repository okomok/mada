

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Action {
    def apply[A](p: Peg[A], f: Vector[A] => Any): Peg[A] = apply(p, Vector.triplify(f))
    def apply[A](p: Peg[A], f: (Vector[A], Long, Long) => Any): Peg[A] = new ActionPeg(p, f)
}

class ActionPeg[A](override val self: Peg[A], f: (Vector[A], Long, Long) => Any) extends PegProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val cur = self.parse(v, first, last)
        if (cur != FAILED) {
            f(v, first, last)
        }
        cur
    }
}
