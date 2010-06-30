

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


import nat._


sealed trait Nat extends Operatable_===
    with Operatable_+ with Operatable_- with Operatable_**
    with Operatable_< with Operatable_<= with Operatable_> with Operatable_>= {

     def self: self
    type self <: Nat

    final override type Operand_=== = Nat
    final override type Operand_+ = Nat
    final override type Operand_- = Nat
    final override type Operand_** = Nat
    final override type Operand_< = Nat
    final override type Operand_<= = Nat
    final override type Operand_> = Nat
    final override type Operand_>= = Nat

    private[mada]  def isZero: isZero
    private[mada] type isZero <: Boolean

    private[mada]  def gtZero: gtZero
    private[mada] type gtZero <: Boolean

    final  def ===[that <: Nat](that: that): ===[that] = this.-(that).isZero
    final type ===[that <: Nat] = -[that]#isZero

     def increment: increment
    type increment <: Nat

     def decrement: decrement
    type decrement <: Nat

    final override  def +[that <: Nat](that: that): +[that] = Add.apply(self, that)
    final override type +[that <: Nat] = Add.apply[self, that]

    final override  def -[that <: Nat](that: that): -[that] = Subtract.apply(self, that)
    final override type -[that <: Nat] = Subtract.apply[self, that]

    final override  def **[that <: Nat](that: that): **[that] = Multiply.apply(self, that)
    final override type **[that <: Nat] = Multiply.apply[self, that]

    final override  def >[that <: Nat](that: that): >[that] = this.-(that).gtZero
    final override type >[that <: Nat] = -[that]#gtZero

    final override  def <[that <: Nat](that: that): <[that] = that.>(self)
    final override type <[that <: Nat] = that# >[self]

    final override  def >=[that <: Nat](that: that): >=[that] = >(that).||(===(that))
    final override type >=[that <: Nat] = >[that]# ||[===[that]]

    final override  def <=[that <: Nat](that: that): <=[that] = that.>=(self)
    final override type <=[that <: Nat] = that# >=[self]

     def foldRight_Any[z <: Any, f <: Function2_Nat_Any_Any](z: z, f: f): foldRight_Any[z, f]
    type foldRight_Any[z <: Any, f <: Function2_Nat_Any_Any] <: Any

     def foldRight_Nat[z <: Nat, f <: Function2_Nat_Nat_Nat](z: z, f: f): foldRight_Nat[z, f]
    type foldRight_Nat[z <: Nat, f <: Function2_Nat_Nat_Nat] <: Nat

     def foldRight_List[z <: List, f <: Function2_Nat_List_List](z: z, f: f): foldRight_List[z, f]
    type foldRight_List[z <: List, f <: Function2_Nat_List_List] <: List

    type accept_Any[v <: Visitor[Any]] <: Any
    type accept_Nat[v <: Visitor[Nat]] <: Nat
    type accept_blendList[v <: Visitor[blend.List]] <: blend.List

    final  def toSInt: toSInt = ToSInt.apply(self)
    final type toSInt = ToSInt.apply[self]
}


sealed trait Zero extends Nat {
    override  def self = this
    override type self = Zero

    override private[mada]  def isZero = `true`
    override private[mada] type isZero = `true`

    override private[mada]  def gtZero = `false`
    override private[mada] type gtZero = `false`

    override  def increment = new Succ(self)
    override type increment = Succ[self]

    override  def decrement = singular
    override type decrement = singular

    override  def foldRight_Any[z <: Any, f <: Function2_Nat_Any_Any](z: z, f: f): foldRight_Any[z, f] = z
    override type foldRight_Any[z <: Any, f <: Function2_Nat_Any_Any] = z

    override  def foldRight_Nat[z <: Nat, f <: Function2_Nat_Nat_Nat](z: z, f: f): foldRight_Nat[z, f] = z
    override type foldRight_Nat[z <: Nat, f <: Function2_Nat_Nat_Nat] = z

    override  def foldRight_List[z <: List, f <: Function2_Nat_List_List](z: z, f: f): foldRight_List[z, f] = z
    override type foldRight_List[z <: List, f <: Function2_Nat_List_List] = z
}


class Succ[n <: Nat](n: n) extends Nat {
    override  def self = this
    override type self = Succ[n]

    override private[mada]  def isZero = `false`
    override private[mada] type isZero = `false`

    override private[mada]  def gtZero = `true`
    override private[mada] type gtZero = `true`

    override  def increment = new Succ(self)
    override type increment = Succ[self]

    override  def decrement = n
    override type decrement = n

    override  def foldRight_Any[z <: Any, f <: Function2_Nat_Any_Any](z: z, f: f): foldRight_Any[z, f] = f.apply(self, n.foldRight_Any(z, f))
    override type foldRight_Any[z <: Any, f <: Function2_Nat_Any_Any] = f#apply[self, n#foldRight_Any[z, f]]

    override  def foldRight_Nat[z <: Nat, f <: Function2_Nat_Nat_Nat](z: z, f: f): foldRight_Nat[z, f] = f.apply(self, n.foldRight_Nat(z, f))
    override type foldRight_Nat[z <: Nat, f <: Function2_Nat_Nat_Nat] = f#apply[self, n#foldRight_Nat[z, f]]

    override  def foldRight_List[z <: List, f <: Function2_Nat_List_List](z: z, f: f): foldRight_List[z, f] = f.apply(self, n.foldRight_List(z, f))
    override type foldRight_List[z <: List, f <: Function2_Nat_List_List] = f#apply[self, n#foldRight_List[z, f]]
}


sealed trait singular extends Nat {
    override  def self = this
    override type self = singular

    override private[mada]  def isZero = `false`
    override private[mada] type isZero = `false`

    override private[mada]  def gtZero = `false`
    override private[mada] type gtZero = `false`

    override  def increment: increment = unsupported
    override type increment = unsupported

    override  def decrement = self
    override type decrement = self

    override  def foldRight_Any[z <: Any, f <: Function2_Nat_Any_Any](z: z, f: f) = unsupported
    override type foldRight_Any[z <: Any, f <: Function2_Nat_Any_Any] = unsupported

    override  def foldRight_Nat[z <: Nat, f <: Function2_Nat_Nat_Nat](z: z, f: f) = unsupported
    override type foldRight_Nat[z <: Nat, f <: Function2_Nat_Nat_Nat] = unsupported

    override  def foldRight_List[z <: List, f <: Function2_Nat_List_List](z: z, f: f) = unsupported
    override type foldRight_List[z <: List, f <: Function2_Nat_List_List] = unsupported
}


object Nat {
    private[mada] val _Zero = new Zero{}
    private[mada] val _singular = new singular{}
}
