

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Meta {

    sealed trait Bool {
        type And[B <: Bool] <: Bool
        type Or[B <: Bool] <: Bool
        type Not <: Bool
        type If[IfTrue, IfFalse]
    }

    final class True extends Bool {
        type And[B <: Bool] = B
        type Or[B <: Bool] = True
        type Not = False
        type If[IfTrue, IfFalse] = IfTrue
    }

    val True = new True

    final class False extends Bool {
        type And[B <: Bool] = False
        type Or[B <: Bool] = B
        type Not = True
        type If[IfTrue, IfFalse] = IfFalse
    }

    val False = new False


    type &&[B1 <: Bool, B2 <: Bool] = B1#And[B2]
    type ||[B1 <: Bool, B2 <: Bool] = B1#Or[B2]

//    implicit val falseToBoolean = TypeToValue[False, Boolean](false)
//    implicit val trueToBoolean = TypeToValue[True, Boolean](true)

}
