

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains placeholders.
 */
@provider
trait Placeholders { this: Meta.type =>
    @returnthis val Placeholders: Placeholders = this
    type _a1[T] = arg1[T]
    type _a2[T] = arg2[T]
    type _a3[T] = arg3[T]

}
