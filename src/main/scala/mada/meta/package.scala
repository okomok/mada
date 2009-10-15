

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object meta {


// metamethods

    @equivalentTo("a")
    type identity[a] = a

    @equivalentTo("Nothing")
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


// Functions short-cut

    type Func0[R] = Function0 {
        type Result0 = R
    }

    type Func1[T1, R] = Function1 {
        type Argument11 = T1
        type Result1 = R
       // type apply1[v1 <: T1] <: R
    }

    type Func2[T1, T2, R] = Function2 {
        type Argument21 = T1
        type Argument22 = T2
        type Result2 = R
    }

    type Func3[T1, T2, T3, R] = Function3 {
        type Argument31 = T1
        type Argument32 = T2
        type Argument33 = T3
        type Result3 = R
    }

    type Predicate1 = Function1 {
        type Result1 = Boolean
    }

    type Transform[A] = Function1 {
        type Argument11 >: A
        type Result1 <: A
    }

}
