

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Provides memoization functionality.
 * Results of parsing different vectors are not memoized.
 *
 * @param   input   the target vector of this memoization
 */
class Memoizer[A](val input: Vector[A]) {
    /**
     * Alias of <code>memoize</code>
     */
    final def apply(p: Peg[A]): Peg[A] = memoize(p)

    /**
     * A peg to return the memoized result when input region is the same.
     */
    def memoize(p: Peg[A]): Peg[A] = new MemoizePeg(p)

    private class MemoizePeg(override val self: Peg[A]) extends PegProxy[A] {
        private val memoTable = new scala.collection.jcl.HashMap[Pair[Int, Int], Int]

        override def parse(v: Vector[A], start: Int, end: Int) = {
            if (regionBase(v) eq regionBase(input)) {
                val key = Pair(start, end)
                val value = memoTable.get(key)
                if (value.isEmpty) {
                    val cur = self.parse(v, start, end)
                    memoTable.put(key, cur)
                    cur
                } else {
                    value.get
                }
            } else {
                self.parse(v, start, end)
            }
        }
    }

    private def regionBase[A](v: Vector[A]): Vector[A] = v match {
        case Vector.Region(w, _, _) => w
        case _ => v
    }
}
