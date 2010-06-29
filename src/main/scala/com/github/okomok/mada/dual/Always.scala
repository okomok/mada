

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


class Always0_Nat[n <: Nat](n: n) extends Function0_Nat {
    override  def apply = n
    override type apply = n
}
