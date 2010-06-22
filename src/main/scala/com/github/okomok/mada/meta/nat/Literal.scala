

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package meta; package nat


/**
 * Contains natural number literals.
 */
object Literal extends LiteralCommon

private[mada] trait LiteralCommon {
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
