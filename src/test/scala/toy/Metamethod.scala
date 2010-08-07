

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package metamethodtest


import com.github.okomok.mada

import mada.dual._
import mada.dual.nat.peano.Literal._
import nat.Peano
// import mada.dual.nat.Operator._ // crash!


class MetamethodTezt {

    type incre[n <: Peano] = n#increment

    type applyx[f[_ <: Peano], n <: Peano] = f[n]
    type twice[f[_ <: Peano] <: Peano, n <: Peano] = f[n]#increment

    trait always1[a] {
        type result[_ <: Any] = a
    }

    trait testTrivial {
        meta.assert[applyx[incre, _3]# equal [_4]]
        meta.assert[twice[incre, _3]# equal [_5]]

        type k = twice[incre, _3]
        meta.assert[k# equal [_5]]

        meta.assert[applyx[always1[_2]#result, _3]# equal [_2]]
    }

}

