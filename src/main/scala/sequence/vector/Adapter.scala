

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


import scala.annotation.unchecked.uncheckedVariance


/**
 * Adapts underlying vector.
 */
trait Adapter[+From, +To] extends Vector[To] {
    /**
     * Underlying vector, overridden in subclasses.
     */
    protected def underlying: Vector[From]

    @annotation.equivalentTo("underlying.start")
    override def start = underlying.start

    @annotation.equivalentTo("underlying.end")
    override def end = underlying.end

    @annotation.equivalentTo("underlying(i).asInstanceOf[To]")
    override def apply(i: Int): To = underlying(i).asInstanceOf[To]

    @annotation.equivalentTo("underlying(i) = e.asInstanceOf[From]")
    override def update(i: Int, e: To @uncheckedVariance): Unit = underlying(i) = e.asInstanceOf[From]

    @annotation.equivalentTo("underlying.isDefinedAt(i)")
    override def isDefinedAt(i: Int): Boolean = underlying.isDefinedAt(i)
}
