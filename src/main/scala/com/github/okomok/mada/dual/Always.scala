

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


class Always0[v <: Any](v: v) extends Function0 {
    override  def apply = v
    override type apply = v
}

class Always0_Nat[v <: Nat](v: v) extends Function0_Nat {
    override  def apply = v
    override type apply = v
}

class Always0_List[v <: List](v: v) extends Function0_List {
    override  def apply = v
    override type apply = v
}

class Always0_Nil extends Function0_List {
    override  def apply = Nil
    override type apply = Nil
}

class Always0_Unit extends Function0_Unit
