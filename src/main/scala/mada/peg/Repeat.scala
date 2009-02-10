

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Repeat {
    def apply[A](p: Peg[A], n: Int, m: Int): Quantifier[A] = {
        if (n < 0 || n > m) {
            throw new IllegalArgumentException("repeat" + (n, m))
        }
        new RepeatPeg(p, n, m)
    }
}


private[mada] class RepeatPeg[A](p: Peg[A], n: Int, m: Int) extends PegProxy[A] with Quantifier[A] {
    private val prefix = new RepeatExactlyPeg(p, n)
    override val self = prefix >> new RepeatAtMostPeg(p, m - n)
    override def until(that: Peg[A]) = prefix >> new RepeatAtMostUntilPeg(p, m - n, that)
}

private[mada] class RepeatExactlyPeg[A](p: Peg[A], n: Int) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        var cur = start
        var i = 0
        while (i != n) {
            cur = p.parse(v, cur, end)
            if (cur == Peg.FAILURE) {
                return Peg.FAILURE
            }
            i += 1
        }
        cur
    }

    override def width = p.width * n
}

private[mada] class RepeatAtMostPeg[A](p: Peg[A], n: Int) extends Peg[A] {
    // RepeatAtMostUntilPeg(p, n, !p) would include redandunt parsing.
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        var cur = start
        var i = 0
        while (i != n && cur != end) {
            val next = p.parse(v, cur, end)
            if (next == Peg.FAILURE) {
                return cur
            }
            cur = next
            i += 1
        }
        cur
    }
}

private[mada] class RepeatAtMostUntilPeg[A](p: Peg[A], n: Int, q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int): Int = {
        var cur = start
        var i = 0
        var next = q.parse(v, cur, end)
        while (i != n && next == Peg.FAILURE) {
            next = p.parse(v, cur, end)
            if (next == Peg.FAILURE) {
                return Peg.FAILURE
            }
            cur = next
            i += 1
            next = q.parse(v, cur, end)
        }
        next
    }
}
