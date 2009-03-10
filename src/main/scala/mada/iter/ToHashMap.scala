

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object ToHashMap {
    def apply[K, V](from: Iterable[(K, V)]): scala.collection.Map[K, V] = {
        val to = new scala.collection.jcl.HashMap[K, V]
        for (e <- from.projection) {
            to.put(e._1, e._2)
        }
        to
    }
}
