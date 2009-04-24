

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


/**
 * Boxes <code>a</code> into <code>Object</code>.
 */
final class boxed[a] extends Object {
    override type isBind = `false`
    override type IsBoxed = `true`
    type unbox = a
}
