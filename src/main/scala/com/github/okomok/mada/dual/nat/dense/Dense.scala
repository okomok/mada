

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

    final  def ===[that <: Dense](that: that): ===[that] = new Equals().apply(self, that)
    final type ===[that <: Dense] = Equals#apply[self, that]

    final  def !==[that <: Dense](that: that): !==[that] = ===(that).not
    final type !==[that <: Dense] = ===[that]#not

    final  def >[that <: Dense](that: that): >[that] = that < self
    final type >[that <: Dense] = that# <[self]

    final  def <[that <: Dense](that: that): <[that] = new LessThan().apply(self, that)
    final type <[that <: Dense] = LessThan#apply[self, that]

    final  def >=[that <: Dense](that: that): >=[that] = (that > self).not
    final type >=[that <: Dense] = that# >[self]#not

    final  def <=[that <: Dense](that: that): <=[that] = (that < self).not
    final type <=[that <: Dense] = that# <[self]#not

     def shiftLeft: shiftLeft
    type shiftLeft <: Dense

     def shiftRight: shiftRight
    type shiftRight <: Dense

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

    override  def head: head = `throw`(new scala.NoSuchElementException("dual.nat.dense.Nil.head"))
    override type head = `throw`[scala.NoSuchElementException]

    override  def tail: tail = `throw`(new scala.NoSuchElementException("dual.nat.dense.Nil.tail"))
    override type tail = `throw`[scala.NoSuchElementException]

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def increment: increment = Cons(`true`, self)
    override type increment = Cons[`true`, self]

    override  def decrement: decrement = unsupported("dual.nat.dense.Nil.decrement")
    override type decrement = unsupported[_]

    override  def **[that <: Dense](that: that): **[that] = self
    override type **[that <: Dense] = self

    override  def shiftLeft: shiftLeft = self
    override type shiftLeft = self

    override  def shiftRight: shiftRight = self
    override type shiftRight = self

    override  def toPeano: toPeano = peano.Zero
    override type toPeano = peano.Zero

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = z
    override type foldRightWithNat[z <: Any, f <: Function2] = z

    override def undual: undual = 0
}


final case class Cons[x <: Boolean, xs <: Dense](override val head: x, override val tail: xs) extends Dense {
    assert(head || tail.isEmpty.not)

    override  val self = this
    override type self = Cons[x, xs]

    override type head = x
    override type tail = xs

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  def increment: increment = new ConsIncrement().apply(head, tail)
    override type increment = ConsIncrement#apply[head, tail]

    override  def decrement: decrement = new ConsDecrement().apply(head, tail)
    override type decrement = ConsDecrement#apply[head, tail]

    override  def **[that <: Dense](that: that): **[that] = new ConsMultiply().apply(head, tail, that)
    override type **[that <: Dense] = ConsMultiply#apply[head, tail, that]

    override  def shiftLeft: shiftLeft = new ConsFalse().apply(self)
    override type shiftLeft = ConsFalse#apply[self]

    override  def shiftRight: shiftRight = tail
    override type shiftRight = tail

    override  def toPeano: toPeano = peano.Succ(decrement.toPeano)
    override type toPeano = peano.Succ[decrement#toPeano]

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = f.apply(self, decrement.foldRightWithNat(z, f))
    override type foldRightWithNat[z <: Any, f <: Function2] = f#apply[self, decrement#foldRightWithNat[z, f]]

    override def undual: undual = (if (head.undual) 1 else 0) + (2 * tail.undual)
}


private[mada] object _Dense {
    val Nil = new Nil{}
}
