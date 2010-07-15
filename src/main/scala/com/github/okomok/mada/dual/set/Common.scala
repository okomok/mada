

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


trait Common {

     def sorted[o <: Ordering](o: o): sorted[o] = BSTreeSet(map.sorted(o))
    type sorted[o <: Ordering] = BSTreeSet[map.sorted[o]]

}
