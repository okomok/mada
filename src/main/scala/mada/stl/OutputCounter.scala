

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stl


/**
 * Counts calls of <code>Vector.update</code>.
 */
class OutputCounter(val from: Int) extends OutputVector[Any] {
    /**
     * Starts from count <code>0</code>.
     */
    def this() = this(0)

    private var c = from

    /**
     * Increments counter.
     */
    override def output(e: Any) = c += 1

    /**
     * Returns the result of counting.
     */
    def count: Int = c
}
