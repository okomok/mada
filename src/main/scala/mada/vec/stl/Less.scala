

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Less {
    def apply[A](implicit c: A => Ordered[A]): Less[A] = new Less(c)
}

class Less[A](c: A => Ordered[A]) extends ((A, A) => Boolean) {
    override def apply(a: A, b: A): Boolean = c(a) < b
}
