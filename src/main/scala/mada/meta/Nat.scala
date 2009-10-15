

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


import nat._


sealed trait Nat extends Operatable {
    type `this` <: Nat

    private[mada] type isZero <: Boolean
    type equals[that <: Nat] <: Boolean
    final override type Operand_== = Nat
    final override type operator_==[that <: Nat] = equals[that]

    type increment <: Nat
    type decrement <: Nat

    type add[that <: Nat] <: Nat
    final override type Operand_+ = Nat
    final override type operator_+[that <: Nat] = add[that]

    private[mada] type negateAdd[that <: Nat] <: Nat
    final type subtract[that <: Nat] = that#negateAdd[`this`]
    final override type Operand_- = Nat
    final override type operator_-[that <: Nat] = subtract[that]

    type multiply[that <: Nat] <: Nat

    type accept_Any[v <: Visitor[Any]] <: Any
    type accept_Nat[v <: Visitor[Nat]] <: Nat
    type accept_blendList[v <: Visitor[blend.List]] <: blend.List
}


// "case classes"

sealed trait Zero extends Nat {
    override type `this` = Zero
    override private[mada] type isZero = `true`
    override type equals[that <: Nat] = that#isZero
    override type increment = Succ[Zero]
    override type decrement = sentinel
    override type add[that <: Nat] = that
    override private[mada] type negateAdd[that <: Nat] = that
    override type multiply[that <: Nat] = Zero

    override type accept_Any[v <: Visitor[Any]] = v#visitZero
    override type accept_Nat[v <: Visitor[Nat]] = v#visitZero
    override type accept_blendList[v <: Visitor[blend.List]] = v#visitZero
}

sealed trait Succ[n <: Nat] extends Nat {
    override type `this` = Succ[n]
    override private[mada] type isZero = `false`
    override type equals[that <: Nat] = n#equals[that#decrement]
    override type increment = Succ[`this`]
    override type decrement = n
    override type add[that <: Nat] = Succ[n#add[that]]
    override private[mada] type negateAdd[that <: Nat] = n#negateAdd[that#decrement]
    override type multiply[that <: Nat] = n#multiply[that]#add[that]

    override type accept_Any[v <: Visitor[Any]] = v#visitSucc[n]
    override type accept_Nat[v <: Visitor[Nat]] = v#visitSucc[n]
    override type accept_blendList[v <: Visitor[blend.List]] = v#visitSucc[n]
}


// sentinel value

sealed trait sentinel extends Nat {
    override type `this` = sentinel
    override private[mada] type isZero = `false`
    override type equals[that <: Nat] = `false`
    override type increment = error
    override type decrement = `this`
    override type add[that <: Nat] = error
    override private[mada] type negateAdd[that <: Nat] = error
    override type multiply[that <: Nat] = error

    override type accept_Any[v <: Visitor[Any]] = error
    override type accept_Nat[v <: Visitor[Nat]] = error
    override type accept_blendList[v <: Visitor[blend.List]] = error
}
