

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import meta._


object Meta extends
    Asserts with Args with Binds with Booleans with Functions with Quotes with Placeholders {


// metamethods

    /**
     * @return  <code>a</code>.
     */
    type identity[a <: Object] = a

    /*
    type ByLazy1[f[_], a] = ByLazy1Impl[f, a]
    trait ByLazy1Impl[f[_], a] extends Method0 {
        override type Type = f[a]
    }
    */

    /**
     * Tests type equality. (probably infeasible.)
     */
    type equals[a <: Object, b <: Object] = Nothing // How?


// the instance

    /**
     * @return  <code>null.asInstanceOf[a]</code>.
     */
    def instance[a <: Object]: a = null.asInstanceOf[a]


// aliases

    /**
     * Alias of <code>meta.Object</code>
     */
    type Object = meta.Object

    /**
     * Alias of <code>meta.Void</code>
     */
    type Void = meta.Void

    /**
     * Alias of <code>meta.boxed</code>
     */
    type boxed[a] = meta.boxed[a]

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
