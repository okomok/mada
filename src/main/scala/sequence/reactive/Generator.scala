

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * A sequence which generates an element on demand.
 */
trait Generator[+A] extends ReactiveOnce[A] {
    /**
     * Generates one element.
     */
    def generate: Unit

    @equivalentTo("for (_ <- 0 until n) generate")
    def generateN(n: Int) = for (_ <- 0 until n) generate

    /**
     * Generates all the elements.
     */
    def generateAll: Unit = throw new UnsupportedOperationException("Generator.generateAll")
}


/**
 * Mixin for a Generator which doesn't allow re-foreach.
 */
trait GeneratorOnce[A] extends Generator[A] with ReactiveOnce[A] {
    @volatile private var _out: A => Unit = null
    protected def out(x: A): Unit = if (_out != null) _out(x)
    final override def foreachOnce(f: A => Unit) = _out = f
}
