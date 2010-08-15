

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


import function._


trait Function0 extends Any {
    type self <: Function0

    final override  def asFunction0 = self
    final override type asFunction0 = self

     def apply: apply
    type apply <: Any

    override lazy val undual: undual = () => apply.undual
    override type undual             = () => apply#undual

    override def canEqual(that: scala.Any) = that.isInstanceOf[Function0]
}


trait Function1 extends Any with ReferenceEquality {
    type self <: Function1

    final override  def asFunction1 = self
    final override type asFunction1 = self

     def apply[v1 <: Any](v1: v1): apply[v1]
    type apply[v1 <: Any] <: Any

    final  def compose[that <: Function1](that: that): compose[that] = Compose.Impl(self, that)
    final type compose[that <: Function1]                            = Compose.Impl[self, that]

    final  def andThen[that <: Function1](that: that): andThen[that] = Compose.Impl(that, self)
    final type andThen[that <: Function1]                            = Compose.Impl[that, self]

    final  def not: not = Not1.Impl(self)
    final type not      = Not1.Impl[self]
}


trait Function2 extends Any with ReferenceEquality {
    type self <: Function2

    final override  def asFunction2 = self
    final override type asFunction2 = self

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

    final override  def asFunction3 = self
    final override type asFunction3 = self

     def apply[v1 <: Any, v2 <: Any, v3 <: Any](v1: v1, v2: v2, v3: v3): apply[v1, v2, v3]
    type apply[v1 <: Any, v2 <: Any, v3 <: Any] <: Any

    final  def curried: curried = Curried3.Impl(self)
    final type curried          = Curried3.Impl[self]

    final  def tupled: tupled = Tupled3.Impl(self)
    final type tupled         = Tupled3.Impl[self]

    final  def tupledLeft: tupledLeft = TupledLeft3.Impl(self)
    final type tupledLeft             = TupledLeft3.Impl[self]

    final  def not: not = Not3.Impl(self)
    final type not      = Not3.Impl[self]
}
