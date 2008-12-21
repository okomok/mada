

// RemoveCopyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object RemoveCopy {
    def apply[A, B >: A](v: Vector[A], __first: Long, __last: Long, w: Vector[B], __result: Long, e: Any): Long = {
        v.stlRemoveCopyIf(__first, __last, w, __result, _ == e)
    }
}

object RemoveCopyIf {
    def apply[A, B >: A](v: Vector[A], __first: Long, __last: Long, w: Vector[B], __result: Long, __pred: A => Boolean): Long = {
        v.stlCopyIf(__first, __last, w, __result, !__pred(_: A))
    }
}
