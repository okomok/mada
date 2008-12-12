

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
    def make[A](r: Rng[A], __comp: (A, A) => Boolean) = new Heap(__comp).make(r)
    def sort[A](r: Rng[A], __comp: (A, A) => Boolean) = new Heap(__comp).sort(r)
}


class Heap[A](__comp: (A, A) => Boolean) {
// adjust
    def adjust(first: Pointer[A], holeIndex: Long, __len: Long, __value: A): Unit = {
        val __first = first.copy
        var __holeIndex = holeIndex

        val __topIndex = __holeIndex;
        var __secondChild = 2 * __holeIndex + 2;
        while (__secondChild < __len) {
            if (__comp(*(__first + __secondChild), *(__first + (__secondChild - 1)))) {
                __secondChild -= 1
            }
            *(__first + __holeIndex) = *(__first + __secondChild);
            __holeIndex = __secondChild;
            __secondChild = 2 * (__secondChild + 1);
        }
        if (__secondChild == __len) {
            *(__first + __holeIndex) = *(__first + (__secondChild - 1))
            __holeIndex = __secondChild - 1
        }
        __push(__first, __holeIndex, __topIndex, __value)
    }

// make
    def make(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        if (__last - __first < 2) {
            return
        }
        val __len = __last - __first
        var __parent = (__len - 2)/2

        while (true) {
            adjust(__first, __parent, __len, *(__first + __parent))
            if (__parent == 0) {
                return
            }
            __parent -= 1
        }
    }

// push
    def push(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        __push(__first, (__last - __first) - 1, 0, *(__last - 1))
    }

    def __push(__first: Pointer[A], holeIndex: Long, __topIndex: Long, __value: A): Unit = {
        var __holeIndex = holeIndex

        var __parent = (__holeIndex - 1) / 2
        while (__holeIndex > __topIndex && __comp(*(__first + __parent), __value)) {
            *(__first + __holeIndex) = *(__first + __parent)
            __holeIndex = __parent
            __parent = (__holeIndex - 1) / 2
        }
        *(__first + __holeIndex) = __value
    }

// pop
    def pop(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        __pop(__first <=< __last - 1, __last - 1, *(__last - 1))
    }

    def __pop(r: Rng[A], __result: Pointer[A], __value: A): Unit = {
        val (__first, __last) = r.toPair

        *(__result) = *(__first)
        adjust(__first, 0, __last - __first, __value);
    }

// sort
    def sort(r: Rng[A]): Unit = {
        val (__first, __last) = r.toPair

        while (__last - __first > 1) {
            pop(__first <=< (__last.--))
        }
    }
}
