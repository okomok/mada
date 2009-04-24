

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Turns metamethod into metafunction.
 */
trait Quotes { this: Meta.type =>

    final class quote0[f[Void] <: Object] extends Function0 {
        override type isBind = `false`
        override type apply0[Void] = f[Void]
    }

    final class quote1[f[_ <: Object] <: Object] extends Function1 {
        override type isBind = `false`
        override type apply1[a1 <: Object] = f[a1]
    }

    final class quote2[f[_ <: Object, _ <: Object] <: Object] extends Function2 {
        override type isBind = `false`
        override type apply2[a1 <: Object, a2 <: Object] = f[a1, a2]
    }

    final class quote3[f[_ <: Object, _ <: Object, _ <: Object] <: Object] extends Function3 {
        override type isBind = `false`
        override type apply3[a1 <: Object, a2 <: Object, a3 <: Object] = f[a1, a2, a3]
    }

}
