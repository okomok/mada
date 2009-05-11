

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


trait Function0 {
    type Result0
    type apply0
}

trait Function1 {
    type Argument11
    type Result1

    type apply1[v1 <: Argument11] <: Result1
}

trait Function2 {
    type Argument21
    type Argument22
    type Result2

    type apply2[v1 <: Argument21, v2 <: Argument22] <: Result2
}

trait Function3 {
    type Argument31
    type Argument32
    type Argument33
    type Result3

    type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] <: Result3
}
