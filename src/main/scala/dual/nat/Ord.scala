

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


import ordering.{LT, GT, EQ}


private[mada] final class Ord extends ordering.AbstractOrdering {
    type self = Ord

    override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] =
        `if`(x.asInstanceOfNat  < y.asInstanceOfNat,
            Const0(LT),
            `if`(x.asInstanceOfNat  > y.asInstanceOfNat,
                Const0(GT),
                Const0(EQ)
            )
        ).apply.asInstanceOfOrderingResult.asInstanceOf[compare[x, y]]
    override type compare[x <: Any, y <: Any] =
        `if`[x#asInstanceOfNat# <[y#asInstanceOfNat],
            Const0[LT],
            `if`[x#asInstanceOfNat# >[y#asInstanceOfNat],
                Const0[GT],
                Const0[EQ]
            ]
        ]#apply#asInstanceOfOrderingResult
}
