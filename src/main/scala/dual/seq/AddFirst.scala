

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class AddFirst[xs <: Seq, e <: Any](override val tail: xs, override val head: e) extends AbstractSeq {
    type self = AddFirst[xs, e]

    override  def isEmpty: isEmpty = `false`
    override type isEmpty          = `false`

    override type head = e
    override type tail = xs
}
