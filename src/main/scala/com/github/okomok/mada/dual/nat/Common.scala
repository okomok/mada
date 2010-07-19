

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


private[mada] class Common {
    @aliasOf("dense.Dense")
     val Dense = dense.Dense
    type Dense = dense.Dense

    @aliasOf("peano.Peano")
     val Peano = peano.Peano
    type Peano = peano.Peano

    /**
     * The natural equivalence of Nat
     */
     val eqv: eqv = new Eqv
    type eqv = Eqv

    /**
     * The natural ordering of Nat
     */
     val ord: ord = new Ord
    type ord = Ord
}


/**
 * Contains operators for Dense.
 */
@compilerWorkaround("2.8.0") // not extended by `Common` to avoid "error: type _140.type is defined twice".
object Operator extends OperatorCommon


private[mada] trait OperatorCommon {
    type  +[x <: Nat, y <: Nat] = x# +[y]
    type  -[x <: Nat, y <: Nat] = x# -[y]
    type **[x <: Nat, y <: Nat] = x# **[y]
    type /[x <: Nat, y <: Nat] = x# /[y]
    type %[x <: Nat, y <: Nat] = x# %[y]
    type ^[x <: Nat, y <: Nat] = x# ^[y]

    type ===[x <: Nat, y <: Nat] = x# ===[y]
    type !==[x <: Nat, y <: Nat] = x# !==[y]

    type  <[x <: Nat, y <: Nat] = x# <[y]
    type <=[x <: Nat, y <: Nat] = x# <=[y]
    type  >[x <: Nat, y <: Nat] = x# >[y]
    type >=[x <: Nat, y <: Nat] = x# >=[y]

    type &[x <: Nat, y <: Nat] = x# &[y]
    type |[x <: Nat, y <: Nat] = x# |[y]
}
