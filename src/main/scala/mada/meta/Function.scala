

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains metafunction traits. (corresponding to MetafunctionClass of Boost)
 */
trait Functions { this: Meta.type =>

    trait Function0 extends Object {
        type apply0[Void] <: Object
    }

    trait Function1 extends Object {
        type apply1[a1 <: Object] <: Object
    }

    trait Function2 extends Object {
        type apply2[a1 <: Object, a2 <: Object] <: Object
    }

    trait Function3 extends Object {
        type apply3[a1 <: Object, a2 <: Object, a3 <: Object] <: Object
    }

    /**
     * Takes variable arguments.
     */
    trait FunctionV extends Function0 with Function1 with Function2 with Function3

}
