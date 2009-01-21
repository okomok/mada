

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Find {
    def apply[A](p: Peg[A], v: Vector[A]): Option[(Int, Int)] = {
        val (x, start, end) = v.triple
        val (i, j) = impl(p, x, start, end)
        if (j == Peg.FAILURE) {
            None
        } else {
            Some((i, j))
        }
    }

    def impl[A](p: Peg[A], v: Vector[A], _first: Int, end: Int): (Int, Int) = {
        var start = _first
        while (start != end) {
            val cur = p.parse(v, start, end)
            if (cur != Peg.FAILURE) {
                return (start, cur)
            }
            start += 1
        }
        (start, Peg.FAILURE)
    }
}
