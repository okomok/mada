

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Tokenize {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[(Long, Long)] = new TokenizeIterator(p, v)
}

class TokenizeIterator[A](p: Peg[A], v: Vector[A]) extends Iterator[(Long, Long)] {
    private val (first, last) = v.toPair
    private var i_j = Find(p, v, first, last)
    override def hasNext = i_j._2 != Peg.FAILURE
    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException("next")
        }
        val result = i_j
        i_j = Find(p, v, i_j._2, last)
        result
    }
}
