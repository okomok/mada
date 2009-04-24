

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains metafunctions to return the n-th argument.
 */
trait Args { this: Meta.type =>
/*
    final class arg1[T <: Object] extends Function1[T, T] with Function2[T, Object, T] with Function3[T, Object, Object, T] {
        override type isBind = `true`

        override type apply1[a1 <: T] = a1
        override type apply2[a1 <: T, a2 <: Object] = a1
        override type apply3[a1 <: T, a2 <: Object, a3 <: Object] = a1
    }

    final class arg2[T <: Object] extends Function2[Object, T, T] with Function3[Object, T, Object, T] {
        override type isBind = `true`

        override type apply2[a1 <: Object, a2 <: T] = a2
        override type apply3[a1 <: Object, a2 <: T, a3 <: Object] = a2
    }

    final class arg3[T <: Object] extends Function3[Object, Object, T, T] {
        override type isBind = `true`

        override type apply3[a1 <: Object, a2 <: Object, a3 <: T] = a3
    }
*/
}
