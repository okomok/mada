

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.gxshub.okomok.mada
package dual; package seq


sealed abstract class Empty extends AbstractSeq {
    type self = Empty

    override  def isEmpty: isEmpty = `true`
    override type isEmpty          = `true`

    override  def head: head = noSuchElement("seq.Empty.head")
    override type head       = noSuchElement[_]

    override  def tail: tail = noSuchElement("seq.Empty.tail")
    override type tail       = noSuchElement[_]
}

private[seq] object _Empty {
    val value = new Empty{}
}
