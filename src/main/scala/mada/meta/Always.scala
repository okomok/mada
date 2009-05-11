

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


@compilerWorkaround
object AlwaysWorkaround { // works around some case-insensitive bug in compilation.

    /**
     * Metafunction always returning <code>a</code>
     */
    sealed trait Always[T, a <: T] extends Function0 with Function1 with Function2 with Function3 {
        override type Result0 = T
        override type apply0 = a

        override type Argument11 = Object
        override type Result1 = T
        override type apply1[v1 <: Argument11] = a

        override type Argument21 = Object
        override type Argument22 = Object
        override type Result2 = T
        override type apply2[v1 <: Argument21, v2 <: Argument22] = a

        override type Argument31 = Object
        override type Argument32 = Object
        override type Argument33 = Object
        override type Result3 = T
        override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = a
    }

    /**
     * Constructor of <code>Always</code>
     */
    sealed trait always[a] extends Always[a, a]

}
