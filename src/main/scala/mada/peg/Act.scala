

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Act3 {
    def apply[A](p: Peg[A], f: Action3[A]): Peg[A] = new Act3Peg(p, f)
}

private[mada] class Act3Peg[A](override val self: Peg[A], f: Action3[A]) extends Forwarder[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = self.parse(v, start, end)
        if (cur != FAILURE) {
            f(v, start, cur)
        }
        cur
    }
}
