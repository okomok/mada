

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package arm


object Arm extends Common with Compatibles


/**
 * Mixin for automatic resource management
 */
trait Arm[+A] extends sequence.Reactive[A] {
    def open: A

    @pre("f is synchronous")
    override def forloop(f: A => Unit, k: => Unit) {
        val r = open
        var primary: Throwable = null
        try {
            f(r)
        } catch {
            case t: Throwable => {
                primary = t
                throw t
            }
        } finally {
            if (primary != null) {
                try {
                    close()
                } catch {
                    case s: Exception => /*primary.addSuppressedException(s)*/
                }
            } else {
                close()
            }
        }
        k
    }
}
