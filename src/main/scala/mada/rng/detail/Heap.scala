
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


object Heap {
    def make[A](r: Rng[A], __comp: (A, A) => Boolean) = new HeapImpl(__comp).make(r)
    def sort[A](r: Rng[A], __comp: (A, A) => Boolean) = new HeapImpl(__comp).sort(r)
}


class HeapImpl[A](__comp: (A, A) => Boolean) {
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
