

// Copyright Shunsuke Sogame 2008-2009.
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


package mada.stl


private[mada] object Merge {
    def apply[A](v1 : Vector[A], first1: Int, __last1: Int, v2 : Vector[A], first2: Int, __last2: Int, ^ : Vector[A], result: Int, __comp: compare.Func[A]): Int = {
        var __first1 = first1
        var __first2 = first2
        var __result = result

        while (__first1 != __last1 && __first2 != __last2) {
            if (__comp(v2(__first2), v1(__first1))) {
                ^(__result) = v2(__first2)
                __first2 += 1
            } else {
                ^(__result) = v1(__first1)
                __first1 += 1
            }
            __result += 1
        }
        Copy(v2, __first2, __last2, ^, Copy(v1, __first1, __last1, ^, __result))
    }
}


private[mada] object MergeSortWithBuffer {
    val CHUNK_SIZE = 7

    def apply[A](* : Vector[A], __first: Int, __last: Int, ^ : Vector[A], __buffer: Int, __comp: compare.Func[A]): Unit = {
        val __len = __last - __first
        val __buffer_last = __buffer + __len

        var __step_size = CHUNK_SIZE
        ChunkInsertionSort(*, __first, __last, __step_size, __comp)

        while (__step_size < __len) {
            loop(*, __first, __last, ^, __buffer, __step_size, __comp);
            __step_size *= 2
            loop(^, __buffer, __buffer_last, *, __first, __step_size, __comp);
            __step_size *= 2
        }
    }

    def loop[A](* : Vector[A], first: Int, __last: Int, ^ : Vector[A], result: Int, step_size: Int, __comp: compare.Func[A]): Unit = {
        var __first = first
        var __result = result
        var __step_size = step_size

        val __two_step = 2 * __step_size;

        while (__last - __first >= __two_step) {
            __result = Merge(*, __first, __first + __step_size, *, __first + __step_size, __first + __two_step, ^, __result, __comp)
            __first += __two_step;
        }
        __step_size = Math.min(__last - __first, __step_size)

        Merge(*, __first, __first + __step_size, *, __first + __step_size, __last, ^, __result, __comp)
    }
}
