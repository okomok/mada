

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


object UnguardedPartition {
    def apply[A](r: Rng[A], __pivot: A, __comp: (A, A) => Boolean): Pointer[A] = {
        val (__first, __last) = r.toPair

        while (true) {
            while (__comp(*(__first), __pivot)) {
                ++(__first)
            }
            --(__last)
            while (__comp(__pivot, *(__last))) {
                --(__last)
            }
            if (!(__first < __last)) {
                return __first
            }
            __first swap __last
            ++(__first)
        }

        throw new Error("unreachable")
    }
}
