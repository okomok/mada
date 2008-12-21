

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Distance {
    def apply[A](v: Vector[A], __first: Long, __last: Long): Long = __last - __first
}
