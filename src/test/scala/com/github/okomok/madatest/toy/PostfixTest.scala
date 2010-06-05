

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package postfix


import junit.framework.Assert._


// See: Techniques for Embedding Postfix Languages in Haskell
//      at http://www.eecs.usma.edu/webs/people/okasaki/pubs.html

// Hmm, scala's eta-expansion is too weak.


object Stack {
    object Empty extends Empty
    class Empty

    class Rev[L](l: L) {
        def --[R](f : L => R): R = f(l)
    }
    implicit def toRev[L](l: L): Rev[L] = new Rev[L](l)

//    def push[S, X](x: X)(s: S): (S, X) = (s, x) // is equivalent to...
    def push[S, X]: X => S => (S, X) = (x: X) => (s: S) => (s, x)
    def pop[S, X](sx: (S, X)): S = sx._1
    def dup[S, X](sx: (S, X)): ((S, X), X) = ((sx._1, sx._2), sx._2)
    def exch[S, X, Y](sxy: ((S, X), Y)): ((S, Y), X) = ((sxy._1._1, sxy._2), sxy._1._2)

    def smap[S, X, Y](f: X => Y)(sx: (S, X)): (S, Y) = (sx._1, f (sx._2))
    def smap2[S, X, Y, Z](f: X => Y => Z)(sxy: ((S, X), Y)): (S, Z) = (sxy._1._1, f (sxy._1._2) (sxy._2))

    def only[X](ex: (Empty, X)): X = ex._2

    def plusInt(x: Int)(y: Int) = x + y
    val plusIntObj: (Int => Int => Int) = { x => y => plusInt(x)(y) }
    def addInt[S](sxy: ((S, Int), Int)): (S, Int) = smap2 (plusIntObj) (sxy)
}

class StackTezt {
    import Stack._
    def testTrivial {
        assertEquals(11, only (addInt (push (6) (push (5) (Empty)))))
        assertEquals(11, Empty -- (push (5)) -- (push (6)) -- (addInt[Empty]) -- only[Int])
    }
}


case class Pr[A, B](_1: A, _2: B)

object StackPr {
    object Empty extends Empty
    class Empty

    def push[S, X](x: X)(s: S): Pr[S, X] = Pr(s, x)

    def pop[S, X](pr: Pr[S, X]): S = pr match {
        case Pr(s, x) => s
    }

    def smap[S, X, Y](f: X => Y)(pr: Pr[S, X]): Pr[S, Y] = pr match {
         case Pr(s, x) => Pr(s, f (x))
    }

    def smap2[S, X, Y, Z](f: X => Y => Z)(pr: Pr[Pr[S, X], Y]): Pr[S, Z] = pr match {
        case Pr(Pr(s, x), y) => Pr(s, f (x) (y))
    }

    def only[X](pr: Pr[Empty, X]): X = pr match {
        case Pr(Empty, x) => x
    }

    def addInt[S](pr: Pr[Pr[S, Int], Int]): Pr[S, Int] = pr match {
        case Pr(Pr(s, x), y) => Pr(s, x + y)
    }
}

/*

object Postfix1 {
    import StackPr.Empty

    type Cmd[S, SS] = S => (SS => _) => _

    def post[S, SS](f: S => SS)(s: S)(g: SS => _): Cmd[S, SS] = next (f (s)) (g)

    def next[S, A](s: S)(k: (S => A)): A = k (s)

    def addInt[S]: Cmd[Pr[Pr[S, Int], Int], Pr[S, Int]] = post (addInt[S])

    type Cmd1[X, S, SS] = S => X => (SS => _) => _

    def post1[X, S, SS](f: X => S => SS): Cmd1[X, S, SS] = next (f (x) (s)) (_: SS => _)

    def push[S, X] = post1 (Stack.push[S, X])

    def begin[A]: (Empty => A) => A = {
        k => next (Empty) (k)
    }
    def end[A]: (Empty, A) => A = {
        (Empty, a) => Stack.only (Empty, a)
    }

 //   val xx = addInt ((Empty, 6), 5)

//        = (s: ((S, Int), Int)) => (g: (S, Int) => _) => next (Stack.addInt (s)) (g)
//        = (s: ((S, Int), Int)) => next (Stack.addInt (s)) (_: (S, Int) => _)
}

object Postfix1 {
    import Stack.Empty

    type Cmd[S, SS] = S => (SS => _) => _

    def post[S, SS]: (S => SS) => Cmd[S, SS] = {
//        = (f: S => SS) => (s: S) => (g: SS => _) => next (f (s)) (g) // too is ok.
        f => s => next (f (s)) (_: SS => _)
    }

    def next[S, A]: S => (S => A) => A = {
        s => k => k (s)
    }

    def addIntObj[S]: ((S, Int), Int) => (S, Int) = {
        (a, b) => Stack.addInt (a, b)
    }
    def addInt[S]: Cmd[((S, Int), Int), (S, Int)] = {
        s => g => post (addIntObj[S]) (s) (g)
    }

    type Cmd1[X, S, SS] = S => X => (SS => _) => _

    def post1[X, S, SS]: (X => S => SS) => Cmd1[X, S, SS] = {
        f => s => x => next (f (x) (s)) (_: SS => _)
    }

    def push[S, X] = post1 (Stack.push[S, X])

    def begin[A]: (Empty => A) => A = {
        k => next (Empty) (k)
    }
    def end[A]: (Empty, A) => A = {
        (Empty, a) => Stack.only (Empty, a)
    }

    val xx = addInt ((Empty, 6), 5)

//        = (s: ((S, Int), Int)) => (g: (S, Int) => _) => next (Stack.addInt (s)) (g)
//
*/

object Postfix1 {
    import StackPr.Empty

    type Cmd[S, SS] = S => (SS => _) => _

    def post[S, SS]: (S => SS) => Cmd[S, SS] = {
//        = (f: S => SS) => (s: S) => (g: SS => _) => next (f (s)) (g) // too is ok.
        f => s => next (f (s)) (_: SS => _)
    }

    def next[S, A]: S => (S => A) => A = { s => k => k (s) }

    def addInt[S]: Cmd[Pr[Pr[S, Int], Int], Pr[S, Int]] = post (StackPr.addInt[S])

    type Cmd1[X, S, SS] = S => X => (SS => _) => _

    def post1[X, S, SS]: (X => S => SS) => Cmd1[X, S, SS] = {
        f => s => x => next (f (x) (s)) (_: SS => _)
    }

    def push[X, S]: Cmd1[X, S, Pr[S, X]] = post1 (StackPr.push[S, X])

    def begin[A](k: Empty => A): A = next (Empty) (k)
    def end[Ans](pr: Pr[Empty, Ans]): Ans = StackPr.only (pr)

    val xx = addInt (Pr(Pr(Empty, 6), 5))

//        = (s: ((S, Int), Int)) => (g: (S, Int) => _) => next (Stack.addInt (s)) (g)
//        = (s: ((S, Int), Int)) => next (Stack.addInt (s)) (_: (S, Int) => _)
}


/*
object Postfix1d {
    type Cmd[S, SS] = (S, SS => _) => _

    def post[S, SS](f: S => SS): Cmd[S, SS] = next(f (s), _: SS => _)
    def next[S, A](s: S, k: S => A): A = k(s)

    def addIntObj[S]: (  ((S, Int), Int)  ) => (S, Int) = Stack.addInt (_)
    def addInt[S]: Cmd[((S, Int), Int), (S, Int)] = post(addIntObj[S])

//        = (s: ((S, Int), Int)) => (g: (S, Int) => _) => next (Stack.addInt (s)) (g)
//        = (s: ((S, Int), Int)) => next (Stack.addInt (s)) (_: (S, Int) => _)
}*/

class Postfix1Tezt {
    import Postfix1._
    def testTrivial {
    //    val push5 = push (5) // X => (SS => _) => _
    //    begin (push5) // (SS => _) => _      push5=Empty, A=
        //         end: Pr[Empty,A] => A , SS=Pr[Empty,A], _ = A
    //    (begin (push (5))) (end)
//        assertEquals(5, begin (push (5)) (end))
 //       assertEquals(11, begin (push (5)) (push (6)) (addInt) end)
    }
}

