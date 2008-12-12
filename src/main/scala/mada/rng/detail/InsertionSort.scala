

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

        if (__first == __last) {
            return
        }
        val __i = __first + 1
        while (__i != __last) {
            linearInsert(__first <=< __i, *(__i))
            ++(__i)
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

    private def linearInsert(r: Rng[A], __val: A): Unit = {
        val (__first, __last) = r.toPair
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
