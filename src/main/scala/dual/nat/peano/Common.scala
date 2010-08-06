

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[dual]
class Common extends LiteralCommon {
    @returnThis
    val Literal: LiteralCommon = this

    @equivalentTo("new Zero{}")
    val Zero = _Peano.Zero
}


private[dual]
trait LiteralCommon {
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
    val _11 = Succ(_10)
    val _12 = Succ(_11)
    val _13 = Succ(_12)
    val _14 = Succ(_13)
    val _15 = Succ(_14)
    val _16 = Succ(_15)
    val _17 = Succ(_16)
    val _18 = Succ(_17)
    val _19 = Succ(_18)
    val _20 = Succ(_19)
    val _21 = Succ(_20)
    val _22 = Succ(_21)
    val _23 = Succ(_22)
    val _24 = Succ(_23)
    val _25 = Succ(_24)
    val _26 = Succ(_25)
    val _27 = Succ(_26)
    val _28 = Succ(_27)
    val _29 = Succ(_28)
    val _30 = Succ(_29)

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
    type _11 = Succ[_10]
    type _12 = Succ[_11]
    type _13 = Succ[_12]
    type _14 = Succ[_13]
    type _15 = Succ[_14]
    type _16 = Succ[_15]
    type _17 = Succ[_16]
    type _18 = Succ[_17]
    type _19 = Succ[_18]
    type _20 = Succ[_19]
    type _21 = Succ[_20]
    type _22 = Succ[_21]
    type _23 = Succ[_22]
    type _24 = Succ[_23]
    type _25 = Succ[_24]
    type _26 = Succ[_25]
    type _27 = Succ[_26]
    type _28 = Succ[_27]
    type _29 = Succ[_28]
    type _30 = Succ[_29]
}
