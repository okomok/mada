

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class ScanLeft[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends TrivialForwarder {
    type self = ScanLeft[xs, z, f]

    override protected lazy val delegate: delegate = new Cons(z, new _ScanLeft(xs, z, f))
    override protected type delegate               =     Cons[z,     _ScanLeft[xs, z, f]]
}

final class _ScanLeft[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends AbstractList {
    type self = _ScanLeft[xs, z, f]

    override  def isEmpty: isEmpty = xs.isEmpty
    override type isEmpty          = xs#isEmpty

    override lazy val head: head = f.apply(z, xs.head)
    override type head           = f#apply[z, xs#head]

    override  def tail: tail = new _ScanLeft(xs.tail, head, f)
    override type tail       =     _ScanLeft[xs#tail, head, f]
}


final class ScanRight[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends TrivialForwarder {
    type self = ScanRight[xs, z, f]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty, const0(single(z)), new Else).apply.asInstanceOfList.asInstanceOf[delegate]
    override protected type delegate =
        `if`[xs#isEmpty, const0[single[z]],     Else]#apply#asInstanceOfList

    class Else extends Function0 {
        type self = Else
        private lazy val ys: ys = new ScanRight(xs.tail, z, f)
        private type ys         =     ScanRight[xs#tail, z, f]
        override  def apply: apply = new Cons(f.apply(xs.head, ys.head), ys)
        override type apply        =     Cons[f#apply[xs#head, ys#head], ys]
    }
}
