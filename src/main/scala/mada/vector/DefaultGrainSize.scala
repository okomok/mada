

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object DefaultGrainSize {
    def apply[A](v: Vector[A]): Int = Math.max(1, v.size / function.Future.POOL_SIZE)
}
