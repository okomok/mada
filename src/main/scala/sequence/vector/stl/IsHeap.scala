

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


private
object IsHeap {
    def apply[A](v: Vector[A], __first: Int, __last: Int, __comp: Ordering[A]): Boolean = {
        __apply(v, __first, __comp, __last - __first)
    }

    def __apply[A](* : Vector[A], __first: Int, __comp: Ordering[A], __n: Int): Boolean = {
        var __parent = 0
        var __child = 1
        while (__child < __n) {
            if (__comp.lt(*(__first + __parent), *(__first + __child))) {
                return false
            }
            if ((__child & 1) == 0) {
                __parent += 1
            }
            __child += 1
        }
        true
    }
}
