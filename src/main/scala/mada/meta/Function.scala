

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


// not work well.

trait Function0 {
    type apply
}

trait Function1 {
    type Arg1
    type apply[v1 <: Arg1]
}

trait Function2 {
    type Arg1
    type Arg2
    type apply[v1 <: Arg1, v2 <: Arg2]
}

trait Function3 {
    type Arg1
    type Arg2
    type Arg3
    type apply[v1 <: Arg1, v2 <: Arg2, v3 <: Arg3]
}
