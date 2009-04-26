

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import meta._


object Meta extends
    Asserts with Args with Binds with Booleans with Forwardings with Functions with Nats with Operators with
    Integers with Quotes with Placeholders with Products with Tuples with While {


// metamethods

    /**
     * @return  <code>a</code>.
     */
    type identity[a <: Object] = a

    /**
     * @return  <code>Nothing</code>.
     */
    type throwError = Nothing

    /**
     * Problem...
     */
    type asInstanceOf[v <: T, T <: Object] = T


// misc

    /**
     * @return  <code>null.asInstanceOf[a]</code>.
     */
    def unmeta[a <: Object]: a = null.asInstanceOf[a]


// aliases

    /**
     * Alias of <code>meta.Object</code>
     */
    type Object = meta.Object

    /**
     * Alias of <code>meta.box</code>
     */
    type box[a] = meta.box[a]

    /**
     * Alias of <code>meta.always</code>
     */
    type always[a <: Object] = meta.always[a]


// namespaces

    /**
     * @return  <code>this</code>.
     */
    val Placeholders: meta.Placeholders = this
}
