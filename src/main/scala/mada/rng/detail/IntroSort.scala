

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.detail


/*
 *
 * Copyright (c) 1994
 * Hewlett-Packard Company
 *
 * Permission to use, copy, modify, distribute and sell this software
 * and its documentation for any purpose is hereby granted without fee,
 * provided that the above copyright notice appear in all copies and
 * that both that copyright notice and this permission notice appear
 * in supporting documentation.  Hewlett-Packard Company makes no
 * representations about the suitability of this software for any
 * purpose.  It is provided "as is" without express or implied warranty.
 *
 *
 * Copyright (c) 1996
 * Silicon Graphics Computer Systems, Inc.
 *
 * Permission to use, copy, modify, distribute and sell this software
 * and its documentation for any purpose is hereby granted without fee,
 * provided that the above copyright notice appear in all copies and
 * that both that copyright notice and this permission notice appear
 * in supporting documentation.  Silicon Graphics makes no
 * representations about the suitability of this software for any
 * purpose.  It is provided "as is" without express or implied warranty.
 */


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

    private def loop(r: Rng[A], depth_limit: Long): Unit = {
        var (__first, __last) = r.toPair
        var __depth_limit = depth_limit

        while (__last - __first > __stl_threshold) {
            if (__depth_limit == 0L) {
                PartialSort(__first, __last, __last, __comp)
                return
            }
            __depth_limit += 1
            val med = Median(*(__first), *(__first + (__last - __first)/2), *(__last - 1), __comp)
            val __cut = UnguardedPartition(__first <=< __last, med, __comp)
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
