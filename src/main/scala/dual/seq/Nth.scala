

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Nth[xs <: Seq, n <: Nat](xs: xs, n: n) extends Function0 {
    type self = Nth[xs, n]

    override  def apply: apply = `if`(n.isZero, Const0(xs.head), new Else(xs, n)).apply
    override type apply        = `if`[n#isZero, Const0[xs#head],     Else[xs, n]]#apply

    private class Else[xs <: Seq, n <: Nat](xs: xs, n: n) extends Function0 {
        type self = Else[xs, n]
        override  def apply: apply = new Nth(xs.tail, n.decrement).apply
        override type apply        =     Nth[xs#tail, n#decrement]#apply
    }
}
