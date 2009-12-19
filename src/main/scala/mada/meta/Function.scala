

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


trait Function0 {
    type Result
    type apply
}

trait Function1 {
    type Argument1
    type Result

    type apply[v1 <: Argument1] <: Result
}

trait Function2 {
    type Argument1
    type Argument2
    type Result

    type apply[v1 <: Argument1, v2 <: Argument2] <: Result
}

trait Function3 {
    type Argument1
    type Argument2
    type Argument3
    type Result

    type apply[v1 <: Argument1, v2 <: Argument2, v3 <: Argument3] <: Result
}
