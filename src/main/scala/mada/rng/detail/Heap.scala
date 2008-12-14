

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


object Heap {
    def adjust[A](at: Pointer[A], __first: Long, holeIndex: Long, __len: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        var __holeIndex = holeIndex

        val __topIndex = __holeIndex;
        var __secondChild = 2 * __holeIndex + 2
        while (__secondChild < __len) {
            if (__comp(*(at, __first + __secondChild), *(at, __first + __secondChild - 1))) {
                __secondChild -= 1
            }
            *(at, __first + __holeIndex) = *(at, __first + __secondChild)
            __holeIndex = __secondChild
            __secondChild = 2 * (__secondChild + 1)
        }
        if (__secondChild == __len) {
            *(at, __first + __holeIndex) = *(at, __first + (__secondChild - 1))
            __holeIndex = __secondChild - 1
        }
        __push(at, __first, __holeIndex, __topIndex, __value, __comp)
    }


    def make[A](at: Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        if (__last - __first < 2) {
            return
        }
        val __len = __last - __first
        var __parent = (__len - 2)/2

        while (true) {
            adjust(at, __first, __parent, __len, *(at, __first + __parent), __comp)
            if (__parent == 0) {
                return
            }
            __parent -= 1
        }
    }


    def push[A](at: Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        __push(at, __first, (__last - __first) - 1, 0, *(at, __last - 1), __comp)
    }

    def __push[A](at: Pointer[A], __first: Long, holeIndex: Long, __topIndex: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        var __holeIndex = holeIndex

        var __parent = (__holeIndex - 1) / 2
        while (__holeIndex > __topIndex && __comp(*(at, __first + __parent), __value)) {
            *(at, __first + __holeIndex) = *(at, __first + __parent)
            __holeIndex = __parent
            __parent = (__holeIndex - 1) / 2
        }
        *(at, __first + __holeIndex) = __value
    }


    def pop[A](at: Pointer[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        __pop(at, __first, __last - 1, __last - 1, *(at, __last - 1), __comp)
    }

    def __pop[A](at: Pointer[A], __first: Long, __last: Long, __result: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        *(at, __result) = *(at, __first)
        adjust(at, __first, 0, __last - __first, __value, __comp);
    }


    def sort[A](at: Pointer[A], __first: Long, last: Long, __comp: (A, A) => Boolean): Unit = {
        var __last = last

        while (__last - __first > 1) {
            pop(at, __first, __last, __comp)
            __last -= 1
        }
    }
}
