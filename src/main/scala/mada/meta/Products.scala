

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta products.
 */
trait Products { this: Meta.type =>

    trait Product extends Object {
        type productArity <: Nat
    }

    trait Product0 extends Product {
        override type productArity = _0N
    }

    trait Product1 extends Product {
        type _T1 <: Object

        override type productArity = _1N

        type _1 <: _T1
    }

    trait Product2 extends Product {
        type _T1 <: Object
        type _T2 <: Object

        override type productArity = _2N

        type _1 <: _T1
        type _2 <: _T2
    }

    trait Product3 extends Product {
        type _T1 <: Object
        type _T2 <: Object
        type _T3 <: Object

        override type productArity = _3N

        type _1 <: _T1
        type _2 <: _T2
        type _3 <: _T3
    }

}
