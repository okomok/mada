

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * The root of the "meta class" hierarchy
 */
trait Obj {
    type IsBind <: Meta.Bool
    type IsBoxed <: Meta.Bool
}
