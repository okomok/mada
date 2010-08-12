

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package boolean


import ordering.{LT, GT, EQ}


private[dual]
final class NaturalOrdering extends ordering.AbstractOrdering {
    type self = NaturalOrdering

    override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = x.asInstanceOfBoolean.equal(y.asInstanceOfBoolean)
    override type equiv[x <: Any, y <: Any]                          = x#asInstanceOfBoolean#equal[y#asInstanceOfBoolean]

    override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] = _compare(x.asInstanceOfBoolean, y.asInstanceOfBoolean)
    override type compare[x <: Any, y <: Any]                            = _compare[x#asInstanceOfBoolean, y#asInstanceOfBoolean]

    private  def _compare[x <: Boolean, y <: Boolean](x: x, y: y): _compare[x, y] =
        `if`(x.not.and(y),
            const0(LT),
            `if`(x.and(y.not),
                const0(GT),
                const0(EQ)
            )
        ).apply.asInstanceOfOrderingResult.asInstanceOf[_compare[x, y]]
    private type _compare[x <: Boolean, y <: Boolean] =
        `if`[x#not#and[y],
            const0[LT],
            `if`[x#and[y#not],
                const0[GT],
                const0[EQ]
            ]
        ]#apply#asInstanceOfOrderingResult
}
