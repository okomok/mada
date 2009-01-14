

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `future(f)( symbolMap("abc" --> join >> "def") )`

class FutureActions[A] {
    val stack = new java.util.ArrayDeque[(Vector[A] => Any, Long)]

    def future(f: Vector[A] => Any)(p: Peg[A]): Peg[A] = new FuturePeg(f, p)
    def join: Peg[A] = new JoinPeg

    class FuturePeg(f: Vector[A] => Any, override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            stack.push((f, first))
            self.parse(v, first, last)
        }
    }

    class JoinPeg extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            val (f, i) = stack.pop
            f(v.window(i, first))
            first
        }
        override def length = 0
    }
}
