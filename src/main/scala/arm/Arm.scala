

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package arm


import sequence.Reactive
import sequence.reactive.{Exit, End, Thrown}


object Arm extends Common with Compatibles


/**
 * Mixin for automatic resource management
 */
trait Arm[+A] extends Reactive[A] {
    def open: A

    @pre("f is synchronous")
    override def forloop(f: A => Unit, k: Exit => Unit) {
        val r = open
        var primary: Throwable = null
        try {
            f(r)
        } catch {
            case t: Throwable => {
                primary = t
                k(Thrown(t))
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
                k(End)
                close()
            }
        }
    }

    def usedBy[B](f: A => Reactive[B]): Reactive[B] = UsedBy(this, f)
}
