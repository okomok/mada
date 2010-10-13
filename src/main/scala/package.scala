

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok


package object mada {

    @aliasOf("arm.Arm")
     val Arm = arm.Arm
    type Arm[+A] = arm.Arm[A]

    @aliasOf("peg.Peg")
     val Peg = peg.Peg
    type Peg[A] = peg.Peg[A]

    @aliasOf("stack.Stack")
     val Stack = stack.Stack
    type Stack[A] = stack.Stack[A]

}
