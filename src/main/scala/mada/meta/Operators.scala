

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


/**
 * Contains "overloadable" operators.
 */
trait Operators { this: Meta.type =>

    // See: SLS 5.3.3 Comparable.
    // Just remove type parameters.

    trait Operatable_+ {
        type Self <: Operatable_+
        type operate_+[that <: Self] <: Self
    }

    type +[a <: Operatable_+, b <: a#Self] = a#operate_+[b]

}
