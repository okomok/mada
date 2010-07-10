

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


// See: http://apocalisp.wordpress.com/2010/06/24/type-level-programming-in-scala-part-5a-binary-numbers/


object Dense extends Common with OperatorCommon {
    @returnThis
    val Operator: OperatorCommon = this
}


sealed abstract class Dense extends Any {
    type self <: Dense

    final override  def asInstanceOfNatDense = self
    final override type asInstanceOfNatDense = self

     def head: head
    type head <: Boolean

     def tail: tail
    type tail <: Dense

     def isEmpty: isEmpty
    type isEmpty <: Boolean

    @equivalentTo("Cons(e, self)")
    final  def addFirst[e <: Boolean](e: e): addFirst[e] = Cons(e, self)
    final type addFirst[e <: Boolean] = Cons[e, self]

    final  def ===[that <: Dense](that: that): ===[that] = Equals(self, that).apply
    final type ===[that <: Dense] = Equals[self, that]#apply

    final  def !==[that <: Dense](that: that): !==[that] = ===(that).not
    final type !==[that <: Dense] = ===[that]#not

     def increment: increment
    type increment <: Dense

     def decrement: decrement
    type decrement <: Dense

    final  def +[that <: Dense](that: that): +[that] = Add(self,that).apply
    final type +[that <: Dense] = Add[self, that]#apply

    final  def -[that <: Dense](that: that): -[that] = Subtract(self, that).apply
    final type -[that <: Dense] = Subtract[self, that]#apply

     def **[that <: Dense](that: that): **[that]
    type **[that <: Dense] <: Dense

    final  def >[that <: Dense](that: that): >[that] = that < self
    final type >[that <: Dense] = that# <[self]

    final  def <[that <: Dense](that: that): <[that] = LessThan(self, that).apply
    final type <[that <: Dense] = LessThan[self, that]#apply

    final  def >=[that <: Dense](that: that): >=[that] = (that > self).not
    final type >=[that <: Dense] = that# >[self]#not

    final  def <=[that <: Dense](that: that): <=[that] = (that < self).not
    final type <=[that <: Dense] = that# <[self]#not

     def <<[that <: Dense](that: that): <<[that]
    type <<[that <: Dense] <: Dense

     def >>[that <: Dense](that: that): >>[that]
    type >>[that <: Dense] <: Dense

     def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f]
    type foldRightWithNat[z <: Any, f <: Function2] <: Any

     def toPeano: toPeano
    type toPeano <: Peano

    @aliasOf("addFirst")
    final def Nat_::[e <: Boolean](e: e): addFirst[e] = addFirst(e)

    final override type undual = scala.Int
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Dense]

    final  def matchCaseNil[that <: Dense, nn <: Function0, nc <: Function0, cn <: Function0, cc <: Function0](that: that, nn: nn, nc: nc, cn: cn, cc: cc): matchCaseNil[that, nn, nc, cn, cc] = isEmpty.`if`(that.isEmpty.`if`(nn, nc), that.isEmpty.`if`(cn, cc))
    final type matchCaseNil[that <: Dense, nn <: Function0, nc <: Function0, cn <: Function0, cc <: Function0] = isEmpty#`if`[that#isEmpty#`if`[nn, nc], that#isEmpty#`if`[cn, cc]]

    final  def matchCaseCons[that <: Dense, tt <: Function0, tf <: Function0, ft <: Function0, ff <: Function0](that: that, tt: tt, tf: tf, ft: ft, ff: ff): matchCaseCons[that, tt, tf, ft, ff] = MatchCaseCons(self, that, tt, tf, ft, ff)
    final type matchCaseCons[that <: Dense, tt <: Function0, tf <: Function0, ft <: Function0, ff <: Function0] = MatchCaseCons[self, that, tt, tf, ft, ff]
}


sealed class Nil extends Dense {
    override  def self = this
    override type self = Nil

    override  def head: head = `throw`(new scala.NoSuchElementException("dual.nat.Nil.head"))
    override type head = `throw`[scala.NoSuchElementException]

    override  def tail: tail = `throw`(new scala.NoSuchElementException("dual.nat.Nil.tail"))
    override type tail = `throw`[scala.NoSuchElementException]

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def increment: increment = Cons(`true`, self)
    override type increment = Cons[`true`, self]

    override  def decrement: decrement = `throw`(new scala.UnsupportedOperationException("dual.nat.Nil.decrement"))
    override type decrement = `throw`[scala.UnsupportedOperationException]

    override  def **[that <: Dense](that: that): **[that] = self
    override type **[that <: Dense] = self

    override  def <<[that <: Dense](that: that): <<[that] = self
    override type <<[that <: Dense] = self

    override  def >>[that <: Dense](that: that): >>[that] = self
    override type >>[that <: Dense] = self

    override  def toPeano: toPeano = peano.Zero
    override type toPeano = peano.Zero

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = z
    override type foldRightWithNat[z <: Any, f <: Function2] = z

    override def undual: undual = 0
}


final case class Cons[x <: Boolean, xs <: Dense](private val x: x, private val xs: xs) extends Dense {
    override  def self = this
    override type self = Cons[x, xs]

    override  def head: head = x
    override type head = x

    override  def tail: tail = xs
    override type tail = xs

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  def increment: increment = IncrementCons(x, xs).apply
    override type increment = IncrementCons[x, xs]#apply

    override  def decrement: decrement = DecrementCons(x, xs).apply
    override type decrement = DecrementCons[x, xs]#apply

    override  def **[that <: Dense](that: that): **[that] = throw new Error//MultiplyCons(x, xs).apply(that)
    override type **[that <: Dense] = Nothing//MultiplyCons[x, xs]#apply[that]

    override  def <<[that <: Dense](that: that): <<[that] = Cons(`false`, self)
    override type <<[that <: Dense] = Cons[`false`, self]

    override  def >>[that <: Dense](that: that): >>[that] = tail
    override type >>[that <: Dense] = tail

    override  def toPeano: toPeano = peano.Succ(decrement.toPeano)
    override type toPeano = peano.Succ[decrement#toPeano]

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = f.apply(self, decrement.foldRightWithNat(z, f))
    override type foldRightWithNat[z <: Any, f <: Function2] = f#apply[self, decrement#foldRightWithNat[z, f]]

    override def undual: undual = (if (x.undual) 1 else 0) + (2 * xs.undual)
}


private[mada] object _Dense {
    val Nil = new Nil{}
}
