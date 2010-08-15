

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


import function._


trait Function0 extends Any {
    type self <: Function0
    type asInstanceOfFunction0 = self

     def apply: apply
    type apply <: Any

    override lazy val undual: undual = () => apply.undual
    override type undual             = () => apply#undual

    override def canEqual(that: scala.Any) = that.isInstanceOf[Function0]
}


trait Function1 extends Any with ReferenceEquality {
    type self <: Function1
    type asInstanceOfFunction1 = self

     def apply[v1 <: Any](v1: v1): apply[v1]
    type apply[v1 <: Any] <: Any

    final  def compose[that <: Function1](that: that): compose[that] = new Compose(self, that)
    final type compose[that <: Function1]                            =     Compose[self, that]

    final  def andThen[that <: Function1](that: that): andThen[that] = new Compose(that, self)
    final type andThen[that <: Function1]                            =     Compose[that, self]

    final  def not: not = Not1.Impl(self)
    final type not      = Not1.Impl[self]
}


trait Function2 extends Any with ReferenceEquality {
    type self <: Function2
    type asInstanceOfFunction2 = self

     def apply[v1 <: Any, v2 <: Any](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Any, v2 <: Any] <: Any

    final  def curried: curried = Curried2.Impl(self)
    final type curried          = Curried2.Impl[self]

    final  def tupled: tupled = Tupled2.Impl(self)
    final type tupled         = Tupled2.Impl[self]

    final  def not: not = Not2.Impl(self)
    final type not      = Not2.Impl[self]
}


trait Function3 extends Any with ReferenceEquality {
    type self <: Function3
    type asInstanceOfFunction3 = self

     def apply[v1 <: Any, v2 <: Any, v3 <: Any](v1: v1, v2: v2, v3: v3): apply[v1, v2, v3]
    type apply[v1 <: Any, v2 <: Any, v3 <: Any] <: Any

    final  def curried: curried = Curried3.Impl(self)
    final type curried          = Curried3.Impl[self]

    final  def tupled: tupled = Tupled3.Impl(self)
    final type tupled         = Tupled3.Impl[self]

    final  def not: not = Not3.Impl(self)
    final type not      = Not3.Impl[self]
}
