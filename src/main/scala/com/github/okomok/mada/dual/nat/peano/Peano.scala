

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


object Peano extends Common with OperatorCommon {
    @returnThis
    val Operator: OperatorCommon = this
}


sealed trait Peano extends Any {
    type self <: Peano

    final override  def asInstanceOfNatPeano = self
    final override type asInstanceOfNatPeano = self

     def isZero: isZero
    type isZero <: Boolean

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

     def ===[that <: Peano](that: that): ===[that]
    type ===[that <: Peano] <: Boolean

     def <=[that <: Peano](that: that): <=[that]
    type <=[that <: Peano] <: Boolean

    final  def !==[that <: Peano](that: that): !==[that] = ===(that).not
    final type !==[that <: Peano] = ===[that]#not

    final  def >=[that <: Peano](that: that): >=[that] = that <= self
    final type >=[that <: Peano] = that# <=[self]

    final  def <[that <: Peano](that: that): <[that] = >=(that).not
    final type <[that <: Peano] = >=[that]#not

    final  def >[that <: Peano](that: that): >[that] = <=(that).not
    final type >[that <: Peano] = <=[that]#not

    //final  def %[that <: Peano](that: that): %[that] = new Mod().apply(self, that)
    //final type %[that <: Peano] = Mod#apply[self, that]

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

     def isEven: isEven
    type isEven <: Boolean

    final  def isOdd: isOdd = isEven.not
    final type isOdd = isEven#not

    final  def toDense: toDense = new ToDense().apply(self)
    final type toDense = ToDense#apply[self]

    final override type undual = scala.Int
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Peano]
}


sealed trait Zero extends Peano {
    override  val self = this
    override type self = Zero

    override  def isZero: isZero = `true`
    override type isZero = `true`

    override  def increment: increment = Succ(self)
    override type increment = Succ[self]

    override  def decrement: decrement = unsupported("dual.nat.peano.Zero.decrement")
    override type decrement = unsupported[_]

    override  def ===[that <: Peano](that: that): ===[that] = that.isZero
    override type ===[that <: Peano] = that#isZero

    override  def <=[that <: Peano](that: that): <=[that] = `true`
    override type <=[that <: Peano] = `true`

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2] = z

    override  def isEven: isEven = `true`
    override type isEven = `true`

    override def undual: undual = 0
}


final case class Succ[n <: Peano](override val decrement: n) extends Peano {
    override  val self = this
    override type self = Succ[n]

    override  def isZero: isZero = `false`
    override type isZero = `false`

    override  def increment: increment = Succ(self)
    override type increment = Succ[self]

    override type decrement = n

    override  def ===[that <: Peano](that: that): ===[that] = new SuccEq().apply(self, that)
    override type ===[that <: Peano] = SuccEq#apply[self, that]

    override  def <=[that <: Peano](that: that): <=[that] = new SuccLtEq().apply(self, that)
    override type <=[that <: Peano] = SuccLtEq#apply[self, that]

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(self, decrement.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2] = f#apply[self, decrement#foldRight[z, f]]

    override lazy val isEven: isEven = decrement.isEven.not
    override type isEven = decrement#isEven#not

    override def undual: undual = 1 + decrement.undual
}

private[mada] object _Peano {
    val Zero = new Zero{}
}
