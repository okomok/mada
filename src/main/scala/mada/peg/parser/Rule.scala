

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


class RuleParser[A] extends Ref[Parser[A]](null) with Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        deref.parse(s, first, last)
    }
}


object Rules1 {
    def apply[A]: (RuleParser[A]) = (Parser.rule[A])
}

object Rules2 {
    def apply[A]: (RuleParser[A], RuleParser[A]) = (Parser.rule[A], Parser.rule[A])
}

object Rules3 {
    def apply[A]: (RuleParser[A], RuleParser[A], RuleParser[A]) = (Parser.rule[A], Parser.rule[A], Parser.rule[A])
}

object Rules4 {
    def apply[A]: (RuleParser[A], RuleParser[A], RuleParser[A], RuleParser[A]) = (Parser.rule[A], Parser.rule[A], Parser.rule[A], Parser.rule[A])
}

object Rules5 {
    def apply[A]: (RuleParser[A], RuleParser[A], RuleParser[A], RuleParser[A], RuleParser[A]) = (Parser.rule[A], Parser.rule[A], Parser.rule[A], Parser.rule[A], Parser.rule[A])
}
