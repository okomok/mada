

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object meta {


// metamethods

    /**
     * @return  <code>a</code>.
     */
    type identity[a] = a

    /**
     * @return  <code>Nothing</code>.
     */
    type error = Nothing


// misc

    /**
     * Returns corresponding runtime value.
     */
    def unmeta[From, To](implicit _unmeta: Unmeta[From, To]): To = _unmeta()

    /**
     * @return  <code>null.asInstanceOf[a]</code>.
     */
    def instance[a]: a = null.asInstanceOf[a]


// operators

    type +[a <: Operatable_+, b <: a#Operand_+] = a#operator_+[b]
    type -[a <: Operatable_-, b <: a#Operand_-] = a#operator_-[b]

    type ==[a <: Operatable_==, b <: a#Operand_==] = a#operator_==[b]
    type !=[a <: Operatable_==, b <: a#Operand_==] = a#operator_==[b]#not

    type &&[a <: Operatable_&&, b <: a#Operand_&&] = a#operator_&&[b]
    type ||[a <: Operatable_||, b <: a#Operand_||] = a#operator_||[b]


// assertions

    // Prefer methods to case classes:
    //   case classes permit should-be-illegal expression.

    /**
     * assertion
     */
    def assert[a >: `true` <: `true`]: scala.Unit = ()

    /**
     * assertion of identity equality
     */
    def assertSame[a >: b <: b, b]: scala.Unit = ()

    /**
     * assertion if <code>a</code> is lower than <code>b</code>.
     */
    def assertLower[a <: b, b]: scala.Unit = ()

    /**
     * assertion if <code>a</code> is upper than <code>b</code>.
     */
    def assertUpper[a >: b, b]: scala.Unit = ()


    type `if`[cond <: Boolean, then, _else] = cond#_if[then, _else]
    // type _if_[cond <: Boolean, then, _else <: then#This] = cond#_if_[then, _else]
    // type ifThen[cond <: Boolean, then] = cond#ifThen[then]
    type natIf[cond <: Boolean, then <: Nat, _else <: Nat] = cond#natIf[then, _else]


// constructors for type inference

    type tuple0 = Tuple0
    type tuple1[t1] = Tuple1[t1, t1]
    type typle2[t1, t2] = Tuple2[t1, t2, t1, t2]
    type typle3[t1, t2, t3] = Tuple3[t1, t2, t3, t1, t2, t3]


// pair

    type Pair[T1, T2, v1 <: T1, v2 <: T2] = Tuple2[T1, T2, v1, v2]
    type pair[t1, t2] = Pair[t1, t2, t1, t2]


// Nat literals

    type _0N = Zero
    type _1N = Succ[_0N]
    type _2N = Succ[_1N]
    type _3N = Succ[_2N]
    type _4N = Succ[_3N]
    type _5N = Succ[_4N]
    type _6N = Succ[_5N]
    type _7N = Succ[_6N]
    type _8N = Succ[_7N]
    type _9N = Succ[_8N]
    type _10N = Succ[_9N]


// always

    /**
     * Metafunction always returning <code>a</code>
     */
    type Always[T, a <: T] = AlwaysWorkaround.Always[T, a]

    /**
     * Constructor of <code>Always</code>
     */
    type always[a] = AlwaysWorkaround.always[a]


// functions

    type Predicate1 = Function1 { type apply1[v1 <: Argument11] <: Boolean }
    type Transform[A] = Function1 { type Argument11 >: A; type apply1[v1 <: Argument11] <: A }

}
