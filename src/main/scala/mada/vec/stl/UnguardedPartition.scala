

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


object UnguardedPartition {
    def apply[A](* : Vector[A], first: Int, last: Int, __pivot: A, __comp: (A, A) => Boolean): Int = {
        var __first = first; var __last = last

        while (true) {
            while (__comp(*(__first), __pivot)) {
                __first += 1
            }
            __last -= 1
            while (__comp(__pivot, *(__last))) {
                __last -= 1
            }
            if (!(__first < __last)) {
                return __first
            }
            IterSwap(*, __first, *, __last)
            __first += 1
        }

        throw new Error("unreachable")
    }
}
