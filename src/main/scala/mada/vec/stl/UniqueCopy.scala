

// Uniqueright Shunsuke Sogame 2008-2009.
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


object UniqueCopy {
    def apply[A, B >: A](v : Vector[A], first: Long, __last: Long, ^ : Vector[B], result: Long): Long = {
        apply(v, first, __last, ^, result, EqualTo)
    }

    def apply[A, B >: A](v : Vector[A], first: Long, __last: Long, ^ : Vector[B], result: Long, __binary_pred: (A, B) => Boolean): Long = {
        var __first = first
        var __result = result

        var __val = v(__first)
        ^(__result) = __val
        __first += 1
        while (__first != __last) {
            if (!__binary_pred(__val, v(__first))) {
                __val = v(__first)
                __result += 1; ^(__result) = __val
            }
            __first += 1
        }
        __result += 1
        __result
    }
}
