

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


object First {
    def apply[A](v: Vector[A]): A = v(0)
}


object Last {
    def apply[A](v: Vector[A]): A = v(v.size - 1)
}
