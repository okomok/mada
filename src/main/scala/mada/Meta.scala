

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import meta._


object Meta extends
    Alwayses with Asserts with Args with Booleans with Forwardings with Functions with Ints with Nats
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
        type _0I = ^._0I
        type _1I = ^._1I
        type _2I = ^._2I
        type _3I = ^._3I
        type _4I = ^._4I
        type _5I = ^._5I
        type _6I = ^._6I
        type _7I = ^._7I
        type _8I = ^._8I
        type _9I = ^._9I
        type _10I = ^._10I
    }

}
