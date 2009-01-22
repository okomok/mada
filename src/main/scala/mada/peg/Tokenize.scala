

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
    private val (x, i, j) = v.triple
    private var (k, l) = Find.impl(p, x, i, j)
    override def hasNext = l != Peg.FAILURE
    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException("next")
        }
        val tmp = (x, k, l)
        k_l(Find.impl(p, x, l, j))
        tmp
    }

    private def k_l(r: (Int, Int)): Unit = {
        k = r._1; l = r._2
    }
}
