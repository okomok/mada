

// Replaceright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


/*
 *
 * Copyright (c) 1994
 * Hewlett-Packard Company
 *
 * Copyright (c) 1996,1997
 * Silicon Graphics Computer Systems, Inc.
 *
 * Copyright (c) 1997
 * Moscow Center for SPARC Technology
 *
 * Copyright (c) 1999
 * Boris Fomitchev
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 * Permission to use or copy this software for any purpose is hereby granted
 * without fee, provided the above notices are retained on all copies.
 * Permission to modify the code and to distribute modified code is granted,
 * provided the above notices are retained, and a notice that the code was
 * modified is included with the above copyright notice.
 *
 */


package mada.vec.stl


object Transform {
    def apply[A, B](v : Vector[A], first: Long, __last: Long, ^ : Vector[B], result: Long, __opr: A => B): Long = {
        var __first = first
        var __result = result

        while(__first != __last) {
            ^(__result) = __opr(v(__first))
            __first += 1; __result += 1
        }
        __result
    }

    def apply[A, B, C](v1 : Vector[A], first1: Long, __last1: Long, v2 : Vector[B], first2: Long,  ^ : Vector[C], result: Long, __binary_op: (A, B) => C): Long = {
        var __first1 = first1
        var __first2 = first2
        var __result = result

        while(__first1 != __last1) {
            ^(__result) = __binary_op(v1(__first1), v2(__first2))
            __first1 += 1; __first2 += 1; __result += 1
        }
        __result
    }
}
