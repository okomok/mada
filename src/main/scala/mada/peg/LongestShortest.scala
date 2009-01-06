

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Longest {
    def apply[A](ps: Peg[A]*): Peg[A] = new LongestPeg(ps: _*)
}

class LongestPeg[A](ps: Peg[A]*) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = FAILED
        for (p <- ps) {
            val i = p.parse(v, first, last)
            if (i != FAILED) {
                cur = Math.max(cur, i)
            }
        }
        cur
        // ps.foldLeft(FAILED)({ (a: Long, p: Peg[A]) => Math.max(a, p.parse(v, first, last)) })
    }
}


object Shortest {
    def apply[A](ps: Peg[A]*): Peg[A] = new ShortestPeg(ps: _*)
}

class ShortestPeg[A](ps: Peg[A]*) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = FAILED
        for (p <- ps) {
            val i = p.parse(v, first, last)
            if (i != FAILED) {
                cur = Math.min(cur, i)
            }
        }
        cur
    }
}
