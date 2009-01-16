

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Peg {
    import peg._

    val FAILURE: Long = -1

    def advance[A](i: Long): Peg[A] = Advance[A](i)
    def any[A]: Peg[A] = Any_[A]
    def begin[A]: Peg[A] = Begin[A]
    def end[A]: Peg[A] = End[A]
    def call[A](f: Unit => Any): Peg[A] = Call[A](f)
    def eps[A]: Peg[A] = Eps[A]
    def error[A]: Peg[A] = Error[A]
    def fail[A]: Peg[A] = Fail[A]
    def icase(v: Vector[Char]): Peg[Char] = Icase(v)
    def lowerCaseRead(p: Peg[Char]): Peg[Char] = LowerCaseRead(p)
    def range[A](i: A, j: A)(implicit c: A => Ordered[A]): Peg[A] = Range(i, j)(c)
    def single[A](e: A): Peg[A] = Single(e)

    def longest[A](ps: Peg[A]*): Peg[A] = Longest(ps)
    def longest[A](ps: Iterable[Peg[A]]): Peg[A] = Longest(ps)
    def shortest[A](ps: Peg[A]*): Peg[A] = Shortest(ps)
    def shortest[A](ps: Iterable[Peg[A]]): Peg[A] = Shortest(ps)

    def singles[A](es: A*): Peg[A] = Singles(es)
    def singles[A](es: scala.collection.Set[A]): Peg[A] = Singles(es)
    def switch[A](es: (A, Peg[A])*): Peg[A] = Switch(es)
    def switch[A](es: scala.collection.Map[A, Peg[A]]): Peg[A] = Switch(es)

    val Compatibles = peg.Compatibles
    def regexPeg(pat: java.util.regex.Pattern): Peg[Char] = RegexPeg(pat)
    def stringPeg(str: String): Peg[Char] = StringPeg(str)
    def vectorPeg[A](v: Vector[A]): Peg[A] = VectorPeg(v)

    def __*[A]: Peg[A] = any[A].star
    def __*?[A](p: Peg[A]): Peg[A] = any[A].starBefore(p)
    def __*>>[A](p: Peg[A]): Peg[A] = any[A].starUntil(p)

    def ?~[A](p: Peg[A]): Peg[A] = p.lookAhead
    def ?![A](p: Peg[A]): Peg[A] = p.lookAhead.not
    def ?<~[A](p: Peg[A]): Peg[A] = p.lookBehind
    def ?<![A](p: Peg[A]): Peg[A] = p.lookBehind.not
    def ?<<~[A](p: Peg[A]): Peg[A] = p.lookBack
    def ?<<![A](p: Peg[A]): Peg[A] = p.lookBack.not

    type PegProxy[A] = peg.PegProxy[A]

    val Rule = peg.Rule
    type Rule[A] = peg.Rule[A]

    val ASTreeBuilder = peg.ASTreeBuilder
    type ASTreeBuilder[T <: javax.swing.tree.DefaultMutableTreeNode] = peg.ASTreeBuilder[T]

    val SymbolSet = peg.SymbolSet
    val SymbolMap = peg.SymbolMap
    type SymbolSet[A] = peg.SymbolSet[A]
    type SymbolMap[A] = peg.SymbolMap[A]

    type ByNeedActions[A] = peg.ByNeedActions[A]
    type RangeActions[A] = peg.RangeActions[A]
    type Captures[A] = peg.Captures[A]
    type PrettyPrinter = peg.PrettyPrinter
}


trait Peg[A] {
    import peg._

    def parse(v: Vector[A], first: Long, last: Long): Long
    def length: Long = throw new UnsupportedOperationException("Peg.length")
    protected final val FAILURE = Peg.FAILURE

    final def and(that: Peg[A]): Peg[A] = And(this, that)
    final def or(that: Peg[A]): Peg[A] = Or(this, that)
    final def minus(that: Peg[A]) = Minus(this, that)
    final def xor(that: Peg[A]): Peg[A] = Xor(this, that)
    final def not: Peg[A] = Not(this)

    final def seqAnd(that: Peg[A]): Peg[A] = SeqAnd(this, that)
    final def seqOr(that: Peg[A]): Peg[A] = SeqOr(this, that)

    final def star: Peg[A] = Star(this)
    final def starBefore(that: Peg[A]): Peg[A] = StarBefore(this, that)
    final def starUntil(that: Peg[A]): Peg[A] = StarUntil(this, that)

    final def plus: Peg[A] = Plus(this)
    final def plusBefore(that: Peg[A]): Peg[A] = PlusBefore(this, that)
    final def plusUntil(that: Peg[A]): Peg[A] = PlusUntil(this, that)

    final def opt: Peg[A] = Opt(this)
    final def optBefore(that: Peg[A]): Peg[A] = OptBefore(this, that)
    final def optUntil(that: Peg[A]): Peg[A] = OptUntil(this, that)

    final def lookAhead: Peg[A] = LookAhead(this)
    final def lookBehind: Peg[A] = LookBehind(this)
    final def lookBack: Peg[A] = LookBack(this)

    final def act(f: (Vector[A], Long, Long) => Any): Peg[A] = Act(this, f)
    final def andIf(pred: (Vector[A], Long, Long) => Boolean): Peg[A] = AndIf(this, pred)
    final def identity: Peg[A] = Identity(this)
    final def memoize: Peg[A] = Memoize(this)
    final def named(name: String) = Named(this, name)
    final def repeat(min: Long, max: Long): Peg[A] = Repeat(this, min, max)

    final def readMap[Z](f: Vector[Z] => Vector[A]): Peg[Z] = ReadMap(this, f)
    final def unmap[Z](f: Z => A): Peg[Z] = Unmap(this, f)

    final def find(v: Vector[A]): Option[(Long, Long)] = Find(this, v)
    final def lookingAt(v: Vector[A]): Option[Long] = LookingAt(this, v)
    final def matches(v: Vector[A]): Boolean = Matches(this, v)

    final def tokenize(v: Vector[A]): Iterator[(Vector[A], Long, Long)] = Tokenize(this, v)
    final def tokens(v: Vector[A]): Iterator[Vector[A]] = Tokens(this, v)
    final def filterFrom(v: Vector[A]): Iterator[A] = FilterFrom(this, v)

    final def unary_~ : Peg[A] = lookAhead
    final def unary_! : Peg[A] = lookAhead.not
    final def unary_- : Peg[A] = not
    final def &(that: Peg[A]): Peg[A] = and(that)
    final def |(that: Peg[A]): Peg[A] = or(that)
    final def -(that: Peg[A]): Peg[A] = minus(that)
    final def ^(that: Peg[A]): Peg[A] = xor(that)
    final def >>(that: Peg[A]): Peg[A] = seqAnd(that)
    final def ||(that: Peg[A]): Peg[A] = seqOr(that)
    final def * : Peg[A] = star
    final def *?(that: Peg[A]): Peg[A] = starBefore(that)
    final def *>>(that: Peg[A]): Peg[A] = starUntil(that)
    final def + : Peg[A] = plus
    final def +?(that: Peg[A]): Peg[A] = plusBefore(that)
    final def +>>(that: Peg[A]): Peg[A] = plusUntil(that)
    final def ? : Peg[A] = opt
    final def ??(that: Peg[A]): Peg[A] = optBefore(that)
    final def ?>>(that: Peg[A]): Peg[A] = optUntil(that)
    final def ^^(f: (Vector[A], Long, Long) => Any): Peg[A] = act(f)

    final def >>?~(that: Peg[A]): Peg[A] = seqAnd(that.lookAhead)
    final def >>?!(that: Peg[A]): Peg[A] = seqAnd(that.lookAhead.not)
    final def >>?<~(that: Peg[A]): Peg[A] = seqAnd(that.lookBehind)
    final def >>?<!(that: Peg[A]): Peg[A] = seqAnd(that.lookBehind.not)
    final def >>?<<~(that: Peg[A]): Peg[A] = seqAnd(that.lookBack)
    final def >>?<<!(that: Peg[A]): Peg[A] = seqAnd(that.lookBack.not)

    final def inCase(e: A): (A, Peg[A]) = (e, this)
}
