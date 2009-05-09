

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import meta._


object Meta extends
    Alwayses with Asserts with Args with Booleans with Forwardings with Functions with Nats
    with Operators with Quotes with Products with Tuples { ^ =>


// metamethods

    /**
     * @return  <code>a</code>.
     */
    type identity[a] = a

    /**
     * @return  <code>Nothing</code>.
     */
    type error = Nothing


// misc

    /**
     * @return  <code>null.asInstanceOf[a]</code>.
     */
    def nullOf[a]: a = null.asInstanceOf[a]

    /**
     * Returns corresponding runtime value.
     */
    def unmeta[From, To](implicit _unmeta: Unmeta[From, To]): To = _unmeta()

    @alias type Unmeta[From, To] = meta.Unmeta[From, To]

    /**
     * Contains aliases of meta Boolean literals.
     */
    object BooleanLiterals {
        type `true` = ^.`true`
        type `false` = ^.`false`
    }

    /**
     * Contains aliases of meta Int literals.
     */
    object IntLiterals {
        type _0 = ^._0
        type _1 = ^._1
        type _2 = ^._2
        type _3 = ^._3
        type _4 = ^._4
        type _5 = ^._5
        type _6 = ^._6
        type _7 = ^._7
        type _8 = ^._8
        type _9 = ^._9
        type _10 = ^._10
    }

}
