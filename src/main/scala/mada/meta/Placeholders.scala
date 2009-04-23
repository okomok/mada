

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains placeholders.
 */
trait Placeholders { this: Meta.type =>
    type _1 = Arg1
    type _2 = Arg2
    type _3 = Arg3
}
