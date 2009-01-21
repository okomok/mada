

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Tokenize {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[Vector[A]] = {
        p.tokenize3(v).map({ w => Vector.tripleVector(w) })
    }
}

object Tokenize3 {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[Vector.Triple[A]] = new Tokenize3Iterator(p, v)
}

class Tokenize3Iterator[A](p: Peg[A], v: Vector[A]) extends Iterator[Vector.Triple[A]] {
    private val (x, start, end) = v.triple
    private var _1_2 = Find.impl(p, x, start, end)
    override def hasNext = _1_2._2 != Peg.FAILURE
    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException("next")
        }
        val tmp = (x, _1_2._1, _1_2._2)
        _1_2 = Find.impl(p, x, _1_2._2, end)
        tmp
    }
}
