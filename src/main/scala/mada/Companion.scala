

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Can return the companion module.
 */
trait Companion[T] {

    /**
     * Returns the companion module.
     */
    def companion: T

}
