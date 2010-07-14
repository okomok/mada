

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


trait Common {

     def empty[o <: Ordering](o: o): empty[o] = BstSet(map.empty(o))
    type empty[o <: Ordering] = BstSet[map.empty[o]]

     def single[k <: Any, o <: Ordering](k: k, o: o): single[k, o] = BstSet(map.single(k, Unit, o))
    type single[k <: Any, o <: Ordering] = BstSet[map.single[k, Unit, o]]

}
