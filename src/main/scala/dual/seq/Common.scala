

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


trait Common {
    /**
     * Makes a Seq Equiv from element Equiv.
     */
     def eqv[ee <: Equiv](ee: ee): eqv[ee] = new Eqv(ee)
    type eqv[ee <: Equiv] = Eqv[ee]

    @equivalentTo("new Empty{}")
     val Empty = _Empty.value
}
