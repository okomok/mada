

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Swap {
    def apply[A](* : Vector[A], i: Long, j: Long): Unit = {
        val tmp = *(i)
        *(i) = *(j)
        *(j) = tmp
    }
}
