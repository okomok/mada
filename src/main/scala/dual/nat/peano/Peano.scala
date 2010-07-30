

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


object Peano extends Common


sealed trait Peano extends Nat {
    type self <: Peano
    type asInstanceOfNatPeano = self

    final override  def increment: increment = Succ(self)
    private type _increment[self <: Peano]   = Succ[self]
    final override type increment = _increment[self]

    override type decrement <: Peano

    final override  def +[that <: Nat](that: that): +[that] = new Add().apply(self, that.toPeano)
    final override type +[that <: Nat] = Add#apply[self, that#toPeano]

    final override  def -[that <: Nat](that: that): -[that] = new Subtract().apply(self, that.toPeano)
    final override type -[that <: Nat] = Subtract#apply[self, that#toPeano]

    final override  def **[that <: Nat](that: that): **[that] = new Multiply().apply(self, that.toPeano)
    final override type **[that <: Nat] = Multiply#apply[self, that#toPeano]

    final override  def divMod[that <: Nat](that: that): divMod[that] = new DivMod().apply(self, that)
    final override type divMod[that <: Nat] = DivMod#apply[self, that]

    final override  def ^[that <: Nat](that: that): ^[that] = toDense  ^ that
    final override type ^[that <: Nat]                      = toDense# ^[that]

    final override  def <[that <: Nat](that: that): <[that] = (that.toPeano  <= self).not
    final override type <[that <: Nat]                      =  that#toPeano# <=[self]#not

    final override  def &[that <: Nat](that: that): &[that] = (toDense  & that).toPeano
    final override type &[that <: Nat]                      =  toDense# &[that]#toPeano

    final override  def |[that <: Nat](that: that): |[that] = (toDense  | that).toPeano
    final override type |[that <: Nat]                      =  toDense# |[that]#toPeano

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

     def isEven: isEven
    type isEven <: Boolean

    final  def isOdd: isOdd = isEven.not
    final type isOdd        = isEven#not

    final  def toDense: toDense = new ToDense().apply(self)
    final type toDense = ToDense#apply[self]

    final override  def toPeano: toPeano = self
    final override type toPeano          = self
}


sealed trait Zero extends Peano {
    type self = Zero

    override  def isZero: isZero = `true`
    override type isZero         = `true`

    override  def decrement: decrement = unsupported("nat.peano.Zero.decrement")
    override type decrement            = unsupported[_]

    override  def ===[that <: Nat](that: that): ===[that] = that.isZero
    override type ===[that <: Nat]                        = that#isZero

    override  def <=[that <: Nat](that: that): <=[that] = `true`
    override type <=[that <: Nat]                       = `true`

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2]                              = z

    override  def isEven: isEven = `true`
    override type isEven         = `true`

    override def undual: undual = 0
}


final case class Succ[n <: Peano](override val decrement: n) extends Peano {
    type self = Succ[n]

    override  def isZero: isZero = `false`
    override type isZero         = `false`

    override type decrement = n

    override  def ===[that <: Nat](that: that): ===[that] = new SuccEq().apply(self, that.toPeano)
    override type ===[that <: Nat] = SuccEq#apply[self, that#toPeano]

    override  def <=[that <: Nat](that: that): <=[that] = new SuccLtEq().apply(self, that.toPeano)
    override type <=[that <: Nat] = SuccLtEq#apply[self, that#toPeano]

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(self, self.decrement.foldRight(z, f))
    private type _foldRight[self <: Peano, z <: Any, f <: Function2]               = f#apply[self, self#decrement#foldRight[z, f]]
    override type foldRight[z <: Any, f <: Function2] = _foldRight[self, z, f]

    override lazy val isEven: isEven = decrement.isEven.not
    override type isEven             = decrement#isEven#not

    override def undual: undual = 1 + decrement.undual
}


private[mada] object _Peano {
    val Zero = new Zero{}
}
