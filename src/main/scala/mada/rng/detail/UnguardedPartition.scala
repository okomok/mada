
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

        throw new Error("doh")
    }
}
