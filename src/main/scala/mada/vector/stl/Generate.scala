

// Uniqueright Shunsuke Sogame 2008-2009.
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


package mada.vector.stl


private[mada] object Generate {
    def apply[A](v : Vector[A], first: Int, __last: Int, __gen: Unit => A): Unit = {
        var __first = first

        while (__first != __last) {
            v(__first) = __gen()
            __first += 1
        }
    }
}

private[mada] object GenerateN {
    def apply[A](^ : Vector[A], first: Int, n: Int, __gen: Unit => A): Unit = {
        var __first = first
        var __n = n

        while ( __n > 0) {
            ^(__first) = __gen()
            __n -= 1; __first += 1
        }
    }
}
