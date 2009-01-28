

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stl


private[mada] object Output {
    def apply[A](f: A => Any): Vector[A] = new OutputVector(f)
}

private[mada] class OutputVector[A](f: A => Any) extends Vector[A] {
    override def size = throw new UnsupportedOperationException("OutputVector.size")
    override def update(i: Int, e: A) = f(e)
}


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
    def count = c
}
