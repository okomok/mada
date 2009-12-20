

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object meta {


    @aliasOf("Nothing")
    type error = Nothing


// unmeta

    /**
     * Returns corresponding runtime value.
     */
    def unmeta[From, To](implicit _unmeta: Unmeta[From, To]): To = _unmeta()


// operators

    type +[a <: Operatable_+, b <: a#Operand_+] = a#operator_+[b]
    type -[a <: Operatable_-, b <: a#Operand_-] = a#operator_-[b]

    type ==[a <: Operatable_==, b <: a#Operand_==] = a#operator_==[b]
    type !=[a <: Operatable_==, b <: a#Operand_==] = a#operator_==[b]#not

    type &&[a <: Operatable_&&, b <: a#Operand_&&] = a#operator_&&[b]
    type ||[a <: Operatable_||, b <: a#Operand_||] = a#operator_||[b]


// assertions

    // Prefer methods to case classes:
    //   case classes often permit should-be-illegal expression.

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


// if

    /**
     * The if-expression to return Any.
     */
    type if_Any[cond <: Boolean, then <: Any, _else <: Any] = cond#if_Any[then, _else]

    /**
     * The if-expression to return Boolean.
     */
    type if_Boolean[cond <: Boolean, then <: Boolean, _else <: Boolean] = cond#if_Boolean[then, _else]

    /**
     * The if-expression to return Nat.
     */
    type if_Nat[cond <: Boolean, then <: Nat, _else <: Nat] = cond#if_Nat[then, _else]


// functional

    @aliasOf("tuple2")
    type pair[v1, v2] = tuple2[v1, v2]

    @equivalentTo("a")
    type identity[a] = a

    @equivalentTo("a")
    type project1st[a, b] = a

    @equivalentTo("b")
    type project2nd[a, b] = b

    @equivalentTo("p#_1")
    type select1st[p <: Product2] = p#_1

    @equivalentTo("p#_2")
    type select2nd[p <: Product2] = p#_2

}
