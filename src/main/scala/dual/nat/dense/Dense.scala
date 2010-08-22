

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


// See: http://apocalisp.wordpress.com/2010/06/24/type-level-programming-in-scala-part-5a-binary-numbers/


object Dense extends Common


sealed abstract class Dense extends AbstractNat {
    type self <: Dense

     def head: head
    type head <: Boolean

     def tail: tail
    type tail <: Dense

     def size: size
    type size <: Peano

    override type increment <: Dense
    override type decrement <: Dense
    override type plus[that <: Nat] <: Dense
    override type minus[that <: Nat] <: Dense
    override type times[that <: Nat] <: Dense
    override type bitAnd[that <: Nat] <: Dense
    override type bitOr[that <: Nat] <: Dense

    @equivalentTo("Cons(e. self)")
     def ::[e <: Boolean](e: e): ::[e]
    type ::[e <: Boolean] <: Dense

     def shiftLeft: shiftLeft
    type shiftLeft <: Dense

     def shiftRight: shiftRight
    type shiftRight <: Dense

     def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f]
    type foldRightWithNat[z <: Any, f <: Function2] <: Any

     def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n]
    type shiftLeftBy[n <: Peano] <: Dense
}


private[dual]
sealed abstract class AbstractDense extends Dense {
    final override  def asNatDense: asNatDense = self
    final override type asNatDense             = self

    @equivalentTo("Cons(e. self)")
    final override  def ::[e <: Boolean](e: e): ::[e] = Cons(e, self)
    final override type ::[e <: Boolean]              = Cons[e, self]

    final override  def plus[that <: Nat](that: that): plus[that] = Plus.apply(self, that.asNatDense)
    final override type plus[that <: Nat]                         = Plus.apply[self, that#asNatDense]

    final override  def minus[that <: Nat](that: that): minus[that] = Minus.apply(self, that.asNatDense)
    final override type minus[that <: Nat]                          = Minus.apply[self, that#asNatDense]

    final override  def divMod[that <: Nat](that: that): divMod[that] = DivMod.apply(self, that.asNatDense)
    final override type divMod[that <: Nat]                           = DivMod.apply[self, that#asNatDense]

    final override  def equal[that <: Nat](that: that): equal[that] = Equal.apply(self, that.asNatDense)
    final override type equal[that <: Nat]                        = Equal.apply[self, that#asNatDense]

    final override  def lt[that <: Nat](that: that): lt[that] = Lt.apply(self, that.asNatDense)
    final override type lt[that <: Nat]                       = Lt.apply[self, that#asNatDense]

    final override  def lteq[that <: Nat](that: that): lteq[that] = that.asNatDense.lt(self).not
    final override type lteq[that <: Nat]                         = that#asNatDense#lt[self]#not

    final override  def bitAnd[that <: Nat](that: that): bitAnd[that] = BitAnd.apply(self, that.asNatDense)
    final override type bitAnd[that <: Nat]                           = BitAnd.apply[self, that#asNatDense]

    final override  def bitOr[that <: Nat](that: that): bitOr[that] = BitOr.apply(self, that.asNatDense)
    final override type bitOr[that <: Nat]                          = BitOr.apply[self, that#asNatDense]
}


sealed class Nil extends AbstractDense {
    type self = Nil

    override  def undual: undual = 0

    override  def head: head = noSuchElement("nat.dense.Nil.head")
    override type head       = noSuchElement[_]

    override  def tail: tail = noSuchElement("nat.dense.Nil.tail")
    override type tail       = noSuchElement[_]

    override  val size: size = peano.Zero
    override type size       = peano.Zero

    override  def isZero: isZero = `true`
    override type isZero         = `true`

    override  def increment: increment = Cons(`true`, self)
    override type increment            = Cons[`true`, self]

    override  def decrement: decrement = unsupported("nat.dense.Nil.decrement")
    override type decrement            = unsupported[_]

    override  def times[that <: Nat](that: that): times[that] = self
    override type times[that <: Nat]                          = self

    override  def exp[that <: Nat](that: that): exp[that] = `if`(that.isZero, const0(_1), const0(self)).apply.asNatDense
    override type exp[that <: Nat]                        = `if`[that#isZero, const0[_1], const0[self]]#apply#asNatDense

    override  def shiftLeft: shiftLeft = self
    override type shiftLeft            = self

    override  def shiftRight: shiftRight = self
    override type shiftRight             = self

    override  def asNatPeano: asNatPeano = peano.Zero
    override type asNatPeano             = peano.Zero

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = z
    override type foldRightWithNat[z <: Any, f <: Function2]                                     = z

    override  def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n] = self
    override type shiftLeftBy[n <: Peano]                       = self
}


final case class Cons[x <: Boolean, xs <: Dense](override val head: x, override val tail: xs) extends AbstractDense {
    assert(head.or(tail.isZero.not))

    type self = Cons[x, xs]

    override  def undual: undual = (if (head.undual) 1 else 0) + (2 * tail.undual)

    override type head = x
    override type tail = xs

    override  val size: size = tail.size.increment
    override type size       = tail#size#increment

    override  def isZero: isZero = `false`
    override type isZero         = `false`

    override  def increment: increment = ConsIncrement.apply(head, tail)
    override type increment            = ConsIncrement.apply[head, tail]

    override  def decrement: decrement = ConsDecrement.apply(head, tail)
    override type decrement            = ConsDecrement.apply[head, tail]

    override  def times[that <: Nat](that: that): times[that] = ConsTimes.apply(head, tail, that.asNatDense)
    override type times[that <: Nat]                          = ConsTimes.apply[head, tail, that#asNatDense]

    override  def exp[that <: Nat](that: that): exp[that] = ConsExp.apply(self, that)
    override type exp[that <: Nat]                        = ConsExp.apply[self, that]

    override  def shiftLeft: shiftLeft = ConsFalse.apply(self)
    override type shiftLeft            = ConsFalse.apply[self]

    override  def shiftRight: shiftRight = tail
    override type shiftRight             = tail

    override  def asNatPeano: asNatPeano = peano.Succ(decrement.asNatPeano)
    override type asNatPeano             = peano.Succ[decrement#asNatPeano]

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = f.apply(self, decrement.foldRightWithNat(z, f))
    override type foldRightWithNat[z <: Any, f <: Function2]                                     = f#apply[self, decrement#foldRightWithNat[z, f]]

    override  def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n] = ConsShiftLeftBy.apply(self, n)
    override type shiftLeftBy[n <: Peano]                       = ConsShiftLeftBy.apply[self, n]
}


private[dual]
object _Dense {
    val Nil = new Nil{}
}
