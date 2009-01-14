

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `from( symbolMap("abc" --> until(f) >> "def") )`

class RangeActions[A] {
    val stack = new java.util.ArrayDeque[Long]

    def from(p: Peg[A]): FromPeg = new FromPeg(p)

    class FromPeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            stack.push(first)
            self.parse(v, first, last)
        }
    }

    def until(f: Vector[A] => Any): UntilPeg = until(Vector.triplify(f))
    def until(f: (Vector[A], Long, Long) => Any): UntilPeg = new UntilPeg(f)

    class UntilPeg(f: (Vector[A], Long, Long) => Any) extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            f(v, stack.pop, first)
            first
        }
        override def length = 0
    }
}
