

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


trait Common {

     def empty[o <: Ordering](o: o): empty[o] = Nil(o)
    type empty[o <: Ordering] = Nil[o]

     def single[k <: Any, v <: Any, o <: Ordering](k: k, v: v, o: o): single[k, v, o] = Node(nat.peano._1, k, v, Nil(o), Nil(o), o)
    type single[k <: Any, v <: Any, o <: Ordering] = Node[nat.peano._1, k, v, Nil[o], Nil[o], o]

}
