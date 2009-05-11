

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.auto


/**
 * Can be used instead of implicit objects.
 */
trait Interface {
    def begin: Unit = ()
    def end: Unit = ()
}
