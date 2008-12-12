

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


import CopyBackwardTo._
import Pointer._


object InsertionSort {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean) = new InsertionSort(__comp).apply(r)
}

object UnguardedInsertionSort {
    def apply[A](r: Rng[A], __comp: (A, A) => Boolean) = new InsertionSort(__comp).unguarded(r)
}


class InsertionSort[A](__comp: (A, A) => Boolean) {
    def apply(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair
        if (__first != __last) {
            val __i = __first + 1
            while (__i != __last) {
                linearInsert(__first <=< __i)
                ++(__i)
            }
        }
    }

    def unguarded(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair
        val __i = __first
        while (__i != __last) {
            unguardedLinearInsert(__i, *(__i))
            ++(__i)
        }
    }

    private def linearInsert(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair
        val __val = *(__last)
        if (__comp(__val, *(__first))) {
            (__first <=< __last)./.copyBackwardTo(__last + 1)./
            *(__first) = __val
        } else {
            unguardedLinearInsert(__last, __val)
        }
    }

    private def unguardedLinearInsert(last: Pointer[A], __val: A): Unit = {
        var __last = last.copy
        val __next = __last.copy
        --(__next)
        while (__comp(__val, *(__next))) {
            *(__last) = *(__next)
            __last = __next.copy
            --(__next)
        }
        *(__last) = __val
    }
}
