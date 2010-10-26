

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
class DefaultRandomNumberGenerator extends (Int => Int) {
    private[this] val rnd = new java.util.Random
    override def apply(__n: Int) = rnd.nextInt(__n)
}


private[vector]
object RandomShuffle {
    def apply[A](v: Vector[A], __first: Int, __last: Int): Unit = {
        RandomShuffle(v, __first, __last, new DefaultRandomNumberGenerator)
    }

    def apply[A](v: Vector[A], __first: Int, __last: Int, __rand: Int => Int): Unit = {
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


private[vector]
object RandomSample {
    def apply[A, B >: A](v : Vector[A], first: Int, __last: Int, ^ : Vector[B], __out_first: Int, __out_last: Int): Int = {
        apply(v, first, __last, ^, __out_first, __out_last, new DefaultRandomNumberGenerator)
    }

    def apply[A, B >: A](v : Vector[A], first: Int, __last: Int, ^ : Vector[B], __out_first: Int, __out_last: Int, __rand: Int => Int): Int = {
        __apply(v, first, __last, ^, __out_first, __rand, __out_last - __out_first)
    }

    def __apply[A, B >: A](v : Vector[A], first: Int, __last: Int, ^ : Vector[B], __out_ite: Int, __rand: Int => Int, __n: Int): Int = {
        var __first = first

        var __m = 0
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

private[vector]
object RandomSampleN {
    def apply[A, B >: A](v : Vector[A], first: Int, __last: Int, ^ : Vector[B], out_ite: Int, __n: Int): Int = {
        apply(v, first, __last, ^, out_ite, __n, new DefaultRandomNumberGenerator)
    }

    def apply[A, B >: A](v : Vector[A], first: Int, __last: Int, ^ : Vector[B], out_ite: Int, __n: Int, __rand: Int => Int): Int = {
        var __first = first
        var __out_ite = out_ite

        var __remaining = __last - __first
        var __m = java.lang.Math.min(__n, __remaining)

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
