

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains placeholders.
 */
trait Placeholders { this: Meta.type =>
    type _1 = arg1
    type _2 = arg2
    type _3 = arg3
}
