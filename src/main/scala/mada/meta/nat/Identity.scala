

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta.nat


sealed trait Identity {
    type increment <: Identity
    type equals[that <: Identity] <: Boolean

    private[mada] type is0 <: Boolean
    private[mada] type is1 <: Boolean
    private[mada] type is2 <: Boolean
    private[mada] type is3 <: Boolean
    private[mada] type is4 <: Boolean
    private[mada] type is5 <: Boolean
    private[mada] type is6 <: Boolean
    private[mada] type is7 <: Boolean
    private[mada] type is8 <: Boolean
    private[mada] type is9 <: Boolean
    private[mada] type is10 <: Boolean
}


sealed trait _0I extends Identity {
    override type increment = _1I
    override type equals[that <: Identity] = that#is0

    private[mada] override type is0 = `true`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
}

sealed trait _1I extends Identity {
    override type increment = _2I
    override type equals[that <: Identity] = that#is1

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `true`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
}

sealed trait _2I extends Identity {
    override type increment = _3I
    override type equals[that <: Identity] = that#is2

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `true`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
}

sealed trait _3I extends Identity {
    override type increment = _4I
    override type equals[that <: Identity] = that#is3

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `true`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
}

sealed trait _4I extends Identity {
    override type increment = _5I
    override type equals[that <: Identity] = that#is4

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `true`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
}

sealed trait _5I extends Identity {
    override type increment = _6I
    override type equals[that <: Identity] = that#is5

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `true`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
}

sealed trait _6I extends Identity {
    override type increment = _7I
    override type equals[that <: Identity] = that#is6

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `true`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
}

sealed trait _7I extends Identity {
    override type increment = _8I
    override type equals[that <: Identity] = that#is7

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `true`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
 }

sealed trait _8I extends Identity {
    override type increment = _9I
    override type equals[that <: Identity] = that#is8

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `true`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `false`
}

sealed trait _9I extends Identity {
    override type increment = _10I
    override type equals[that <: Identity] = that#is9

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `true`
    private[mada] override type is10 = `false`
}

sealed trait _10I extends Identity {
    override type increment = error
    override type equals[that <: Identity] = that#is10

    private[mada] override type is0 = `false`
    private[mada] override type is1 = `false`
    private[mada] override type is2 = `false`
    private[mada] override type is3 = `false`
    private[mada] override type is4 = `false`
    private[mada] override type is5 = `false`
    private[mada] override type is6 = `false`
    private[mada] override type is7 = `false`
    private[mada] override type is8 = `false`
    private[mada] override type is9 = `false`
    private[mada] override type is10 = `true`
}
