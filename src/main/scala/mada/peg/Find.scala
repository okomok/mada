

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Find {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[(Long, Long)] = new FindIterator(p, v)
}

class FindIterator[A](p: Peg[A], v: Vector[A]) extends Iterator[(Long, Long)] {
    private var (first, last) = v.toPair
    private var cur = findNext
    override def hasNext = cur != Peg.FAILURE
    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException
        }
        val result = (first, cur)
        first = cur
        cur = findNext
        result
    }

    private def findNext: Long = {
        while (first != last) {
            val cur = p.parse(v, first, last)
            if (cur != Peg.FAILURE) {
                return cur
            }
            first += 1
        }
        Peg.FAILURE
    }
}
