

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
object MinElement {
    def apply[A](* : Vector[A], first: Int, __last: Int, __comp: Ordering[A]): Int = {
        var __first = first

        if (__first == __last) {
            return __first
        }
        var __result = __first
        __first += 1
        while (__first != __last) {
            if (__comp.lt(*(__first), *(__result))) {
                __result = __first
            }
            __first += 1
        }
        __result
    }
}

private[vector]
object MaxElement {
    def apply[A](* : Vector[A], first: Int, __last: Int, __comp: Ordering[A]): Int = {
        MinElement(*, first, __last, __comp.reverse)
    }
}

