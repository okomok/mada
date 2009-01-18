

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `start( symbolMap("abc" --> endBy(f) >> "def") )`

class RangeActions[A] {
    val stack = new java.util.ArrayDeque[Long]

    def apply(p: Peg[A]): Peg[A] = start(p)
    def start(p: Peg[A]): Peg[A] = new StartPeg(p)

    class StartPeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long) = {
            stack.push(first)
            self.parse(v, first, last)
        }
    }

    def endBy(f: Vector.Func3[A, Any]): Peg[A] = new EndByPeg(f)

    class EndByPeg(f: Vector.Func3[A, Any]) extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long) = {
            f(v, stack.pop, first)
            first
        }
        override def length = 0
    }
}
