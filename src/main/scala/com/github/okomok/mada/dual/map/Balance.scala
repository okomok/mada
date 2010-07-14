

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


// TODO...

private[mada] case class Balance[k <: Any, v <: Any, l <: Map, r <: Map, o <: Ordering](k: k, v: v, l: l, r: r, o: o) extends Function0 {
    override  val self = this
    override type self = Balance[k, v, l, r, o]

    private val newsize: newsize = (l.size + r.size).increment.asInstanceOf[newsize]
    private type newsize = l#size# +[r#size]#increment

    override  def apply: apply = Node(newsize, k, v, l, r, o)
    override type apply = Node[newsize, k, v, l, r, o]
}
