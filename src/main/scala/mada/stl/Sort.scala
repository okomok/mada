

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stl


private[mada] object Sort {
    def apply[A](v: Vector[A], __first: Int, __last: Int)(implicit c: A => Ordered[A]): Unit = {
        apply(v, __first, __last, Functions.less(c))
    }

    def apply[A](v: Vector[A], __first: Int, __last: Int, __comp: (A, A) => Boolean): Unit = {
        IntroSort(v, __first, __last, __comp)
    }
}
