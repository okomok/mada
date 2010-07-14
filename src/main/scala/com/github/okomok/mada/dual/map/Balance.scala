

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


// TODO...

private[mada] class Balance {
    private  def newsize[l <: Map, r <: Map](l: l, r: r): newsize[l, r] = (l.size + r.size).increment.asInstanceOf[newsize[l, r]]
    private type newsize[l <: Map, r <: Map] = l#size# +[r#size]#increment

     def apply[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o): apply[k, v, l, r, o] =
        Node(newsize(l, r), k, v, l, r, o)
    type apply[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering] =
        Node[newsize[l, r], k, v, l, r, o]
}
