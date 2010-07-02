

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package metamethodtest


import com.github.okomok.mada

import mada.dual._
// import mada.dual.nat.Operator._ // crash!


class MetamethodTezt {

    type incre[n <: Nat] = n#increment

    type applyx[f[_ <: Nat], n <: Nat] = f[n]
    type twice[f[_ <: Nat] <: Nat, n <: Nat] = f[n]#increment

    trait always1[a] {
        type result[_ <: Any] = a
    }

    trait testTrivial {
        meta.assert[applyx[incre, _3N]# === [_4N]]
        meta.assert[twice[incre, _3N]# === [_5N]]

        type k = twice[incre, _3N]
        meta.assert[k# === [_5N]]

        meta.assert[applyx[always1[_2N]#result, _3N]# === [_2N]]
    }

}

