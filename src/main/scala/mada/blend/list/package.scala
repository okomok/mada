

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


package object list {

    /**
     * Creates meta List from <code>scala.List</code>.
     */
    def typed[l <: List](from: scala.List[Any])(implicit _typed: Typed[l]) = _typed(from)

}
