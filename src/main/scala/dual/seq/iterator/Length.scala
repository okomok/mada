

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


import nat.dense._0


final class Length[it <: Iterator](it: it) extends Function0 {
    type self = Length[it]
    override  def apply: apply[it] =
        `if`(it.isEnd, Const0(_0), new Else).apply.asInstanceOfNat
    override type apply[it <: Iterator] =
        `if`[it#isEnd, Const0[_0],     Else]#apply#asInstanceOfNat

    private class Else extends Function0 {
        type self = Else
        override  def apply: apply = new Length(it.next).apply.increment.asInstanceOf[apply]
        override type apply        =     Length[it#next]#apply#increment
    }
}
