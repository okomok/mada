

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


sealed trait Option {

    /**
     * True if the option is the <code>None</code> value, false otherwise.
     */
    type isEmpty <: Boolean

    @aliasOf("isEmpty#not")
    final type isDefined = isEmpty#not

    /**
     * Returns the metavalue of this option.
     */
    type get

}


sealed trait none extends Option {
    override type isEmpty = `true`
    override type get = `null`
}

sealed trait some[x] extends Option {
    override type isEmpty = `false`
    override type get = x
}
