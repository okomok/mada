

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


import nat.dense._0

private[dual]
object Length {
     def apply[xs <: List](xs: xs): apply[xs] = `if`(xs.isEmpty, const0(_0), Else(xs)).apply.asInstanceOfNat
    type apply[xs <: List]                    = `if`[xs#isEmpty, const0[_0], Else[xs]]#apply#asInstanceOfNat

    case class Else[xs <: List](xs: xs) extends Function0 {
        type self = Else[xs]
        override  def apply: apply = Length.apply(xs.tail).increment.asInstanceOf[apply]
        override type apply        = Length.apply[xs#tail]#increment
    }
}
