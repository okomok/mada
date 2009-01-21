

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `startAt( symbolMap("abc" --> endBy(f) >> "def") )`

class RangeActions[A] {
    val stack = new java.util.ArrayDeque[Int]

    def apply(p: Peg[A]): Peg[A] = startAt(p)
    def startAt(p: Peg[A]): Peg[A] = new StartAtPeg(p)

    class StartAtPeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            stack.push(start)
            self.parse(v, start, end)
        }
    }

    def endBy(f: Vector.Func3[A, Any]): Peg[A] = new EndByPeg(f)

    class EndByPeg(f: Vector.Func3[A, Any]) extends Peg[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            f(v, stack.pop, start)
            start
        }
        override def length = 0
    }
}
