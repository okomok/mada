

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta products.
 */
@provider
trait Products { this: Meta.type =>

    trait Product {
        type productArity <: Nat
    }

    trait Product0 extends Product {
        override type productArity = Meta._0
    }

    trait Product1 extends Product {
        type _T1

        override type productArity = Meta._1

        type _1 <: _T1
    }

    trait Product2 extends Product {
        type _T1
        type _T2

        override type productArity = Meta._2

        type _1 <: _T1
        type _2 <: _T2
    }

    trait Product3 extends Product {
        type _T1
        type _T2
        type _T3

        override type productArity = Meta._3

        type _1 <: _T1
        type _2 <: _T2
        type _3 <: _T3
    }

}
