

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


import nat._


sealed trait Nat extends Any {
     def self: self
    type self <: Nat

    private[mada]  def isZero: isZero
    private[mada] type isZero <: Boolean

    private[mada]  def gtZero: gtZero
    private[mada] type gtZero <: Boolean

    final  def ===[that <: Nat](that: that): ===[that] = this.-(that).isZero
    final type ===[that <: Nat] = -[that]#isZero

    final  def !==[that <: Nat](that: that): !==[that] = ===(that).not
    final type !==[that <: Nat] = ===[that]#not

     def increment: increment
    type increment <: Nat

     def decrement: decrement
    type decrement <: Nat

    final  def +[that <: Nat](that: that): +[that] = new Add().apply(self, that)
    final type +[that <: Nat] = Add#apply[self, that]

    final  def -[that <: Nat](that: that): -[that] = new Subtract().apply(self, that)
    final type -[that <: Nat] = Subtract#apply[self, that]

    final  def **[that <: Nat](that: that): **[that] = new Multiply().apply(self, that)
    final type **[that <: Nat] = Multiply#apply[self, that]

    final  def >[that <: Nat](that: that): >[that] = this.-(that).gtZero
    final type >[that <: Nat] = -[that]#gtZero

    final  def <[that <: Nat](that: that): <[that] = that.>(self)
    final type <[that <: Nat] = that# >[self]

    final  def >=[that <: Nat](that: that): >=[that] = >(that).||(===(that))
    final type >=[that <: Nat] = >[that]# ||[===[that]]

    final  def <=[that <: Nat](that: that): <=[that] = that.>=(self)
    final type <=[that <: Nat] = that# >=[self]

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

    final override  def asInstanceOfNat = self
    final override type asInstanceOfNat = self

    final override type undual = scala.Int

    final override def equals(that: scala.Any) = that match {
        case that: Nat => undual == that.undual
        case _ => false
    }
}


sealed trait Zero extends Nat {
    override  def self = this
    override type self = Zero

    override private[mada]  def isZero = `true`
    override private[mada] type isZero = `true`

    override private[mada]  def gtZero = `false`
    override private[mada] type gtZero = `false`

    override  def increment = Succ(self)
    override type increment = Succ[self]

    override  def decrement = singular
    override type decrement = singular

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2] = z

    override def undual = 0
}


final case class Succ[n <: Nat](n: n) extends Nat {
    override  def self = this
    override type self = Succ[n]

    override private[mada]  def isZero = `false`
    override private[mada] type isZero = `false`

    override private[mada]  def gtZero = `true`
    override private[mada] type gtZero = `true`

    override  def increment = Succ(self)
    override type increment = Succ[self]

    override  def decrement = n
    override type decrement = n

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(self, n.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2] = f#apply[self, n#foldRight[z, f]]

    override def undual = 1 + n.undual
}


sealed trait singular extends Nat {
    override  def self = this
    override type self = singular

    override private[mada]  def isZero = `false`
    override private[mada] type isZero = `false`

    override private[mada]  def gtZero = `false`
    override private[mada] type gtZero = `false`

    override  def increment = unsupported
    override type increment = unsupported

    override  def decrement = self
    override type decrement = self

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f) = unsupported
    override type foldRight[z <: Any, f <: Function2] = unsupported
}


object Nat {
    private[mada] val _Zero = new Zero{}
    private[mada] val _singular = new singular{}
}
