

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final case class ListIterator[xs <: List](xs: xs) extends seq.Iterator {
    type self = ListIterator[xs]

     def isEnd: isEnd = xs.isEmpty
    type isEnd        = xs#isEmpty

     def deref: deref = xs.head
    type deref        = xs#head

     def next: next = ListIterator(xs.tail)
    type next       = ListIterator[xs#tail]
}
