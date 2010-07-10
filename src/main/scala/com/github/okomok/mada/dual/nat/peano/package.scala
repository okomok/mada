

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


package object peano extends peano.Common {
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
