

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package boolean


/**
 * Contains infix operators for Boolean.
 */
@deprecated("danger!")
object Operator {
    type ===[x <: Boolean, y <: Boolean] = x# ===[y]
    type !==[x <: Boolean, y <: Boolean] = x# !==[y]

    type &&[x <: Boolean, y <: Boolean] = x# &&[y]
    type ||[x <: Boolean, y <: Boolean] = x# ||[y]
}
