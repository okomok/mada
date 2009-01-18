

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object ToString {
    def apply[A](v: Vector[A]): String = v.toJclArrayList.toString
}
