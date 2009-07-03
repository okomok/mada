

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object option {

    /**
     * Typed <code>None</code>
     */
    def NoneOf[A]: Option[A] = None

    @equivalentTo("if (e eq null) None else Some(e)")
    def fromRef[A <: AnyRef](e: A): Option[A] = if (e eq null) None else Some(e)

}
