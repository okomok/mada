

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object precondition {

    /**
     * Throws if <code>n</code> is not positive.
     */
    def positive(n: Int, msg: String): Unit = {
        if (n <= 0) {
            throw new IllegalArgumentException(msg + ": " + n + " shall be positive.")
        }
    }

    /**
     * Throws if <code>n</code> is negative.
     */
    def nonnegative(n: Int, msg: String): Unit = {
        if (n < 0) {
            throw new IllegalArgumentException(msg + ": " + n + " shall be nonnegative.")
        }
    }

    /**
     * Throws if <code>(n, m)</code> is not range.
     */
    def range(n: Int, m: Int, msg: String): Unit = {
        if (n > m) {
            throw new IllegalArgumentException(msg + ": " + Tuple2(n, m) + " shall be a valid range.")
        }
    }

    /**
     * Throws if a sequence is empty.
     */
    def notEmpty[A](s: iterative.Sequence[A], msg: String): Unit = {
        if (s.toIterative.isEmpty) {
            throw new UnsupportedOperationException(msg + ": sequence shall not be empty.")
        }
    }

    /**
     * Throws if sequences are not the same size.
     */
    def sameSize[A, B](v: Vector[A], w: Vector[B], msg: String): Unit = {
        if (v.size != w.size) {
            throw new UnsupportedOperationException(msg + ": sequences shall be the same size.")
        }
    }

}
