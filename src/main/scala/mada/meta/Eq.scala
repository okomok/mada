

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Identity equality
 */
@provider
trait Eq { this: Meta.type =>

    type eq[A , B] = `false`

}
