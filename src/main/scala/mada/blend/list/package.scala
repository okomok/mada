

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


package object list {

    /**
     * Creates a list from <code>sequence.List</code>.
     */
    def typed[l <: List](from: sequence.List[Any])(implicit _typed: Typed[l]) = _typed(from)

}
