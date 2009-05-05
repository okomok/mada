

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


/**
 * Provides meta list.
 */
@provider
trait Lists { this: Meta.type =>

    sealed trait List {
        type head
        type tail <: List
    }

    sealed trait nil extends List {
        override type head = error
        override type tail = nil
    }

    sealed trait cons[h, t <: List] extends List {
        override type head = h
        override type tail = t
    }

    type ::[h, t <: List] = cons[h, t]

}
