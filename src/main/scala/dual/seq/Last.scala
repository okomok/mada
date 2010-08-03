

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Last[xs <: Seq](xs: xs) extends Function0 {
    type self = Last[xs]

    override  def apply: apply = `if`(xs.tail.isEmpty, Const0(xs.head), new Else).apply.asInstanceOf[apply]
    override type apply        = `if`[xs#tail#isEmpty, Const0[xs#head],     Else]#apply

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new Last(xs.tail).apply.asInstanceOf[apply]
        override type apply        =     Last[xs#tail]#apply
    }
}
