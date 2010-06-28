

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


import nat._


sealed trait Nat extends Operatable {
    type self <: Nat

    private[mada] type isZero <: Boolean
    private[mada] type gtZero <: Boolean

    final type equals[that <: Nat] = subtract[that]#isZero
    final override type Operand_== = Nat
    final override type operator_==[that <: Nat] = equals[that]

    type increment <: Nat
    type decrement <: Nat

    final type add[that <: Nat] = foldRight_Nat[that, Function2[Nat, Nat, Nat]{ type apply[_ <: Nat, b <: Nat] = b#increment }]
    final override type Operand_+ = Nat
    final override type operator_+[that <: Nat] = add[that]
    final type subtract[that <: Nat] = that#foldRight_Nat[self, Function2[Nat, Nat, Nat]{ type apply[_ <: Nat, b <: Nat] = b#decrement }]
    final override type Operand_- = Nat
    final override type operator_-[that <: Nat] = subtract[that]

    final type multiply[that <: Nat] = foldRight_Nat[Zero, Function2[Nat, Nat, Nat]{ type apply[_ <: Nat, b <: Nat] = that#add[b] }]
    final override type Operand_* = Nat
    final override type operator_*[that <: Nat] = multiply[that]

    final type gt[that <: Nat] = subtract[that]#gtZero
    final type lt[that <: Nat] = that#gt[self]
    final type gteq[that <: Nat] = gt[that]#or[equals[that]]
    final type lteq[that <: Nat] = that#gteq[self]
    final override type Operand_< = Nat
    final override type Operand_<= = Nat
    final override type Operand_> = Nat
    final override type Operand_>= = Nat
    final override type operator_<[that <: Nat] = lt[that]
    final override type operator_<=[that <: Nat] = lteq[that]
    final override type operator_>[that <: Nat] = gt[that]
    final override type operator_>=[that <: Nat] = gteq[that]

    type foldRight_Any[z <: Any, f <: Function2[Nat, Any, Any]] <: Any
    type foldRight_Nat[z <: Nat, f <: Function2[Nat, Nat, Nat]] <: Nat
    type foldRight_blendList[z <: blend.List, f <: Function2[Nat, blend.List, blend.List]] <: blend.List

    type accept_Any[v <: Visitor[Any]] <: Any
    type accept_Nat[v <: Visitor[Nat]] <: Nat
    type accept_blendList[v <: Visitor[blend.List]] <: blend.List
}


// "case classes"

class Zero extends Nat {
    override type self = Zero

    override private[mada] type isZero = `true`
    override private[mada] type gtZero = `false`

    override type increment = Succ[Zero]
    override type decrement = sentinel

    override type foldRight_Any[z <: Any, f <: Function2[Nat, Any, Any]] = z
    override type foldRight_Nat[z <: Nat, f <: Function2[Nat, Nat, Nat]] = z
    override type foldRight_blendList[z <: blend.List, f <: Function2[Nat, blend.List, blend.List]] = z

    override type accept_Any[v <: Visitor[Any]] = v#visitZero
    override type accept_Nat[v <: Visitor[Nat]] = v#visitZero
    override type accept_blendList[v <: Visitor[blend.List]] = v#visitZero
}

class Succ[n <: Nat] extends Nat {
    override type self = Succ[n]

    override private[mada] type isZero = `false`
    override private[mada] type gtZero = `true`

    override type increment = Succ[self]
    override type decrement = n

    override type foldRight_Any[z <: Any, f <: Function2[Nat, Any, Any]] = f#apply[self, n#foldRight_Any[z, f]]
    override type foldRight_Nat[z <: Nat, f <: Function2[Nat, Nat, Nat]] = f#apply[self, n#foldRight_Nat[z, f]]
    override type foldRight_blendList[z <: blend.List, f <: Function2[Nat, blend.List, blend.List]] = f#apply[self, n#foldRight_blendList[z, f]]

    override type accept_Any[v <: Visitor[Any]] = v#visitSucc[n]
    override type accept_Nat[v <: Visitor[Nat]] = v#visitSucc[n]
    override type accept_blendList[v <: Visitor[blend.List]] = v#visitSucc[n]
}


// sentinel value

sealed trait sentinel extends Nat {
    override type self = sentinel

    override private[mada] type isZero = `false`
    override private[mada] type gtZero = `false`

    override type increment = `null`
    override type decrement = self

    override type foldRight_Any[z <: Any, f <: Function2[Nat, Any, Any]] = `null`
    override type foldRight_Nat[z <: Nat, f <: Function2[Nat, Nat, Nat]] = `null`
    override type foldRight_blendList[z <: blend.List, f <: Function2[Nat, blend.List, blend.List]] = `null`

    override type accept_Any[v <: Visitor[Any]] = `null`
    override type accept_Nat[v <: Visitor[Nat]] = `null`
    override type accept_blendList[v <: Visitor[blend.List]] = `null`
}
