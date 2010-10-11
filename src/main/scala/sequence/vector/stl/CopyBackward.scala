

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


package com.github.okomok.mada
package sequence; package vector; package stl


private[vector]
object CopyBackward {
    def apply[A, B >: A](v : Vector[A], __first: Int, last: Int, ^ : Vector[B], result: Int): Int = {
        var __last = last
        var __result = result

        var __n = __last - __first
        while (__n > 0) {
            __result -= 1; __last -= 1
            ^(__result) = v(__last)
            __n -= 1
        }
        __result
    }
}
