

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on maps.
 */
object Sets {
    @aliasOf("scala.collection.mutable.Set")
    type Mutable[A] = scala.collection.mutable.Set[A]

    @aliasOf("scala.collection.immutable.Set")
    type Immutable[A] = scala.collection.immutable.Set[A]
}
