

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: "Packrat Parsers Can Support Left Recursion"


package mada.peg


/*

class LRules[A] {
    type Map[K, V] = java.util.HashMap[K, V]

    type RULE = LRule
    type POSITION = TripleKey[A]
    type SET_OF_RULE = java.util.HashSet[RULE]

    trait AST
    case object FAIL extends AST
    case object SUCCESS extends AST
    case class LR(var seed: AST, rule: RULE, var head: HEAD, next: LR)
    case class HEAD(rule: RULE, involvedSet: SET_OF_RULE, var evalSet: SET_OF_RULE)
    case class MEMOENTRY(var ans: Either[AST, LR], var pos: POSITION)

    implicit def AST2Either(ast: AST): Either[AST, LR] = Left[AST, LR](ast)
    def Either2AST(ei: Either[AST, LR]): AST = ei.left.get
    implicit def LR2Either(lr: LR): Either[AST, LR] = Right[AST, LR](lr)
    implicit def Either2LR(ei: Either[AST, LR]): LR = ei.right.get

    private var Pos: POSITION = null
    private var LRStack: LR = null

    def Pos_<=(that: POSITION): Boolean = Pos.first <= that.first

    def makeRule: LRule = new LRule

    class LRule(private var p: Peg[A]) extends PegProxy[A] {
        def this() = this(null)

        override def self = p
        def ::=(that: Peg[A]): Unit = { p = that }
        def <--(that: Peg[A]): Unit = { this ::= that }
        def copy: LRule = new LRule(p)

        val memoTable = new Map[POSITION, MEMOENTRY]
        def body = self

        override def parse(v: Vector[A], first: Long, last: Long) = {
            Pos = TripleKey(v, first, last)
            APPLY_RULE(this, Pos)
            Pos.first
        }
    }

    def EVAL(B: Peg[A]): AST = {
        if (B.parse(Pos.v, Pos.first, Pos.last) == Peg.FAILURE) {
            FAIL
        } else {
            SUCCESS
        }
    }

    object MEMO {
        def apply(R: RULE, P: POSITION): MEMOENTRY = R.memoTable.get(P)
        def update(R: RULE, P: POSITION, M: MEMOENTRY): Unit = R.memoTable.put(P, M)
    }

    object HEADS {
        private val heads = new Map[POSITION, HEAD]
        def apply(P: POSITION): HEAD = heads.get(P)
        def update(P: POSITION, H: HEAD): Unit = {
            if (H == null) {
                heads.remove(P)
            } else {
                heads.put(P, H)
            }
        }
    }

    def APPLY_RULE(R: RULE, P: POSITION): Either[AST, LR] = {
        val m = RECALL(R, P)
        if (m == null) {
            // Create a new LR and push it onto the rule
            // invocation stack.
            val lr = new LR(FAIL, R, null, LRStack)
            LRStack = lr
            // Memoize lr, then evaluate R.
            val m = new MEMOENTRY(lr, P) // ***recursion detecting****
            MEMO(R, P) = m
            val ans = EVAL(R.body)
            // Pop lr off the rule invocation stack.
            LRStack = LRStack.next
            m.pos = Pos
            if (lr.head != null) {
                lr.seed = ans
                return LR_ANSWER(R, P, m)
            } else {
                m.ans = ans
                return ans
            }
        } else { // memo hit
            Pos = m.pos
            if (m.ans.isRight) { // if m.ans is LR ***recursion detected****
                SETUP_LR(R, m.ans)
                return m.ans.seed
            } else {
                return m.ans
            }
        }
    }

    def RECALL(R: RULE, P: POSITION): MEMOENTRY = {
        val m = MEMO(R, P)
        val h = HEADS(P)
        // If not growing a seed parse, just return what is stored
        // in the memo table.
        if (h == null) {
            return m
        }
        // Do not evaluate any rule that is not involved in this
        // left recursion.
        if (m != null && (h.rule == R || h.involvedSet.contains(R))) {
            return new MEMOENTRY(FAIL, P)
        }
        // Allow involved rules to be evaluated, but only once,
        // during a seed-growing iteration.
        if (h.evalSet.contains(R)) {
            h.evalSet.remove(R)
            val ans = EVAL(R.body)
            m.ans = ans
            m.pos = Pos
        }
        return m
    }

    def EMPTY_SET = new SET_OF_RULE

    def SETUP_LR(R: RULE, L: LR): Unit = {
        if (L.head == null) {
            L.head = new HEAD(R, EMPTY_SET, EMPTY_SET)
        }
        var s = LRStack
        while (s.head != L.head) {
            s.head = L.head
            L.head.involvedSet.add(s.rule)
            s = s.next
        }
    }

    def LR_ANSWER(R: RULE, P: POSITION, M: MEMOENTRY): Either[AST, LR] = {
        val h = M.ans.head
        if (h.rule != R) {
            return M.ans.seed
        } else {
            M.ans = M.ans.seed
            if (Either2AST(M.ans) == FAIL) {
                return FAIL
            } else {
                return GROW_LR(R, P, M, h)
            }
        }
    }

    def COPY(S: SET_OF_RULE): SET_OF_RULE = S.clone.asInstanceOf[SET_OF_RULE]

    def GROW_LR(R: RULE, P: POSITION, M: MEMOENTRY, H: HEAD): Either[AST, LR] = {
        HEADS(P) = H // line A
        loop
        def loop: Unit = {
            while (true) {
                Pos = P
                H.evalSet = COPY(H.involvedSet) // line B
                val ans = EVAL(R.body)
                if (ans == FAIL || Pos_<=(M.pos)) {
                    return // break
                }
                M.ans = ans
                M.pos = Pos
            }
        }
        HEADS(P) = null // line C
        Pos = M.pos
        return M.ans
    }
}


class LRule[A](v: Vector[A]) extends Peg[A] {
    private var p: Peg[A] = null
    private val mp = new Peg.Memoizer(v).memoize(this)

    def left: Peg[A] = mp
    def ::=(that: Peg[A]): Unit = { p = that }
    def <--(that: Peg[A]): Unit = { this ::= that }

    private var parsing = false
    private var position = FAILURE
    private var recurred = false

    override def parse(v: Vector[A], first: Long, last: Long) = {
        if (parsing && first == position) {
            recurred = true
            FAILURE
        } else {
            parsing = true
            position = first
            val cur = p.parse(v, first, last)
            mp.table.put(first, cur)
            position = FAILURE
            parsing = false
            if (recurred) {
                println("recurred:" + cur)
                recurred = false
                if (cur != FAILURE) {
                    grow(v, first, last)
                } else {
                    cur
                }
            } else {
                cur
            }
        }
    }

    private def grow(v: Vector[A], first: Long, last: Long): Long = {
        var cur = first
        while (true) {
            val i = p.parse(v, first, last)
            println("iteration:" + i)
            if (i == FAILURE || i <= cur) {
                return cur
            }
            cur = i
            mp.table.put(first, cur)
        }
        cur
    }
}

class LRule[A](v: Vector[A]) extends Peg[A] {
    private var p: Peg.Memoizer[A]#MemoizePeg = null
    private var q: Peg[A] = null
    private val memo = new Peg.Memoizer(v)

    def ::=(that: Peg[A]): Unit = { q = that; p = memo.memoize(that) }
    def <--(that: Peg[A]): Unit = { this ::= that }

    private var parsing = false
    private var recurred = false

    override def parse(v: Vector[A], first: Long, last: Long) = {
        if (parsing) {
            recurred = true
            FAILURE
        } else {
            parsing = true
            val cur = p.parse(v, first, last)
            parsing = false
            if (recurred) {
                recurred = false
                if (cur != FAILURE) {
                    growLR(v, first, last)
                } else {
                    cur
                }
            } else {
                cur
            }
        }
    }

    private def growLR(v: Vector[A], first: Long, last: Long): Long = {
        var cur = first
        while (true) {
            val i = q.parse(v, first, last)
            if (i == FAILURE || i <= cur) {
                return cur
            }
            cur = i
            p.table.put(first, cur)
        }
        cur
    }
}*/
