

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stl


/**
 * Counts calls of <code>Vector#update</code>.
 */
class OutputCounter(val start: Int) extends Vector[Any] {
    /**
     * Starts from count <code>0</code>.
     */
    def this() = this(0)

    private var c = start

    /**
     * Throws <code>UnsupportedOperationException</code>.
     */
    override def size = throw new UnsupportedOperationException("OutputCounter.size")

    /**
     * Increments counter.
     */
    override def update(i: Int, e: Any) = c += 1

    /**
     * Returns the result of counting.
     */
    def count: Int = c
}
