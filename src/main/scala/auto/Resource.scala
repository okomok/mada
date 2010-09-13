

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


/**
 * Mixin for automatic resource management.
 */
trait Resource[+A] extends Auto[A] {
    protected def get: A
    protected def begin: Unit = ()
    protected def end: Unit

    final override def foreach(f: A => Unit) = {
        begin
        var primary: Throwable = null
        try {
            f(get)
        } catch {
            case t: Throwable => {
                primary = t
                throw t
            }
        } finally {
            if (null ne primary) {
                try {
                    end
                } catch {
                    case s: Exception => /*primary.addSuppressedException(s)*/
                }
            } else {
                end
            }
        }
    }
}
