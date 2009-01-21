

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object First {
    def apply[A](v: Vector[A]): A = v(0)
}

object FirstOption {
    def apply[A](v: Vector[A]): Option[A] = if (v.isEmpty) None else Some(v.first)
}


object Last {
    def apply[A](v: Vector[A]): A = v(v.size - 1)
}

object LastOption {
    def apply[A](v: Vector[A]): Option[A] = if (v.isEmpty) None else Some(v.last)
}
