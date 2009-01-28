

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Emulates capturing groups of regular expressions.
 */
class CapturingGroups[K, A](val map: scala.collection.mutable.Map[K, Vector[A]]) {
    def this() = this(new scala.collection.jcl.HashMap[K, Vector[A]])

    /**
     * Trivial short-cut to <code>map</code> methods
     */
    val group = new PartialFunction[K, Vector[A]] {
        /**
         * @return  <code>map.contains(k)</code>
         */
        override def isDefinedAt(k: K) = map.contains(k)

        /**
         * @return  <code>map(k)</code>
         */
        override def apply(k: K) = map(k)

        /**
         * @return  <code>map(k) = v</code>
         */
        def update(k: K, v: Vector[A]): Unit = map(k) = v
    }

    /**
     * Alias of <code>capture</code>
     */
    def apply(k: K, p: Peg[A]): Peg[A] = capture(k, p)

    /**
     * Captures matched region.
     */
    def capture(k: K, p: Peg[A]): Peg[A] = p.act({ (v, i, j) => group(k) = v(i, j) })

    /**
     * Alias of <code>backref</code>
     */
    def apply(k: K): Peg[A] = backref(k)

    /**
     * Back-reference.
     */
    def backref(k: K): Peg[A] = new BackrefPeg(k)

    private class BackrefPeg(k: K) extends PegProxy[A] {
        override def self = Peg.vectorPeg(group(k))
    }
}
