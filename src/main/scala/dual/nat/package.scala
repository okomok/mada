

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


package object nat extends nat.Common {
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
