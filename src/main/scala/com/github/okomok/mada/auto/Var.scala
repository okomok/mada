

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


/**
 * Variable Auto. (Filter is enough?)
 */
class Var[A](private var a: Auto[A]) extends Auto[A] {
    def this() = this(null)
    override def get = a.get
    override def begin = if (a ne null) a.begin
    override def end = if (a ne null) a.end
    def underlying: Auto[A] = a
    def release: Auto[A] = { val tmp = a; a = null; tmp }
    def reset(b: Auto[A]): Unit = { if ((a ne b) && (a ne null)) a.end; a = b }
}


private[mada] case class AsVar[A](_1: Auto[A]) extends Auto[Var[A]] {
    override val get = new Var(_1)
    override def begin = get.begin
    override def end = get.end
}
