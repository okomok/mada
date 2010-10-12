

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


// List

private[sequence]
case class FromJList[A](_1: java.util.List[A]) extends Forwarder[A] {
    override protected val delegate: Vector[A] = _1 match {
        case ToJList(from) => from // from-to fusion
        case _ => new _FromJList(_1)
    }
}

private
class _FromJList[A](_1: java.util.List[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.size
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: A) = _1.set(i, e)

    override def sort[B >: A](implicit c: Ordering[B]) = {
        java.util.Collections.sort(_1.asInstanceOf[java.util.List[B]], c)
        this
    }

    override def stableSort[B >: A](implicit c: Ordering[B]) = {
        java.util.Collections.sort(_1.asInstanceOf[java.util.List[B]], c)
        this
    }

    override def toJList = _1 // to-from fusion
}

private
case class ToJList[A](_1: Vector[A]) extends java.util.AbstractList[A] with java.util.RandomAccess {
    override def get(index: Int) = _1.nth(index)
    override def set(index: Int, element: A) = {
        val old = _1.nth(index)
        _1.nth(index) = element
        old
    }
    override def size() = _1.size
}


// ArrayList

@deprecated("unused")
private
object ToJArrayList {
    def apply[A](from: Vector[A]): java.util.ArrayList[A] = {
        val r = new java.util.ArrayList[A](from.size)
        from.foreach{ e => r.add(e) }
        r
    }
}


// CharSequence

private
case class FromJCharSequence(_1: java.lang.CharSequence) extends Forwarder[Char] {
    override protected val delegate: Vector[Char] = _1 match {
        case ToJCharSequence(from) => from // from-to fusion
        case _ => new _FromJCharSequence(_1)
    }
}

private class _FromJCharSequence(_1: java.lang.CharSequence) extends Vector[Char] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1.charAt(i)

    override def toJCharSequence(implicit pre: Vector[Char] <:< Vector[Char]) = _1 // to-from fusion
}

private
case class ToJCharSequence(_1: Vector[Char]) extends java.lang.CharSequence {
    override def charAt(index: Int) = _1.nth(index)
    override def length = _1.nth.size
    override def subSequence(start: Int, end: Int): java.lang.CharSequence = new ToJCharSequence(_1.nth(start, end))
    override def toString = _1.nth.stringize
}
