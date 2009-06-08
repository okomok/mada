

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


private object Precondition {

    def positive(n: Int, msg: String): Unit = {
        if (n <= 0) {
            throw new IllegalArgumentException(msg + ": " + n + " shall be positive.")
        }
    }

    def nonnegative(n: Int, msg: String): Unit = {
        if (n < 0) {
            throw new IllegalArgumentException(msg + ": " + n + " shall be nonnegative.")
        }
    }

    def range(n: Int, m: Int, msg: String): Unit = {
        if (n > m) {
            throw new IllegalArgumentException(msg + ": " + Tuple2(n, m) + " shall be a valid range.")
        }
    }

    def notEmpty[A](s: iterative.Sequence[A], msg: String): Unit = {
        if (s.asIterative.isEmpty) {
            throw new UnsupportedOperationException(msg + ": sequence shall not be empty.")
        }
    }

    def sameSize[A, B](v: Vector[A], w: Vector[B], msg: String): Unit = {
        if (v.size != w.size) {
            throw new UnsupportedOperationException(msg + ": sequences shall be the same size.")
        }
    }

}
