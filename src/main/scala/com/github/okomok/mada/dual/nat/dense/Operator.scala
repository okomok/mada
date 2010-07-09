

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


/**
 * Contains infix operators for Dense.
 */
object Operator extends OperatorCommon // may crash compiler.


trait OperatorCommon {
    type  +[x <: Dense, y <: Dense] = x# +[y]
    type  -[x <: Dense, y <: Dense] = x# -[y]
    type **[x <: Dense, y <: Dense] = x# **[y]

    type ===[x <: Dense, y <: Dense] = x# ===[y]
    type !==[x <: Dense, y <: Dense] = x# !==[y]

    type  <[x <: Dense, y <: Dense] = x# <[y]
    type <=[x <: Dense, y <: Dense] = x# <=[y]
    type  >[x <: Dense, y <: Dense] = x# >[y]
    type >=[x <: Dense, y <: Dense] = x# >=[y]
}
