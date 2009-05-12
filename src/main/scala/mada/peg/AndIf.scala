

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object AndIf3 {
    def apply[A](p: Peg[A], pred: vector.Pred3[A]): Peg[A] = new AndIf3Peg(p, pred)
}

private[mada] class AndIf3Peg[A](override val self: Peg[A], pred: vector.Pred3[A]) extends Forwarder[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = self.parse(v, start, end)
        if (cur == FAILURE || !pred(v, start, cur)) {
            FAILURE
        } else {
            cur
        }
    }
}
