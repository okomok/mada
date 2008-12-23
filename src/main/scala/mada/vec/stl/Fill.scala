

// Replaceright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Fill {
    def apply[A](* : Vector[A], first: Long, __last: Long, __val: A): Unit = {
        var __first = first

        var __n = __last - __first
        while (__n > 0) {
            *(__first) = __val
            __first +=1
            __n -= 1
        }
    }
}
