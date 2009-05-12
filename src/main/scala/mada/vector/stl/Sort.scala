

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector.stl


private[mada] object Sort {
    def apply[A](v: Vector[A], __first: Int, __last: Int, __comp: compare.Func[A]): Unit = {
        IntroSort(v, __first, __last, __comp)
    }
}
