

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


import nat.dense


private[mada] final class Length {
     def apply[it <: Iterator](it: it): apply[it] =
        `if`(it.isEnd, Const0(dense._0), Else(it)).apply.asInstanceOfNat
    type apply[it <: Iterator] =
        `if`[it#isEnd, Const0[dense._0], Else[it]]#apply#asInstanceOfNat

    case class Else[it <: Iterator](it: it) extends Function0 {
        type self = Else[it]
        override  def apply: apply = View(it.next).length.increment.asInstanceOf[apply]
        override type apply        = View[it#next]#length#increment
    }
}
