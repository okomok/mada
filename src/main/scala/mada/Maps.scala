

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on maps.
 */
object Maps {
    @aliasOf("scala.collection.mutable.Map")
    type Mutable[K, V] = scala.collection.mutable.Map[K, V]

    @aliasOf("scala.collection.immutable.Map")
    type Immutable[K, V] = scala.collection.immutable.Map[K, V]

    @aliasOf("java.util.concurrent.ConcurrentMap")
    type Concurrent[K, V] = java.util.concurrent.ConcurrentMap[K, V]

    /**
     * Puts only if <code>key</code> is not contained, then gets.
     */
    def lazyGet[K, V](map: Concurrent[K, () => V])(key: K)(value: => V): V = {
        // See: Java Concurrency in Practice - Listing 5.19
        Java.toOption(map.get(key)).getOrElse{
            val v = Functions.byLazy(value)
            Java.toOption(map.putIfAbsent(key, v)).getOrElse{
                v
            }
        }.apply()
    }
}
