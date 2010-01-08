

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend


// Doesn't work well.

/**
 * Polymorphic function
 */
trait Function0 {
    type apply
    def apply: apply
}

trait Function1 { Self =>
    private val self = this.asInstanceOf[self]
    private[mada] type self <: Function1

    type Arg1
    type Result
    type apply[v1 <: Arg1] <: Result
    def apply[v1 <: Arg1](v1: v1): apply[v1]
}

trait Function2 {
    type Arg1; type Arg2
    type apply[V1 <: Arg1, V2 <: Arg2]
    def apply[V1 <: Arg1, V2 <: Arg2](v1: V1, v2: V2): apply[V1, V2]
}

trait Function3 {
    type Arg1; type Arg2; type Arg3
    type apply[V1 <: Arg1, V2 <: Arg2, V3 <: Arg3]
    def apply[V1 <: Arg1, V2 <: Arg2, V3 <: Arg3](v1: V1, v2: V2, v3: V3): apply[V1, V2, V3]
}

/*
case class Compose1[f <: Function1 { type Arg1 <: g#Result }, g <: Function1 { type Result <: f#Arg1 }](f: f, g: g) extends Function1 {
    override type Arg1 = g#Arg1
    override type Result = f#Result
    override type apply[v1 <: Arg1] = f#apply[g#apply[v1]]
    override def apply[v1 <: Arg1](v1: v1): apply[v1] = throw new Error//f.apply(g.apply(v1))
}
*/
