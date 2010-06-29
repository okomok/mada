

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


/**
 * Contains natural number literals.
 */
object Literal extends LiteralCommon

private[mada] trait LiteralCommon {
    val _0N = _Nat._zero
    val _1N = new succ(_0N)
    val _2N = new succ(_1N)
    val _3N = new succ(_2N)
    val _4N = new succ(_3N)
    val _5N = new succ(_4N)
    val _6N = new succ(_5N)
    val _7N = new succ(_6N)
    val _8N = new succ(_7N)
    val _9N = new succ(_8N)
    val _10N = new succ(_9N)

    type _0N = zero
    type _1N = succ[_0N]
    type _2N = succ[_1N]
    type _3N = succ[_2N]
    type _4N = succ[_3N]
    type _5N = succ[_4N]
    type _6N = succ[_5N]
    type _7N = succ[_6N]
    type _8N = succ[_7N]
    type _9N = succ[_8N]
    type _10N = succ[_9N]
}
