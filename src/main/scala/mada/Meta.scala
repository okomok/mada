

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import meta._


object Meta extends
    Alwayses with Asserts with Args with Binds with Booleans with Forwardings with Functions with Ints with
    IntLists with Nats with Operators with Quotes with Placeholders with Products with Tuples {


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

}
