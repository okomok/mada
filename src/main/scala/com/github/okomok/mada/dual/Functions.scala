

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


trait Function0 extends Any {
    type self <: Function0

    final override  def asInstanceOfFunction0 = self
    final override type asInstanceOfFunction0 = self

     def apply: apply
    type apply <: Any
}

trait Function1 extends Any {
    type self <: Function1

    final override  def asInstanceOfFunction1 = self
    final override type asInstanceOfFunction1 = self

     def apply[v1 <: Any](v1: v1): apply[v1]
    type apply[v1 <: Any] <: Any
}

trait Function2 extends Any {
    type self <: Function2

    final override  def asInstanceOfFunction2 = self
    final override type asInstanceOfFunction2 = self

     def apply[v1 <: Any, v2 <: Any](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Any, v2 <: Any] <: Any
}
