

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on associative container.
 */
package object assoc {

    /**
     * Puts only if <code>key</code> is not contained, then gets.
     */
    def lazyGet[K, V](m: java.util.concurrent.ConcurrentMap[K, () => V])(key: K)(value: => V): V = {
        // See: Java Concurrency in Practice - Listing 5.19
        option.fromRef(m.get(key)).getOrElse{
            val v = function.ofLazy(value)
            option.fromRef(m.putIfAbsent(key, v)).getOrElse{
                v
            }
        }.apply()
    }

}
