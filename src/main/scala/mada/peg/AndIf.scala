

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object AndIf {
    def apply[A](p: Peg[A], pred: Vector.Func[A, Boolean]): Peg[A] = new AndIfPeg(p, pred)
}

private[mada] class AndIfPeg[A](override val self: Peg[A], pred: Vector.Func[A, Boolean]) extends PegProxy[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = self.parse(v, start, end)
        if (cur == Peg.FAILURE || !pred(v(start, cur))) {
            Peg.FAILURE
        } else {
            cur
        }
    }
}
