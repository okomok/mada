

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Copy {
    def apply[A, B >: A](v : Vector[A], __first: Long, __last: Long, * : Vector[B], result: Long): Long = {
        var __result = result
        v.stlForEach(__first, __last, { (e: A) => *(__result) = e; __result += 1 } )
        __result
    }
}


object CopyIf {
    def apply[A, B >: A](v : Vector[A], __first: Long, __last: Long, * : Vector[B], result: Long, __pred: A => Boolean): Long = {
        var __result = result
        v.stlForEach(__first, __last, { (e: A) => if (__pred(e)) { *(__result) = e; __result += 1 } })
        __result
    }
}
