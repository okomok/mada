

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


// See: http://apocalisp.wordpress.com/2010/06/24/type-level-programming-in-scala-part-5a-binary-numbers/


object Dense extends Common


sealed abstract class Dense extends Nat {
    type self <: Dense

    final override  def asInstanceOfNatDense = self
    final override type asInstanceOfNatDense = self

     def head: head
    type head <: Boolean

     def tail: tail
    type tail <: Dense

     def size: size
    type size <: Peano

    override type increment <: Dense
    override type decrement <: Dense

    @equivalentTo("Cons(e. self)")
    final def ::[e <: Boolean](e: e): Cons[e, self] = Cons(e, self)

    final override  def +[that <: Nat](that: that): +[that] = new Add().apply(self, that.toDense)
    final override type +[that <: Nat] = Add#apply[self, that#toDense]

    final override  def -[that <: Nat](that: that): -[that] = new Subtract().apply(self, that.toDense)
    final override type -[that <: Nat] = Subtract#apply[self, that#toDense]

    override type **[that <: Nat] <: Dense

    final override  def divMod[that <: Nat](that: that): divMod[that] = new DivMod().apply(self, that.toDense)
    final override type divMod[that <: Nat] = DivMod#apply[self, that#toDense]

    final override  def ===[that <: Nat](that: that): ===[that] = new Equals().apply(self, that.toDense)
    final override type ===[that <: Nat] = Equals#apply[self, that#toDense]

    final override  def <[that <: Nat](that: that): <[that] = new LessThan().apply(self, that.toDense)
    final override type <[that <: Nat] = LessThan#apply[self, that#toDense]

    final override  def <=[that <: Nat](that: that): <=[that] = (that.toDense < self).not
    final override type <=[that <: Nat] = that#toDense# <[self]#not

    final override  def &[that <: Nat](that: that): &[that] = new BitAnd().apply(self, that.toDense)
    final override type &[that <: Nat] = BitAnd#apply[self, that#toDense]

    final override  def |[that <: Nat](that: that): |[that] = new BitOr().apply(self, that.toDense)
    final override type |[that <: Nat] = BitOr#apply[self, that#toDense]

     def shiftLeft: shiftLeft
    type shiftLeft <: Dense

     def shiftRight: shiftRight
    type shiftRight <: Dense

    final override  def toDense: toDense = self
    final override type toDense = self

     def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f]
    type foldRightWithNat[z <: Any, f <: Function2] <: Any

     def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n]
    type shiftLeftBy[n <: Peano] <: Dense
}


sealed class Nil extends Dense {
    override  val self = this
    override type self = Nil

    override  def head: head = `throw`(new scala.NoSuchElementException("dual.nat.dense.Nil.head"))
    override type head = `throw`[scala.NoSuchElementException]

    override  def tail: tail = `throw`(new scala.NoSuchElementException("dual.nat.dense.Nil.tail"))
    override type tail = `throw`[scala.NoSuchElementException]

    override  val size: size = peano.Zero
    override type size = peano.Zero

    override  def isZero: isZero = `true`
    override type isZero = `true`

    override  def increment: increment = Cons(`true`, self)
    override type increment = Cons[`true`, self]

    override  def decrement: decrement = unsupported("dual.nat.dense.Nil.decrement")
    override type decrement = unsupported[_]

    override  def **[that <: Nat](that: that): **[that] = self
    override type **[that <: Nat] = self

    override  def ^[that <: Nat](that: that): ^[that] = new NilExp().apply(self, that)
    override type ^[that <: Nat] = NilExp#apply[self, that]

    override  def shiftLeft: shiftLeft = self
    override type shiftLeft = self

    override  def shiftRight: shiftRight = self
    override type shiftRight = self

    override  def toPeano: toPeano = peano.Zero
    override type toPeano = peano.Zero

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = z
    override type foldRightWithNat[z <: Any, f <: Function2] = z

    override  def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n] = self
    override type shiftLeftBy[n <: Peano] = self

    override def undual: undual = 0
}


final case class Cons[x <: Boolean, xs <: Dense](override val head: x, override val tail: xs) extends Dense {
    assert(head || tail.isZero.not)

    override  val self = this
    override type self = Cons[x, xs]

    override type head = x
    override type tail = xs

    override  val size: size = tail.size.increment
    override type size = tail#size#increment

    override  def isZero: isZero = `false`
    override type isZero = `false`

    override  def increment: increment = new ConsIncrement().apply(head, tail)
    override type increment = ConsIncrement#apply[head, tail]

    override  def decrement: decrement = new ConsDecrement().apply(head, tail)
    override type decrement = ConsDecrement#apply[head, tail]

    override  def **[that <: Nat](that: that): **[that] = new ConsMultiply().apply(head, tail, that.toDense)
    override type **[that <: Nat] = ConsMultiply#apply[head, tail, that#toDense]

    override  def ^[that <: Nat](that: that): ^[that] = new ConsExp().apply(self, that)
    override type ^[that <: Nat] = ConsExp#apply[self, that]

    override  def shiftLeft: shiftLeft = new ConsFalse().apply(self)
    override type shiftLeft = ConsFalse#apply[self]

    override  def shiftRight: shiftRight = tail
    override type shiftRight = tail

    override  def toPeano: toPeano = new ConsToPeano().apply(self)//peano.Succ(decrement.toPeano)
    override type toPeano = ConsToPeano#apply[self]//peano.Succ[decrement#toPeano]

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = f.apply(self, decrement.foldRightWithNat(z, f))
    override type foldRightWithNat[z <: Any, f <: Function2] = f#apply[self, decrement#foldRightWithNat[z, f]]

    override  def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n] = new ConsShiftLeftBy().apply(self, n)
    override type shiftLeftBy[n <: Peano] = ConsShiftLeftBy#apply[self, n]

    override def undual: undual = (if (head.undual) 1 else 0) + (2 * tail.undual)
}


private[mada] object _Dense {
    val Nil = new Nil{}
}
