

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import meta._


object Meta extends
    Asserts with Args with Binds with Booleans with Forwards with Functions with Operators with
    Integers with Quotes with Placeholders {


// metamethods (a.k.a type-constructor)

    /**
     * @return  <code>a</code>.
     */
    type identity[a <: Object] = a

    /**
     * @return  <code>Nothing</code>.
     */
    type throwError = Nothing

    /**
     * May be useful if you forget everything is type. :-)
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
     * Alias of <code>meta.void</code>
     */
    type void = meta.void

    /**
     * Alias of <code>meta.newObject</code>
     */
    type newObject[a] = meta.newObject[a]

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
