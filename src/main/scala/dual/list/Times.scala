

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


/* too slow to compile.
private[mada]
final class Times[xs <: List, n <: Nat](xs: xs, n: n) {
     def apply = repeat(Unit).take(n).flatMap(new Const)
    type apply = repeat[Unit]#take[n]#flatMap[    Const]

    class Const extends Function1 {
        type self = Const
        override  def apply[x <: Any](x: x): apply[x] = xs
        override type apply[x <: Any]                 = xs
    }
}
*/


private[mada]
final class Times[xs <: List, n <: Nat](xs: xs, n: n) extends AbstractList {
    type self = Times[xs, n]

    override  def isEmpty: isEmpty = xs.isEmpty  || n.isZero
    override type isEmpty          = xs#isEmpty# ||[n#isZero]

    override  def head: head = xs.head
    override type head       = xs#head

    override  def tail: tail = xs.tail  ++ new Times(xs, n.decrement)
    override type tail       = xs#tail# ++[    Times[xs, n#decrement]]
}
