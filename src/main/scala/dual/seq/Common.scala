

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

    @aliasOf("iterator.Iterator")
     val Iterator = iterator.Iterator
    type Iterator = iterator.Iterator

    @aliasOf("views.View")
     val View = views.View
    type View[it <: Iterator] = views.View[it]
}
