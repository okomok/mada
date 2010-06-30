

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


/**
 * Contains infix operators for Nat.
 */
@deprecated("danger!")
object Operator {// crashes compiler!!
    type  +[x <: Nat, y <: Nat] = x# +[y]
    type  -[x <: Nat, y <: Nat] = x# -[y]
//    type *[x <: Nat, y <: Nat] = x# *[y]

    type ===[x <: Nat, y <: Nat] = x# ===[y]
    type !==[x <: Nat, y <: Nat] = x# !==[y]

    type  <[x <: Nat, y <: Nat] = x# <[y]
    type <=[x <: Nat, y <: Nat] = x# <=[y]
    type  >[x <: Nat, y <: Nat] = x# >[y]
    type >=[x <: Nat, y <: Nat] = x# >=[y]
}
