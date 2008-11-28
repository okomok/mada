
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


package madatest.rng.detail


import mada.Assert
import mada.rng._
import mada.rng.From._
import mada.rng.Pointer._
import junit.framework.Assert._


object Search {
    def apply[A1, A2](__first1: Pointer[A1], __last1: Pointer[A1], __first2: Pointer[A2], __last2: Pointer[A2], __predicate: (A1, A2) => Boolean): Pointer[A1] = {
        Assert("requires ForwardPointer", __first1.traversal <:< ForwardTraversal)

        // Test for empty ranges
        if (__first1 == __last1 || __first2 == __last2)
            return __first1

        // Test for a pattern of length 1.
        val __tmp = __first2.copy
        ++(__tmp)
        if (__tmp == __last2) {
            while (__first1 != __last1 && !__predicate(*(__first1), *(__first2)))
                ++(__first1)
            return __first1
        }

        // General case.

        var __p1: Pointer[A2] = null; var __p: Pointer[A2] = null

        __p1 = __first2.copy; ++(__p1)

        var __current = __first1.copy

        while (__first1 != __last1) {
            while (__first1 != __last1 && !__predicate(*(__first1), *(__first2)))
                ++(__first1)
            if (__first1 == __last1)
                return __last1;

            __p = __p1.copy
            __current = __first1.copy
            if (++(__current) == __last1) return __last1

            while (__predicate(*(__current), *(__p))) {
                if (++(__p) == __last2)
                    return __first1
                if (++(__current) == __last1)
                    return __last1
            }

            ++(__first1)
        }
        __first1
    }

    def apply[A1, A2](r1: Rng[A1], r2: Rng[A2], f: (A1, A2) => Boolean): Pointer[A1] = apply(r1.begin, r1.end, r2.begin, r2.end, f)
    def apply[A](p1: Pointer[A], q1: Pointer[A], p2: Pointer[A], q2: Pointer[A]): Pointer[A] = apply(p1, q1, p2, q2, (_: A) == (_: A))
    def apply[A](r1: Rng[A], r2: Rng[A]): Pointer[A] = apply(r1, r2, (_: A) == (_: A))
}


class SearchTest {
    def testTrivial {
        val r1 = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)).eval
        val r2 = from(Array(0,12,15,11)).eval
        val p1 = Search(r1, r2)
        assertEquals(p1, r1.begin + 10)
    }
}
