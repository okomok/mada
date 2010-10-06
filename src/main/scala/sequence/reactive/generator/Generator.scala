

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


object Generator extends Common


/**
 * Generates an element on demand.
 */
trait Generator[+A] {
    /**
     * Returns true iif no more elments generated.
     * `false` by default.
     */
    def isEnd: Boolean = false

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

    /**
     * Retrieves a snapshot.
     */
    def sequence: Reactive[A]
}
