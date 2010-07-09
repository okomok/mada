

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


/**
 * Contains infix operators for Peano.
 */
object Operator extends OperatorCommon // may crash compiler.


trait OperatorCommon {
    type  +[x <: Peano, y <: Peano] = x# +[y]
    type  -[x <: Peano, y <: Peano] = x# -[y]
    type **[x <: Peano, y <: Peano] = x# **[y]

    type ===[x <: Peano, y <: Peano] = x# ===[y]
    type !==[x <: Peano, y <: Peano] = x# !==[y]

    type  <[x <: Peano, y <: Peano] = x# <[y]
    type <=[x <: Peano, y <: Peano] = x# <=[y]
    type  >[x <: Peano, y <: Peano] = x# >[y]
    type >=[x <: Peano, y <: Peano] = x# >=[y]
}
