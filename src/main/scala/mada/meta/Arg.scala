

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * Returns the n-th argument.
 */
sealed abstract class arg1[T] extends Function1 with Function2 with Function3 {
    override type Argument11 = T
    override type Result1 = T
    override type apply1[v1 <: Argument11] = v1

    override type Argument21 = T
    override type Argument22 = Object
    override type Result2 = T
    override type apply2[v1 <: Argument21, v2 <: Argument22] = v1

    override type Argument31 = T
    override type Argument32 = Object
    override type Argument33 = Object
    override type Result3 = T
    override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = v1
}

sealed abstract class arg2[T] extends Function2 with Function3 {
    override type Argument21 = Object
    override type Argument22 = T
    override type Result2 = T
    override type apply2[v1 <: Argument21, v2 <: Argument22] = v2

    override type Argument31 = Object
    override type Argument32 = T
    override type Argument33 = Object
    override type Result3 = T
    override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = v2
}

sealed abstract class arg3[T] extends Function3 {
    override type Argument31 = Object
    override type Argument32 = Object
    override type Argument33 = T
    override type Result3 = T
    override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = v3
}
