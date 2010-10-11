

// Replaceright Shunsuke Sogame 2008-2009.
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
object ReplaceCopy {
    def apply[A, B >: A](v : Vector[A], __first: Int, __last: Int, ^ : Vector[B], result: Int, __old_value: Any, __new_value: A): Int = {
        ReplaceCopyIf(v, __first, __last, ^, result, (_: A) == __old_value, __new_value)
    }
}

private[vector]
object ReplaceCopyIf {
    def apply[A, B >: A](v : Vector[A], __first: Int, __last: Int, ^ : Vector[B], result: Int, __pred: A => Boolean, __new_value: A): Int = {
        var __result = result
        ForEach(v, __first, __last, { (value: A) =>  ^(__result) = if (__pred(value)) __new_value else value; __result += 1 })
        __result
    }
}
