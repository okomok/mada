

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package auto


object Auto extends Common with Compatibles


/**
 * Mixin for automatic resource management
 */
trait Auto[+A] extends sequence.Reactive[A] {
    def open: A

    override def foreach(f: A => Unit) = {
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
            if (null ne primary) {
                try {
                    close
                } catch {
                    case s: Exception => /*primary.addSuppressedException(s)*/
                }
            } else {
                close
            }
        }
    }
}
