

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

    final  def ===[that <: Dense](that: that): ===[that] = new Equals().apply(self, that)
    final type ===[that <: Dense] = Equals#apply[self, that]

    final  def !==[that <: Dense](that: that): !==[that] = ===(that).not
    final type !==[that <: Dense] = ===[that]#not

     def increment: increment
    type increment <: Dense

     def decrement: decrement
    type decrement <: Dense

    final  def +[that <: Dense](that: that): +[that] = new Add().apply(self,that)
    final type +[that <: Dense] = Add#apply[self, that]

    final  def -[that <: Dense](that: that): -[that] = new Subtract().apply(self, that)
    final type -[that <: Dense] = Subtract#apply[self, that]

     def **[that <: Dense](that: that): **[that]
    type **[that <: Dense] <: Dense

    final  def >[that <: Dense](that: that): >[that] = that < self
    final type >[that <: Dense] = that# <[self]

    final  def <[that <: Dense](that: that): <[that] = new LessThan().apply(self, that)
    final type <[that <: Dense] = LessThan#apply[self, that]

    final  def >=[that <: Dense](that: that): >=[that] = (that > self).not
    final type >=[that <: Dense] = that# >[self]#not

    final  def <=[that <: Dense](that: that): <=[that] = (that < self).not
    final type <=[that <: Dense] = that# <[self]#not

     def <<[that <: Dense](that: that): <<[that]
    type <<[that <: Dense] <: Dense

     def >>[that <: Dense](that: that): >>[that]
    type >>[that <: Dense] <: Dense

    final  def &[that <: Dense](that: that): &[that] = new BitAnd().apply(self, that)
    final type &[that <: Dense] = BitAnd#apply[self, that]

    final  def |[that <: Dense](that: that): |[that] = new BitOr().apply(self, that)
    final type |[that <: Dense] = BitOr#apply[self, that]

     def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f]
    type foldRightWithNat[z <: Any, f <: Function2] <: Any

     def toPeano: toPeano
    type toPeano <: Peano

    @aliasOf("addFirst")
    final def Nat_::[e <: Boolean](e: e): addFirst[e] = addFirst(e)

    final override type undual = scala.Int
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Dense]
}


sealed class Nil extends Dense {
    override  val self = this
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
    override  val self = this
    override type self = Cons[x, xs]

    override  def head: head = x
    override type head = x

    override  def tail: tail = xs
    override type tail = xs

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  def increment: increment = new IncrementCons().apply(x, xs)
    override type increment = IncrementCons#apply[x, xs]

    override  def decrement: decrement = new DecrementCons().apply(x, xs)
    override type decrement = DecrementCons#apply[x, xs]

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