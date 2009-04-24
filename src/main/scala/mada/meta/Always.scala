

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


/**
 * Metafunction always returning <code>a</code>
 */
trait Alwayses { this: Meta.type =>

    final class always0[a <: Object] extends Function0 {
        override type isBind = `false`

        override type Result = a
        override type apply[void] = a
    }

    final class always1[a <: Object] extends Function1 {
        override type isBind = `false`

        override type Result = a
        override type apply[b1 <: Object] = a
    }

    final class always2[a <: Object] extends Function2 {
        override type isBind = `false`

        override type Result = a
        override type apply[b1 <: Object, b2 <: Object] = a
    }

    final class always3[a <: Object] extends Function3 {
        override type isBind = `false`

        override type Result = a
        override type apply[b1 <: Object, b2 <: Object, b3 <: Object] = a
    }

}
