

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import meta._


object Meta extends
    Asserts with Args with Binds with Bools with Funcs with Quotes with Placeholders {


// metamethods

    /**
     * @return  <code>a</code>.
     */
    type Identity[a <: Obj] = a

    /*
    type ByLazy1[f[_], a] = ByLazy1Impl[f, a]
    trait ByLazy1Impl[f[_], a] extends Method0 {
        override type Type = f[a]
    }
    */

    /**
     * Tests type equality. (probably infeasible.)
     */
    type Equals[a <: Obj, b <: Obj] = Nothing // How?


// the value

    /**
     * @return  <code>null.asInstanceOf[a]</code>.
     */
    def value[a <: Obj]: a = null.asInstanceOf[a]


// aliases

    /**
     * Alias of <code>meta.Obj</code>
     */
    type Obj = meta.Obj

    /**
     * Alias of <code>meta.Boxed</code>
     */
    type Boxed[a] = meta.Boxed[a]

    /**
     * Alias of <code>meta.Always</code>
     */
    type Always[a <: Obj] = meta.Always[a]

    /**
     * Alias of <code>meta.Void</code>
     */
    type Void = meta.Void


// namespaces

    /**
     * @return  <code>this</code>.
     */
    val Placeholders: meta.Placeholders = this
}
