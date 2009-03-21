

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Sort {
    def apply[A](v: Vector[A], lt: Compare.Func[A]): Vector[A] = {
        stl.Sort(v, v.start, v.end, lt)
        v
    }
}

private[mada] object StableSort {
    def apply[A](v: Vector[A], lt: Compare.Func[A]): Vector[A] = {
        stl.StableSort(v, v.start, v.end, lt)
        v
    }
}
