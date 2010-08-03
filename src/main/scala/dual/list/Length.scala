

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


import nat.dense._0


final class Length[xs <: List](xs: xs) extends Function0 {
    type self = Length[xs]

    override  def apply: apply = `if`(xs.isEmpty, Const0(_0), new Else).apply.asInstanceOfNat
    override type apply        = `if`[xs#isEmpty, Const0[_0],     Else]#apply#asInstanceOfNat

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new Length(xs.tail).apply.increment.asInstanceOf[apply]
        override type apply        =     Length[xs#tail]#apply#increment
    }
}
