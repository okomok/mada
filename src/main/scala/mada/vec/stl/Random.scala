

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


class DefaultRandomNumberGenerator extends (Long => Long) {
    private val rnd = new Random
    def apply(__n: Long) = Math.abs(rnd.nextLong % __n)
}


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
            IterSwap(v, __i, v, __first + __rand((__i - __first) + 1))
            __i += 1
        }
    }
}


object RandomSample {
    def apply[A, B >: A](v : Vector[A], first: Long, __last: Long, ^ : Vector[B], __out_first: Long, __out_last: Long): Long = {
        apply(v, first, __last, ^, __out_first, __out_last, new DefaultRandomNumberGenerator)
    }

    def apply[A, B >: A](v : Vector[A], first: Long, __last: Long, ^ : Vector[B], __out_first: Long, __out_last: Long, __rand: Long => Long): Long = {
        __apply(v, first, __last, ^, __out_first, __rand, __out_last - __out_first)
    }

    def __apply[A, B >: A](v : Vector[A], first: Long, __last: Long, ^ : Vector[B], __out_ite: Long, __rand: Long => Long, __n: Long): Long = {
        var __first = first

        var __m = 0L
        var __t = __n
        while (__first != __last && __m < __n) {
            ^(__out_ite + __m) = v(__first)
            __m += 1; __first += 1
        }

        while (__first != __last) {
            __t += 1
            val __M = __rand(__t)
            if (__M < __n) {
                ^(__out_ite + __M) = v(__first)
            }
            __first += 1
        }

        __out_ite + __m
    }
}

object RandomSampleN {
    def apply[A, B >: A](v : Vector[A], first: Long, __last: Long, ^ : Vector[B], out_ite: Long, __n: Long): Long = {
        apply(v, first, __last, ^, out_ite, __n, new DefaultRandomNumberGenerator)
    }

    def apply[A, B >: A](v : Vector[A], first: Long, __last: Long, ^ : Vector[B], out_ite: Long, __n: Long, __rand: Long => Long): Long = {
        var __first = first
        var __out_ite = out_ite

        var __remaining = __last - __first
        var __m = Math.min(__n, __remaining)

        while (__m > 0) {
            if (__rand(__remaining) < __m) {
                ^(__out_ite) = v(__first)
                __out_ite += 1
                __m -= 1
            }

            __remaining -= 1
            __first += 1
        }
        __out_ite
    }
}
