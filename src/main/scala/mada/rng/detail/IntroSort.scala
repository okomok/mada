

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


package mada.rng.detail


object IntroSort {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean): Unit = {
        val (__*, __first, __last) = r.toTriple

        if (__first != __last) {
            loop(__*, __first, __last, lg(__last - __first) * 2, __comp)
            finalInsertionSort(__*, __first, __last, __comp)
        }
    }

    def loop[A](* : Pointer[A], __first: Long, last: Long, depth_limit: Long, __comp: (A, A) => Boolean): Unit = {
        var __last = last
        var __depth_limit = depth_limit

        while (__last - __first > __stl_threshold) {
            if (__depth_limit == 0) {
                PartialSort(*, __first, __last, __last, __comp)
                return
            }
            val __cut = UnguardedPartition(*, __first, __last, Median(*(__first), *(__first + (__last - __first)/2), *(__last - 1), __comp), __comp)
            __depth_limit /= 2 // See: http://marc.info/?l=apache-stdcxx-dev&m=120120284610472&w=2
            loop(*, __cut, __last, __depth_limit, __comp)
            __last = __cut
        }
    }

    def finalInsertionSort[A](* : Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        if (__last - __first > __stl_threshold) {
            InsertionSort(*, __first, __first + __stl_threshold, __comp)
            InsertionSort.unguarded(*, __first + __stl_threshold, __last, __comp)
        } else {
            InsertionSort(*, __first, __last, __comp)
        }
    }

    val __stl_threshold = 16

    def lg(n: Long): Long = {
        var __n = n
        var __k = 0L
        while (__n != 1) {
            __k += 1
            __n >>= 1
        }
        __k
    }
}
