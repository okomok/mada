

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


// See: http://apocalisp.wordpress.com/2010/06/24/type-level-programming-in-scala-part-5a-binary-numbers/


import nat._


sealed abstract class Nat extends Any {
    type self <: Nat

    final override  def asInstanceOfNat = self
    final override type asInstanceOfNat = self

     def head: head
    type head <: Boolean

     def tail: tail
    type tail <: Nat

    @equivalentTo("NatCons(e, self)")
    final  def addFirst[e <: Boolean](e: e): addFirst[e] = NatCons(e, self)
    final type addFirst[e <: Boolean] = NatCons[e, self]

    private[mada]  def ifNil[then <: Function0, _else <: Function0](then: then, _else: _else): ifNil[then, _else]
    private[mada] type ifNil[then <: Function0, _else <: Function0] <: Function0

    final  def ===[that <: Nat](that: that): ===[that] = throw new Error//this.-(that).isZero
    final type ===[that <: Nat] = Nothing//-[that]#isZero

    final  def !==[that <: Nat](that: that): !==[that] = throw new Error//===(that).not
    final type !==[that <: Nat] = Nothing//===[that]#not

     def increment: increment
    type increment <: Nat

     def decrement: decrement
    type decrement <: Nat

     def +[that <: Nat](that: that): +[that]
    type +[that <: Nat] <: Nat

     def -[that <: Nat](that: that): -[that]
    type -[that <: Nat] <: Nat

     def **[that <: Nat](that: that): **[that]
    type **[that <: Nat] <: Nat

    final  def >[that <: Nat](that: that): >[that] = throw new Error//this.-(that).gtZero
    final type >[that <: Nat] = Nothing//-[that]#gtZero

    final  def <[that <: Nat](that: that): <[that] = throw new Error//that.>(self)
    final type <[that <: Nat] = Nothing//that# >[self]

    final  def >=[that <: Nat](that: that): >=[that] = throw new Error//>(that).||(===(that))
    final type >=[that <: Nat] = Nothing// >[that]# ||[===[that]]

    final  def <=[that <: Nat](that: that): <=[that] = throw new Error// that.>=(self)
    final type <=[that <: Nat] = Nothing//that# >=[self]

     def <<[that <: Nat](that: that): <<[that]
    type <<[that <: Nat] <: Nat

     def >>[that <: Nat](that: that): >>[that]
    type >>[that <: Nat] <: Nat

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

    @aliasOf("addFirst")
    final def Nat_::[e <: Boolean](e: e): addFirst[e] = addFirst(e)

    final override type undual = scala.Int
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Nat]
}


sealed class NatNil extends Nat {
    override  def self = this
    override type self = NatNil

    override  def head = `throw`(new scala.NoSuchElementException("dual.NatNil.head"))
    override type head = `throw`[scala.NoSuchElementException]

    override  def tail = `throw`(new scala.NoSuchElementException("dual.NatNil.tail"))
    override type tail = `throw`[scala.NoSuchElementException]

    override private[mada]  def ifNil[then <: Function0, _else <: Function0](then: then, _else: _else) = then
    override private[mada] type ifNil[then <: Function0, _else <: Function0] = then

    override  def increment = NatCons(`true`, self)
    override type increment = NatCons[`true`, self]

    override  def decrement = `throw`(new scala.UnsupportedOperationException("dual.NatNil.decrement"))
    override type decrement = `throw`[scala.UnsupportedOperationException]

    override  def +[that <: Nat](that: that) = that
    override type +[that <: Nat] = that

    override  def -[that <: Nat](that: that) = SubtractNil(that).apply
    override type -[that <: Nat] = SubtractNil[that]#apply

    override  def **[that <: Nat](that: that) = self
    override type **[that <: Nat] = self

    override  def <<[that <: Nat](that: that) = self
    override type <<[that <: Nat] = self

    override  def >>[that <: Nat](that: that) = self
    override type >>[that <: Nat] = self

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2] = z

    override def undual = 0
}


final case class NatCons[x <: Boolean, xs <: Nat](private val x: x, private val xs: xs) extends Nat {
    override  def self = this
    override type self = NatCons[x, xs]

    override  def head = x
    override type head = x

    override  def tail = xs
    override type tail = xs

    override private[mada]  def ifNil[then <: Function0, _else <: Function0](then: then, _else: _else) = _else
    override private[mada] type ifNil[then <: Function0, _else <: Function0] = _else

    override  def increment = IncrementCons(x, xs).apply
    override type increment = IncrementCons[x, xs]#apply

    override  def decrement = DecrementCons(x, xs).apply
    override type decrement = DecrementCons[x, xs]#apply

    override  def +[that <: Nat](that: that) = AddCons(x, xs, that).apply
    override type +[that <: Nat] = AddCons[x, xs, that]#apply

    override  def -[that <: Nat](that: that) = SubtractCons(x, xs, that).apply
    override type -[that <: Nat] = SubtractCons[x, xs, that]#apply

    override  def **[that <: Nat](that: that) = throw new Error//MultiplyCons(x, xs).apply(that)
    override type **[that <: Nat] = Nothing//MultiplyCons[x, xs]#apply[that]

    override  def <<[that <: Nat](that: that) = NatCons(`false`, self)
    override type <<[that <: Nat] = NatCons[`false`, self]

    override  def >>[that <: Nat](that: that) = tail
    override type >>[that <: Nat] = tail

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(self, xs.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2] = f#apply[self, xs#foldRight[z, f]]

    override def undual = 1 + (2 * xs.undual)
}


object Nat {
    private[mada] val _Nil = new NatNil{}
}
