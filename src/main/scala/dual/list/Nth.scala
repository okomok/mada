

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Nth[xs <: List, n <: Nat](xs: xs, n: n) extends Function0 {
    type self = Nth[xs, n]

    override  def apply: apply = `if`(n.isZero, const0(xs.head), new Else).apply.asInstanceOf[apply]
    override type apply        = `if`[n#isZero, const0[xs#head],     Else]#apply

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new Nth(xs.tail, n.decrement).apply.asInstanceOf[apply]
        override type apply        =     Nth[xs#tail, n#decrement]#apply
    }
}
