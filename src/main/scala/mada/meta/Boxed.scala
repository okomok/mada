

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Boxes <code>a</code> into <code>Obj</code>.
 */
trait Boxed[a] extends Obj {
    override type IsBind = Meta.False
    override type IsBoxed = Meta.True
    type Unbox = a
}
