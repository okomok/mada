

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class ScanLeft[xs <: Seq, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends AbstractSeq {
    type self = ScanLeft[xs, z, f]

    override  def isEmpty: isEmpty = `false`
    override type isEmpty          = `false`

    override  def head: head = z
    override type head       = z

    override  def tail: tail = new ScanLeft(xs.tail, f.apply(z, xs.head), f)
    override type tail       =     ScanLeft[xs#tail, f#apply[z, xs#head], f]
}


final class ScanRight[xs <: Seq, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends TrivialForwarder {
    type self = ScanRight[xs, z, f]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty, Const0(single(z)), new Else).apply.asInstanceOfSeq.asInstanceOf[delegate]
    override protected type delegate =
        `if`[xs#isEmpty, Const0[single[z]],     Else]#apply#asInstanceOfSeq

    class Else extends Function0 {
        type self = Else
        private lazy val ys: ys = new ScanRight(xs.tail, z, f)
        private type ys         =     ScanRight[xs#tail, z, f]
        override  def apply: apply = new AddFirst(ys, f.apply(xs.head, ys.head))
        override type apply        =     AddFirst[ys, f#apply[xs#head, ys#head]]
    }
}
