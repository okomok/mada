

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains metafunction traits.
 */
trait Functions { this: Meta.type =>

    trait Function0 extends Object {
        type Result0 <: Object

        type apply0 <: Result0
    }

    trait Function1 extends Object {
        type Argument11 <: Object
        type Result1 <: Object

        type apply1[v1 <: Argument11] <: Result1
    }

    trait Function2 extends Object {
        type Argument21 <: Object
        type Argument22 <: Object
        type Result2 <: Object

        type apply2[v1 <: Argument21, v2 <: Argument22] <: Result2
    }

    trait Function3 extends Object {
        type Argument31 <: Object
        type Argument32 <: Object
        type Argument33 <: Object
        type Result3 <: Object

        type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] <: Result3
    }

    sealed trait foofoo[f <: Function0 { type Result0 <: Integer }]

    type Predicate1[v <: Object] = Function1 { type Argument11 <: v; type Result1 <: Boolean }
    type Transform[v <: Object] = Function1 { type Argument11 <: v; type Result1 <: v }

}
