

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] class Common extends LiteralCommon {
    @returnThis
    val Literal: LiteralCommon = this

    @equivalentTo("new Zero{}")
    val Zero = _Peano.Zero

    private[mada] val Singular = _Peano.Singular
}


private[mada] trait LiteralCommon {
    val  _0 = _Peano.Zero
    val  _1 = Succ(_0)
    val  _2 = Succ(_1)
    val  _3 = Succ(_2)
    val  _4 = Succ(_3)
    val  _5 = Succ(_4)
    val  _6 = Succ(_5)
    val  _7 = Succ(_6)
    val  _8 = Succ(_7)
    val  _9 = Succ(_8)
    val _10 = Succ(_9)

    type  _0 = Zero
    type  _1 = Succ[_0]
    type  _2 = Succ[_1]
    type  _3 = Succ[_2]
    type  _4 = Succ[_3]
    type  _5 = Succ[_4]
    type  _6 = Succ[_5]
    type  _7 = Succ[_6]
    type  _8 = Succ[_7]
    type  _9 = Succ[_8]
    type _10 = Succ[_9]
}


/**
 * Contains operators for Dense.
 */
@compilerWorkaround("2.8.0") // not extended by `Common` to avoid "error: type _140.type is defined twice".
object Operator extends OperatorCommon

private[mada] trait OperatorCommon {
    type  +[x <: Peano, y <: Peano] = x# +[y]
    type  -[x <: Peano, y <: Peano] = x# -[y]
    type **[x <: Peano, y <: Peano] = x# **[y]

    type ===[x <: Peano, y <: Peano] = x# ===[y]
    type !==[x <: Peano, y <: Peano] = x# !==[y]

    type  <[x <: Peano, y <: Peano] = x# <[y]
    type <=[x <: Peano, y <: Peano] = x# <=[y]
    type  >[x <: Peano, y <: Peano] = x# >[y]
    type >=[x <: Peano, y <: Peano] = x# >=[y]
}