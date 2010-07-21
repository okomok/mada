

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


trait Common {

    /**
     * Constructs an empty sorted map.
     */
     def sorted[o <: Ordering](o: o): sorted[o] = bstree.Nil(o)
    type sorted[o <: Ordering] = bstree.Nil[o]

}
