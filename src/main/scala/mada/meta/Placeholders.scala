

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains placeholders.
 */
trait Placeholders { this: Meta.type =>

    type _a1[T <: Object] = arg1[T]
    type _a2[T <: Object] = arg2[T]
    type _a3[T <: Object] = arg3[T]

}
