

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

    override private[mada]  def gtZero: gtZero = `false`
    override private[mada] type gtZero = `false`

    override  def increment: increment = Succ(self)
    override type increment = Succ[self]

    override  def decrement: decrement = Singular
    override type decrement = Singular

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

    override private[mada]  def gtZero: gtZero = `true`
    override private[mada] type gtZero = `true`

    override  def increment: increment = Succ(self)
    override type increment = Succ[self]

    override type decrement = n

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(self, decrement.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2] = f#apply[self, decrement#foldRight[z, f]]

    override lazy val isEven: isEven = decrement.isEven.not
    override type isEven = decrement#isEven#not

    override def undual: undual = 1 + decrement.undual
}


sealed trait Singular extends Peano {
    override  val self = this
    override type self = Singular

    override  def isZero: isZero = `false`
    override  type isZero = `false`

    override private[mada]  def gtZero: gtZero = `false`
    override private[mada] type gtZero = `false`

    override  def increment: increment = `throw`(new scala.UnsupportedOperationException("dual.nat.Singular.increment"))
    override type increment = `throw`[scala.UnsupportedOperationException]

    override  def decrement: decrement = self
    override type decrement = self

    override  def isEven: isEven = `throw`(new scala.UnsupportedOperationException("dual.nat.Singular.isEven"))
    override type isEven = `throw`[scala.UnsupportedOperationException]

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] =  `throw`(new scala.UnsupportedOperationException("dual.Singular.foldRight"))
    override type foldRight[z <: Any, f <: Function2] = `throw`[scala.UnsupportedOperationException]
}


private[mada] object _Peano {
    val Zero = new Zero{}
    val Singular = new Singular{}
}
