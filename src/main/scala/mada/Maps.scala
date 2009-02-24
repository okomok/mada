

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on maps.
 */
object Maps {
    /**
     * Alias of <code>scala.collection.mutable.Map</code>
     */
    type Mutable[K, V] = scala.collection.mutable.Map[K, V]

    /**
     * Alias of <code>scala.collection.immutable.Map</code>
     */
    type Immutable[K, V] = scala.collection.immutable.Map[K, V]

    /**
     * Puts only if <code>key</code> is not contained, then gets.
     */
    def lazyGet[K, V](map: Mutable[K, V])(key: K)(value: => V): V = {
        val ov = map.get(key)
        if (ov.isEmpty) {
            val v = value
            map.put(key, v)
            v
        } else {
            ov.get
        }
    }
}
