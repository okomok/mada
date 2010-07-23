

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map; package bstree


private[mada] final class Clear {
     def apply[m <: BSTree](m: m): apply[m] = Nil(m.ord)
    type apply[m <: BSTree] = Nil[m#ord]
}
