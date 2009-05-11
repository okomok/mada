

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
    @aliasOf("memoize")
    final def apply(p: Peg[A]): Peg[A] = memoize(p)

    /**
     * A peg to return the memoized result when input vector is the same.
     */
    def memoize(p: Peg[A]): Peg[A] = new MemoizePeg(p)

    private class MemoizePeg(override val self: Peg[A]) extends PegProxy[A] {
        private val memoTable = new java.util.concurrent.ConcurrentHashMap[Pair[Int, Int], () => Int]

        override def parse(v: Vector[A], start: Int, end: Int) = {
            if (v.regionBase eq input.regionBase) {
                Maps.lazyGet(memoTable)(Pair(start, end)){ self.parse(v, start, end) }
            } else {
                self.parse(v, start, end)
            }
        }
    }
}
