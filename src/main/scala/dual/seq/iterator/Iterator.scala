

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package iterator


object Iterator extends Common


/**
 * The dual Iterator
 */
trait Iterator extends Any with ReferenceEquality {
    type self <: Iterator
    type asInstanceOfIterator = self

     def isEnd: isEnd
    type isEnd <: Boolean

     def deref: deref
    type deref <: Any

     def next: next
    type next <: Iterator
/*
     def category: category
    type category

     def isBegin: isBegin = unsupported("Iterator.isBegin")
    type isBegin <: Boolean

     def previous: previous = unsupported("Iterator.previous")
    type previous <: Iterator

     def advance[n <: Int]: advance
    type advance[n <: Int] <: Iterator

     def distanceTo[that <: Iterator](that: that) = unsupported("Iterator.distanceTo")
    type distanceTo[that <: Iterator] <: Int
*/
}


/**
 * The End Iterator
 */
sealed abstract class End extends AbstractIterator {
    type self = End

    override  def isEnd: isEnd = `true`
    override type isEnd        = `true`

    override  def deref: deref = noSuchElement("seq.iterator.End.deref")
    override type deref        = noSuchElement[_]

    override  def next: next = noSuchElement("seq.iterator.End.next")
    override type next       = noSuchElement[_]
}

private[mada] object _Iterator {
    val End = new End{}
}
