

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Provides memoization functionality.
 * Parsing different input vectors is not memoized.
 *
 * @param   input the target vector of this memoization
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
        import Products.RangeVal

        val memoTable = new scala.collection.jcl.HashMap[RangeVal, Int]

        override def parse(v: Vector[A], start: Int, end: Int) = {
            if (v eq input) {
                val key = RangeVal(start, end)
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
}
