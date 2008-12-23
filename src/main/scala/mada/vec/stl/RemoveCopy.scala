

// RemoveCopyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object RemoveCopy {
    def apply[A, B >: A](v : Vector[A], __first: Long, __last: Long, ^ : Vector[B], __result: Long, e: Any): Long = {
        RemoveCopyIf(v, __first, __last, ^, __result, (_: A) == e)
    }
}

object RemoveCopyIf {
    def apply[A, B >: A](v : Vector[A], __first: Long, __last: Long, ^ : Vector[B], __result: Long, __pred: A => Boolean): Long = {
        CopyIf(v, __first, __last, ^, __result, !__pred(_: A))
    }
}
