

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Sort {
    def apply[A](v: Vector[A], __first: Long, __last: Long)(implicit c: A => Ordered[A]): Unit = {
        apply(v, __first, __last, { (x: A, y: A) => c(x) < y })
    }

    def apply[A](v: Vector[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        IntroSort(v, __first, __last, __comp)
    }
}
