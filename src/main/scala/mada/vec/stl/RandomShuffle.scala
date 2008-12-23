

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


object RandomShuffle {
    def apply[A](v: Vector[A], __first: Long, __last: Long): Unit = {
        RandomShuffle(v, __first, __last, new DefaultRandomNumberGenerator)
    }

    def apply[A](v: Vector[A], __first: Long, __last: Long, __rand: Long => Long): Unit = {
        if (__first == __last) {
            return
        }
        var __i = __first + 1
        while (__i != __last) {
            v.swap(__i, __first + __rand((__i - __first) + 1))
            __i += 1
        }
    }
}

class DefaultRandomNumberGenerator extends (Long => Long) {
    private val rnd = new java.util.Random
    def apply(__n: Long) = Math.abs(rnd.nextLong % __n)
}
