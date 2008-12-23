

// Replaceright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Replace {
    def apply[A](* : Vector[A], first: Long, __last: Long, __old_value: Any, __new_value: A): Unit = {
        ReplaceIf(*, first, __last, (_: A) == __old_value, __new_value)
    }
}

object ReplaceIf {
    def apply[A](* : Vector[A], first: Long, __last: Long, __pred: A => Boolean, __new_value: A): Unit = {
        var __first = first

        while (__first != __last) {
            if (__pred(*(__first))) {
                *(__first) = __new_value
            }
            __first += 1
        }
    }
}
