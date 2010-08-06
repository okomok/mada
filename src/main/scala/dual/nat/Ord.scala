

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


import ordering.{LT, GT, EQ}


private[dual]
final class Ord extends ordering.AbstractOrdering {
    type self = Ord

    override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] =
        `if`(x.asInstanceOfNat  < y.asInstanceOfNat,
            const0(LT),
            `if`(x.asInstanceOfNat  > y.asInstanceOfNat,
                const0(GT),
                const0(EQ)
            )
        ).apply.asInstanceOfOrderingResult.asInstanceOf[compare[x, y]]
    override type compare[x <: Any, y <: Any] =
        `if`[x#asInstanceOfNat# <[y#asInstanceOfNat],
            const0[LT],
            `if`[x#asInstanceOfNat# >[y#asInstanceOfNat],
                const0[GT],
                const0[EQ]
            ]
        ]#apply#asInstanceOfOrderingResult
}
