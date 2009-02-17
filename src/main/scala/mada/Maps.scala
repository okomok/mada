

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on maps.
 */
object Maps {
    /**
     * Puts only if <code>key</code> is not contained, then gets.
     */
    def lazyGet[K, V](map: scala.collection.mutable.Map[K, V])(key: K)(value: => V): V = {
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
