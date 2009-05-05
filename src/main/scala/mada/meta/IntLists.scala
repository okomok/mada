

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Provides meta int list.
 * This is considered as "pointer" in the meta world.
 */
@provider
trait IntLists { this: Meta.type =>

    object IntList {

        sealed trait nil extends IntList {
            override type head = error
            override type tail = error
        }

        sealed trait cons[h <: Int, t <: IntList] extends IntList {
            override type head = h
            override type tail = t
        }

    }

    trait IntList {
        type head <: Int
        type tail <: IntList
    }

}
