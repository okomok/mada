

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


package mada.rng.stl.detail


object InsertionSort {
    def apply[A](* : Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        if (__first == __last) {
            return
        }
        var __i = __first + 1
        while (__i != __last) {
            linearInsert(*, __first, __i, *(__i), __comp)
            __i += 1
        }
    }

    def linearInsert[A](* : Pointer[A], __first: Long, __last: Long, __val: A, __comp: (A, A) => Boolean): Unit = {
        if (__comp(__val, *(__first))) {
            CopyBackwardImpl.inRandomAccess(*, __first, __last, __last + 1)
            *(__first) = __val
        } else {
            unguardedLinearInsert(*, __last, __val, __comp)
        }
    }

    def unguarded[A](* : Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        var __i = __first
        while (__i != __last) {
            unguardedLinearInsert(*, __i, *(__i), __comp)
            __i += 1
        }
    }

    def unguardedLinearInsert[A](* : Pointer[A], last: Long, __val: A, __comp: (A, A) => Boolean): Unit = {
        var __last = last
        var __next = __last

        __next -= 1
        while (__comp(__val, *(__next))) {
            *(__last) = *(__next)
            __last = __next
            __next -= 1
        }
        *(__last) = __val
    }
}
