

// RemoveCopyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector; package stl


private
object RemoveCopy {
    def apply[A, B >: A](v : Vector[A], __first: Int, __last: Int, ^ : Vector[B], __result: Int, e: Any): Int = {
        RemoveCopyIf(v, __first, __last, ^, __result, (_: A) == e)
    }
}

private
object RemoveCopyIf {
    def apply[A, B >: A](v : Vector[A], __first: Int, __last: Int, ^ : Vector[B], __result: Int, __pred: A => Boolean): Int = {
        CopyIf(v, __first, __last, ^, __result, !__pred(_: A))
    }
}
