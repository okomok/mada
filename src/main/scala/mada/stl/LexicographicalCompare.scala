

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


package mada.stl


private[mada] object LexicographicalCompare {
    def apply[A](v1: Vector[A], first1: Int, __last1: Int, v2: Vector[A], first2: Int, __last2: Int)(implicit c: Functions.OrderedView[A]): Boolean = {
        apply(v1, first1, __last1, v2, first2, __last2, Less(c))
    }

    def apply[A](v1: Vector[A], first1: Int, __last1: Int, v2: Vector[A], first2: Int, __last2: Int, __comp: Functions.Compare[A]): Boolean = {
        var __first1 = first1
        var __first2 = first2

        while ((__first1 != __last1) && (__first2 != __last2)) {
            if (__comp(v1(__first1), v2(__first2))) {
                return true
            }
            if (__comp(v2(__first2), v1(__first1))) {
                return false
            }
            __first1 += 1; __first2 += 1
        }
        return (__first1 == __last1) && (__first2 != __last2)
    }
}