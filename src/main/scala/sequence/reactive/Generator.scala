

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * A sequence which generates an element on demand.
 */
trait Generator[+A] extends Reactive[A] {
    def generateOne: Unit
    def generate(n: Int = 1) = for (_ <- 0 until n) generateOne
    def generateAll: Unit = throw new UnsupportedOperationException("Generator.generateAll")
}


/**
 * Mixin to implement a trivial Generator
 */
trait TrivialGenerator[A] extends Generator[A] {
    private var _out: A => Unit = null
    protected def out(x: A): Unit = if (_out != null) _out(x)
    final override def foreach(f: A => Unit) = _out = f
}
