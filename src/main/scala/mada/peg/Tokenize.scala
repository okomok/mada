

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Tokenize {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[(Vector[A], Long, Long)] = new TokenizeIterator(p, v)
}

class TokenizeIterator[A](p: Peg[A], v: Vector[A]) extends Iterator[(Vector[A], Long, Long)] {
    private val (first, last) = v.toPair
    private var _1_2 = Find(p, v, first, last)
    override def hasNext = _1_2._2 != Peg.FAILURE
    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException("next")
        }
        val tmp = (v, _1_2._1, _1_2._2)
        _1_2 = Find(p, v, _1_2._2, last)
        tmp
    }
}
