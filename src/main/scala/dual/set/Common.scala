

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


trait Common {

    /**
     * Constructs an empty sorted set.
     */
     def sorted[o <: Ordering](o: o): sorted[o] = BSTree(map.bstree.Nil(o))
    type sorted[o <: Ordering] = BSTree[map.bstree.Nil[o]]

}
