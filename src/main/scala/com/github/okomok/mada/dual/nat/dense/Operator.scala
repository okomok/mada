

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


/**
 * Contains infix operators for Dense.
 */
@deprecated("danger!")// crashes compiler!!
object Operator extends OperatorCommon


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
