

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package meta


trait Product {
    type productArity <: Nat
}


trait Product1 extends Product {
    override type productArity = _1N

    type _1
}

trait Product2 extends Product {
    override type productArity = _2N

    type _1
    type _2
}

trait Product3 extends Product {
    override type productArity = _3N

    type _1
    type _2
    type _3
}
