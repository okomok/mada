

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._


// `object Helper` would fall in weird compiler error.
// Also, don't import multiple times.
class Helper {
    type natOrd = ordering.ofNatPeano
    val natOrd: natOrd = ordering.ofNatPeano

    type node[k <: nat.Peano, l <: map.Map, r <: map.Map] =
        map.Node[l#size# +[r#size]#increment, k, k, l, r, natOrd]
    def node[k <: nat.Peano, l <: map.Map, r <: map.Map](k: k, l: l, r: r): node[k, l, r] =
        map.Node((l.size + r.size).increment, k, k, l, r, natOrd).asInstanceOf[node[k, l, r]]

    type nil = map.Nil[natOrd]
    val nil: nil = map.Nil(natOrd)

    type leaf[k <: nat.Peano] = map.single[k, k, natOrd]
    def leaf[k <: nat.Peano](k: k) = map.single(k, k, natOrd)
}
