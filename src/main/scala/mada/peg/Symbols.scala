

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


package mada.peg


object Symbols {
    def apply[A](vs: Vector[A]*)(implicit c: A => Ordered[A]): Parser[A] = new Symbols(vs.elements, vec.stl.Less(c))
}

class Symbols[A](vs: Iterator[Vector[A]], lt: (A, A) => Boolean) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        tree.parse(v, first, last)
    }

    private val tree = {
        val t = new detail.TSTree[A, Unit](lt)
        vs.foreach(t.put(_, ()))
        t
    }
}
