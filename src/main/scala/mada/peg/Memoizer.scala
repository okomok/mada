

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


/**
 * Provides memoization functionality.
 * Results of parsing different sequence.vectors are not memoized.
 *
 * @param   input   the target sequence.vector of this memoization
 */
class Memoizer[A](val input: sequence.Vector[A]) {
    @aliasOf("memoize")
    final def apply(p: Peg[A]): Peg[A] = memoize(p)

    /**
     * A peg to return the memoized result when input sequence.vector is the same.
     */
    def memoize(p: Peg[A]): Peg[A] = Memoize(p)

    case class Memoize(_1: Peg[A]) extends Peg[A] {
        private val memoTable = new java.util.concurrent.ConcurrentHashMap[Pair[Int, Int], () => Int]

        override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
            if (v.regionBase eq input.regionBase) {
                assoc.lazyGet(memoTable)(Pair(start, end)){ _1.parse(v, start, end) }
            } else {
                _1.parse(v, start, end)
            }
        }
        override def width = _1.width
    }
}
