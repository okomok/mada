

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Find {
    def apply[A](p: Peg[A], v: Vector[A]): Option[(Int, Int)] = {
        val (first, last) = v.pair
        val (i, j) = impl(p, v, first, last)
        if (j == Peg.FAILURE) {
            None
        } else {
            Some((i, j))
        }
    }

    def impl[A](p: Peg[A], v: Vector[A], _first: Int, last: Int): (Int, Int) = {
        var first = _first
        while (first != last) {
            val cur = p.parse(v, first, last)
            if (cur != Peg.FAILURE) {
                return (first, cur)
            }
            first += 1
        }
        (first, Peg.FAILURE)
    }
}
