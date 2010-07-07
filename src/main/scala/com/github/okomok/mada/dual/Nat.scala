

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

     def isEmpty: isEmpty
    type isEmpty <: Boolean

    @equivalentTo("NatCons(e, self)")
    final  def addFirst[e <: Boolean](e: e): addFirst[e] = NatCons(e, self)
    final type addFirst[e <: Boolean] = NatCons[e, self]

    final  def ===[that <: Nat](that: that): ===[that] = Equals(self, that).apply
    final type ===[that <: Nat] = Equals[self, that]#apply

    final  def !==[that <: Nat](that: that): !==[that] = ===(that).not
    final type !==[that <: Nat] = ===[that]#not

     def increment: increment
    type increment <: Nat

     def decrement: decrement
    type decrement <: Nat

    final  def +[that <: Nat](that: that) = Add(self,that).apply
    final type +[that <: Nat] = Add[self, that]#apply

    final  def -[that <: Nat](that: that) = Subtract(self, that).apply
    final type -[that <: Nat] = Subtract[self, that]#apply

     def **[that <: Nat](that: that): **[that]
    type **[that <: Nat] <: Nat

    final  def >[that <: Nat](that: that): >[that] = that.<(self)
    final type >[that <: Nat] = that# <[self]

    final  def <[that <: Nat](that: that) = Lt(self, that).apply
    final type <[that <: Nat] = Lt[self, that]#apply

    final  def >=[that <: Nat](that: that): >=[that] = that.<=(self)
    final type >=[that <: Nat] = that# <=[self]

    final  def <=[that <: Nat](that: that): <=[that] = Lteq(self, that).apply
    final type <=[that <: Nat] = Lteq[self, that]#apply

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

    final  def matchCaseNil[that <: Nat, nn <: Function0, nc <: Function0, cn <: Function0, cc <: Function0](that: that, nn: nn, nc: nc, cn: cn, cc: cc): matchCaseNil[that, nn, nc, cn, cc] = isEmpty.`if`(that.isEmpty.`if`(nn, nc), that.isEmpty.`if`(cn, cc))
    final type matchCaseNil[that <: Nat, nn <: Function0, nc <: Function0, cn <: Function0, cc <: Function0] = isEmpty#`if`[that#isEmpty#`if`[nn, nc], that#isEmpty#`if`[cn, cc]]

    final  def matchCaseCons[that <: Nat, tt <: Function0, tf <: Function0, ft <: Function0, ff <: Function0](that: that, tt: tt, tf: tf, ft: ft, ff: ff) = MatchCaseCons(self, that, tt, tf, ft, ff)
    final type matchCaseCons[that <: Nat, tt <: Function0, tf <: Function0, ft <: Function0, ff <: Function0] = MatchCaseCons[self, that, tt, tf, ft, ff]
}


sealed class NatNil extends Nat {
    override  def self = this
    override type self = NatNil

    override  def head = `throw`(new scala.NoSuchElementException("dual.NatNil.head"))
    override type head = `throw`[scala.NoSuchElementException]

    override  def tail = `throw`(new scala.NoSuchElementException("dual.NatNil.tail"))
    override type tail = `throw`[scala.NoSuchElementException]

    override  def isEmpty = `true`
    override type isEmpty = `true`

    override  def increment = NatCons(`true`, self)
    override type increment = NatCons[`true`, self]

    override  def decrement = `throw`(new scala.UnsupportedOperationException("dual.NatNil.decrement"))
    override type decrement = `throw`[scala.UnsupportedOperationException]

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

    override  def isEmpty = `false`
    override type isEmpty = `false`

    override  def increment = IncrementCons(x, xs).apply
    override type increment = IncrementCons[x, xs]#apply

    override  def decrement = DecrementCons(x, xs).apply
    override type decrement = DecrementCons[x, xs]#apply

    override  def **[that <: Nat](that: that) = throw new Error//MultiplyCons(x, xs).apply(that)
    override type **[that <: Nat] = Nothing//MultiplyCons[x, xs]#apply[that]

    override  def <<[that <: Nat](that: that) = NatCons(`false`, self)
    override type <<[that <: Nat] = NatCons[`false`, self]

    override  def >>[that <: Nat](that: that) = tail
    override type >>[that <: Nat] = tail

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(self, decrement.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2] = f#apply[self, decrement#foldRight[z, f]]

    override def undual = (if (x.undual) 1 else 0) + (2 * xs.undual)
}


object Nat {
    private[mada] val _Nil = new NatNil{}
}
