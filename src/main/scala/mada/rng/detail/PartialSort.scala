
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


object PartialSort {
    def apply[A](first: Pointer[A], middle: Pointer[A], last: Pointer[A], __comp: (A, A) => Boolean): Unit = {
        val (__first, __middle, __last) = (first.copy, middle.copy, last.copy)

        Heap.make(__first <=< __middle, __comp)
        val __i = __middle.copy
        while (__i < __last) {
            if (__comp(*(__i), *(__first))) {
                new Heap(__comp).__pop(__first <=< __middle, __i, *(__i))
            }
            ++(__i)
        }
        Heap.sort(__first <=< __middle, __comp)
    }
}