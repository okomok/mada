

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Using[+A](_1: Reactive[A], _2: java.io.Closeable) extends Forwarder[A] {
    override protected val delegate = {
        _1 catching {
            case t => {
                try {
                    _2.close()
                } catch {
                    case s: Exception => /*t.addSuppressedException(s)*/
                } finally {
                    throw t
                }
            }
        } onExit { _ =>
            _2.close()
        }
    }
}
