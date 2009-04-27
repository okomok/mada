

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains metafunction traits.
 */
trait Functions { this: Meta.type =>

    trait Function0 extends Object {
        type apply0 <: Object
    }

    trait Function1 extends Object {
        type Argument11 <: Object

        type apply1[v1 <: Argument11] <: Object
    }

    trait Function2 extends Object {
        type Argument21 <: Object; type Argument22 <: Object

        type apply2[v1 <: Argument21, v2 <: Argument22] <: Object
    }

    trait Function3 extends Object {
        type Argument31 <: Object; type Argument32 <: Object; type Argument33 <: Object

        type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] <: Object
    }

    type Predicate1 = Function1 { type apply1[v1 <: Argument11] <: Boolean }
    type Transform = Function1 { type apply1[v1 <: Argument11] <: Argument11 }

}
