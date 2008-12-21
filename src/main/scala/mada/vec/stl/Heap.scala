

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


package mada.vec.stl


// PushHeap

object PushHeap {
    def apply[A](* : Vector[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        __apply(*, __first, (__last - __first) - 1, 0, *(__last - 1), __comp)
    }

    def __apply[A](* : Vector[A], __first: Long, holeIndex: Long, __topIndex: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        var __holeIndex = holeIndex

        var __parent = (__holeIndex - 1) / 2
        while (__holeIndex > __topIndex && __comp(*(__first + __parent), __value)) {
            *(__first + __holeIndex) = *(__first + __parent)
            __holeIndex = __parent
            __parent = (__holeIndex - 1) / 2
        }
        *(__first + __holeIndex) = __value
    }
}


// PopHeap

object PopHeap {
    def apply[A](* : Vector[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        __apply(*, __first, __last - 1, __last - 1, *(__last - 1), __comp)
    }

    def __apply[A](* : Vector[A], __first: Long, __last: Long, __result: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        *(__result) = *(__first)
        AdjustHeap(*, __first, 0, __last - __first, __value, __comp);
    }
}


// MakeHeap

object MakeHeap {
    def apply[A](* : Vector[A], __first: Long, __last: Long, __comp: (A, A) => Boolean): Unit = {
        if (__last - __first < 2) {
            return
        }
        val __len = __last - __first
        var __parent = (__len - 2)/2

        while (true) {
            AdjustHeap(*, __first, __parent, __len, *(__first + __parent), __comp)
            if (__parent == 0) {
                return
            }
            __parent -= 1
        }
    }
}


// SortHeap

object SortHeap {
    def apply[A](* : Vector[A], __first: Long, last: Long, __comp: (A, A) => Boolean): Unit = {
        var __last = last

        while (__last - __first > 1) {
            *.stlPopHeap(__first, __last, __comp)
            __last -= 1
        }
    }
}


// AdjustHeap

object AdjustHeap {
    def apply[A](* : Vector[A], __first: Long, holeIndex: Long, __len: Long, __value: A, __comp: (A, A) => Boolean): Unit = {
        var __holeIndex = holeIndex

        val __topIndex = __holeIndex;
        var __secondChild = 2 * __holeIndex + 2
        while (__secondChild < __len) {
            if (__comp(*(__first + __secondChild), *(__first + __secondChild - 1))) {
                __secondChild -= 1
            }
            *(__first + __holeIndex) = *(__first + __secondChild)
            __holeIndex = __secondChild
            __secondChild = 2 * (__secondChild + 1)
        }
        if (__secondChild == __len) {
            *(__first + __holeIndex) = *(__first + (__secondChild - 1))
            __holeIndex = __secondChild - 1
        }
        PushHeap.__apply(*, __first, __holeIndex, __topIndex, __value, __comp)
    }
}
