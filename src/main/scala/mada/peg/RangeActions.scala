

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `startAt( SymbolMap("abc" --> endWith(f) >> "def") )`

/**
 * Associates actions where Peg can't be placed.
 */
class RangeActions[A] {
    private val stack = new java.util.ArrayDeque[Int]

    /**
     * Alias of <code>startAt</code>
     */
    def apply(p: Peg[A]): Peg[A] = startAt(p)

    /**
     * Marks starting point of actions.
     */
    def startAt(p: Peg[A]): Peg[A] = new StartAtPeg(p)

    private class StartAtPeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            stack.push(start)
            self.parse(v, start, end)
        }
    }

    /**
     * Triggers the action.
     */
    def endWith(f: Vector.Func[A, Any]): Peg[A] = new EndWithPeg(f)

    private class EndWithPeg(f: Vector.Func[A, Any]) extends Peg[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            f(v(stack.pop, start))
            start
        }
        override def length = 0
    }
}
