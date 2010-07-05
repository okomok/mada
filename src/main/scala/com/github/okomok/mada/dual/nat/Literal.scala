

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


/**
 * Contains natural number literals.
 */
object Literal extends LiteralCommon

private[mada] trait LiteralCommon {
    val _0N = Nat._Zero
    val _1N = Succ(_0N)
    val _2N = Succ(_1N)
    val _3N = Succ(_2N)
    val _4N = Succ(_3N)
    val _5N = Succ(_4N)
    val _6N = Succ(_5N)
    val _7N = Succ(_6N)
    val _8N = Succ(_7N)
    val _9N = Succ(_8N)
    val _10N = Succ(_9N)

    type _0N = Zero
    type _1N = Succ[_0N]
    type _2N = Succ[_1N]
    type _3N = Succ[_2N]
    type _4N = Succ[_3N]
    type _5N = Succ[_4N]
    type _6N = Succ[_5N]
    type _7N = Succ[_6N]
    type _8N = Succ[_7N]
    type _9N = Succ[_8N]
    type _10N = Succ[_9N]
}
