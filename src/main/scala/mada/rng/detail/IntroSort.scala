

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


import Pointer._


object IntroSort {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean) = new IntroSortImpl(__comp).apply(r)
}


class IntroSortImpl[A](__comp: (A, A) => Boolean) {
    private val __stl_threshold = 16

    def apply(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        if (__first != __last) {
            loop(__first <=< __last, lg(__last - __first) * 2)
            finalInsertionSort(__first <=< __last)
        }
    }

    // See: http://marc.info/?l=apache-stdcxx-dev&m=120120284610472&w=2
    private def loop(r: Rng[A], depth_limit: Long): Unit = {
        var (__first, __last) = r.toPair
        var __depth_limit = depth_limit

        while (__last - __first > __stl_threshold) {
            if (__depth_limit == 0) {
//                val start = java.lang.System.currentTimeMillis
                PartialSort(__first, __last, __last, __comp)
//                val elapsed = java.lang.System.currentTimeMillis - start
//                if (elapsed != 0) { println("PartialSort: " + elapsed) }
                return
            }
//            val start = java.lang.System.currentTimeMillis
            val __cut = UnguardedPartition(__first <=< __last, Median(*(__first), *(__first + (__last - __first)/2), *(__last - 1), __comp), __comp)
//            val elapsed = java.lang.System.currentTimeMillis - start
//            if (elapsed != 0) { println("UnguardedPartition: " + elapsed) }
            __depth_limit /= 2
            loop(__cut <=< __last, __depth_limit)
            __last = __cut
        }
    }

    private def finalInsertionSort(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        if (__last - __first > __stl_threshold) {
            InsertionSort(__first <=< __first + __stl_threshold, __comp)
            UnguardedInsertionSort(__first + __stl_threshold <=< __last, __comp)
        } else {
            InsertionSort(__first <=< __last, __comp)
        }
    }

    private def lg(n: Long): Long = {
        var __n = n
        var __k = 0L
        while (__n != 1) {
            __k += 1
            __n >>= 1
        }
        __k
    }
}
