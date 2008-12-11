
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


object Median {
    def apply[A](__a: A, __b: A, __c: A, __comp: (A, A) => Boolean): A = {
        if (__comp(__a, __b)) {
            if (__comp(__b, __c)) {
                __b
            } else if (__comp(__a, __c)) {
                __c
            } else {
                __a
            }
        } else if (__comp(__a, __c)) {
            return __a
        } else if (__comp(__b, __c)) {
            return __c
        } else {
            return __b
        }
    }
}
