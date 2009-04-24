

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains metafunction traits.
 */
trait Functions { this: Meta.type =>

    trait Function0 extends Object {
        type Result <: Object
        type apply[void] <: Result
    }

    trait Function1 extends Object {
        type Argument1 <: Object
        type Result <: Object
        type apply[v1 <: Argument1] <: Result
    }

    trait Function2 extends Object {
        type Argument1 <: Object
        type Argument2 <: Object
        type Result <: Object
        type apply[v1 <: Argument1, v2 <: Argument2] <: Result
    }

    trait Function3 extends Object {
        type Argument1 <: Object
        type Argument2 <: Object
        type Argument3 <: Object
        type Result <: Object
        type apply[v1 <: Argument1, v2 <: Argument2, v3 <: Argument3] <: Result
    }

}
