

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Find {
    def apply[A](p: Peg[A], v: Vector[A]): Option[Vector[A]] = {
        val (i, j) = impl(p, v, v.start, v.end)
        if (j == FAILURE) {
            None
        } else {
            Some(v(i, j))
        }
    }

    def impl[A](p: Peg[A], v: Vector[A], _start: Int, end: Int): (Int, Int) = {
        var start = _start
        while (start != end) {
            val cur = p.parse(v, start, end)
            if (cur != FAILURE) {
                return (start, cur)
            }
            start += 1
        }
        (start, FAILURE)
    }
}
