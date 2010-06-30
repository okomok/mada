

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


class Always0_Any[v <: Any](v: v) extends Function0_Any {
    override  def apply = v
    override type apply = v
}

class Always0_Nat[n <: Nat](n: n) extends Function0_Nat {
    override  def apply = n
    override type apply = n
}

class Always0_Unit extends Function0_Unit {
    override  def apply = unit
    override type apply = unit
}
