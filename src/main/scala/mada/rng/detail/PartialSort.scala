

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
