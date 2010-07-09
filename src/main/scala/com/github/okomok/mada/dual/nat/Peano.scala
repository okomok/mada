

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


import peano._


object Peano extends LiteralCommon with OperatorCommon {
    @aliasOf("peano.Literal")
    val Literal = peano.Literal

    @aliasOf("peano.Operator")
    val Operator = peano.Operator
}


sealed trait Peano extends Any {
    type self <: Peano

    final override  def asInstanceOfNatPeano = self
    final override type asInstanceOfNatPeano = self

    private[mada]  def isZero: isZero
    private[mada] type isZero <: Boolean

    private[mada]  def gtZero: gtZero
    private[mada] type gtZero <: Boolean

    final  def ===[that <: Peano](that: that): ===[that] = this.-(that).isZero
    final type ===[that <: Peano] = -[that]#isZero

    final  def !==[that <: Peano](that: that): !==[that] = ===(that).not
    final type !==[that <: Peano] = ===[that]#not

     def increment: increment
    type increment <: Peano

     def decrement: decrement
    type decrement <: Peano

    final  def +[that <: Peano](that: that): +[that] = new Add().apply(self, that)
    final type +[that <: Peano] = Add#apply[self, that]

    final  def -[that <: Peano](that: that): -[that] = new Subtract().apply(self, that)
    final type -[that <: Peano] = Subtract#apply[self, that]

    final  def **[that <: Peano](that: that): **[that] = new Multiply().apply(self, that)
    final type **[that <: Peano] = Multiply#apply[self, that]

    final  def >[that <: Peano](that: that): >[that] = this.-(that).gtZero
    final type >[that <: Peano] = -[that]#gtZero

    final  def <[that <: Peano](that: that): <[that] = that.>(self)
    final type <[that <: Peano] = that# >[self]

    final  def >=[that <: Peano](that: that): >=[that] = >(that).||(===(that))
    final type >=[that <: Peano] = >[that]# ||[===[that]]

    final  def <=[that <: Peano](that: that): <=[that] = that.>=(self)
    final type <=[that <: Peano] = that# >=[self]

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

    final override type undual = scala.Int
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Peano]
}


sealed trait Zero extends Peano {
    override  def self = this
    override type self = Zero

    override private[mada]  def isZero = `true`
    override private[mada] type isZero = `true`

    override private[mada]  def gtZero = `false`
    override private[mada] type gtZero = `false`

    override  def increment = Succ(self)
    override type increment = Succ[self]

    override  def decrement = Singular
    override type decrement = Singular

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2] = z

    override def undual = 0
}


final case class Succ[n <: Peano](private val n: n) extends Peano {
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


sealed trait Singular extends Peano {
    override  def self = this
    override type self = Singular

    override private[mada]  def isZero = `false`
    override private[mada] type isZero = `false`

    override private[mada]  def gtZero = `false`
    override private[mada] type gtZero = `false`

    override  def increment = `throw`(new scala.UnsupportedOperationException("dual.nat.Singular.increment"))
    override type increment = `throw`[scala.UnsupportedOperationException]

    override  def decrement = self
    override type decrement = self

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f) =  `throw`(new scala.UnsupportedOperationException("dual.Singular.foldRight"))
    override type foldRight[z <: Any, f <: Function2] = `throw`[scala.UnsupportedOperationException]
}


private[mada] object _Peano {
    val Zero = new Zero{}
    val Singular = new Singular{}
}
