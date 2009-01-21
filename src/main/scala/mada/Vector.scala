

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Vector {
    import vec._

    val asLazy = AsLazy
    val concat = Concat
    def empty[A]: Vector[A] = Empty.apply[A]
    val flatten = Flatten
    val flatten3 = Flatten3
    val lefts = Lefts
    val rights = Rights
    val lowerCase = LowerCase
    val upperCase = UpperCase
    val range = Range
    val single= Single
    val sort = Sort
    val stringize = Stringize
    val undivide = Undivide
    val undivide3 = Undivide3
    val untokenize = Untokenize
    val untokenize3 = Untokenize3
    val unzip = Unzip

    val Compatibles = vec.Compatibles
    val arrayVector = ArrayVector
    val cellVector = CellVector
    val jclCharSequenceVector = jcl.CharSequenceVector
    val jclListVector = jcl.ListVector
    val optionVector = OptionVector
    val productVector = ProductVector
    val randomAccessSeqVector = RandomAccessSeqVector
    val stringVector = StringVector
    val tripleVector = TripleVector
    val jclCharSequence = jcl.VectorCharSequence

    val fromIterator = FromIterator
    val fromJclIterator = jcl.FromIterator
    val fromValues = FromValues

    val triplify = Triplify
    val untriplify = Untriplify
    type Triple[A] = (Vector[A], Int, Int)
    type Func[A, B] = Vector[A] => B
    type Func3[A, B] = (Vector[A], Int, Int) => B

    val triples = VectorTriples
    val triplesVector = TriplesVector

    type NotReadableError[A] = vec.NotReadableError[A]
    type NotWritableError[A] = vec.NotWritableError[A]

    type VectorAdapter[Z, A] = vec.VectorAdapter[Z, A]
    type VectorProxy[A] = vec.VectorProxy[A]
    type NotWritable[A] = vec.NotWritable[A]

    type IntFileVector = vec.IntFileVector
    type LongFileVector = vec.LongFileVector
}


trait Vector[A] {
    import vec._

    def size: Int
    def apply(i: Int): A = throw new NotReadableError(this)
    def update(i: Int, e: A): Unit = throw new NotWritableError(this)

    final def vector: Vector[A] = this
    def triple: Vector.Triple[A] = VectorTriple(this)

    override def equals(that: Any): Boolean = Equals(this, that)
    def equalsTo[B](that: Vector[B]): Boolean = EqualsTo(this, that)
    def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(this, that, p)
    override def hashCode: Int = HashCode(this)
    final def refHashCode: Int = super.hashCode
    override def toString: String = ToString(this)

    final def break(p: A => Boolean): (Vector[A], Vector[A]) = Break(this, p)
    final def partition(p: A => Boolean): (Vector[A], Vector[A]) = Partition(this, p)
    final def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)
    final def splitAt(i: Int): (Vector[A], Vector[A]) = SplitAt(this, i)

    final def divide(n: Int): Vector[Vector[A]] = Divide(this, n)
    final def divide3(n: Int): Vector[Vector.Triple[A]] = Divide3(this, n)

    final def drop(n: Int): Vector[A] = Drop(this, n)
    final def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)
    final def take(n: Int): Vector[A] = Take(this, n)
    final def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)
    final def offset(i: Int, j: Int): Vector[A] = Offset(this, i, j)
    final def slice(n: Int, m: Int): Vector[A] = Slice(this, n, m)
    final def slice(n: Int): Vector[A] = Slice(this, n)
    def window(n: Int, m: Int): Vector[A] = Window(this, n, m)

    final def elements: Iterator[A] = iterator
    final def iterator: Iterator[A] = VectorIterator(this)
    final def jclListIterator: java.util.ListIterator[A] = jcl.VectorListIterator(this)
    final def linearAccessSeq: Seq[A] = LinearAccessSeq(this)
    def randomAccessSeq: RandomAccessSeq.Mutable[A] = VectorRandomAccessSeq(this)

    final def toArray: Array[A] = ToArray(this)
    final def toJclArrayList: java.util.ArrayList[A] = jcl.ToArrayList(this)
    final def toList: List[A] = ToList(this)

    final def first: A = First(this)
    def firstOption: Option[A] = FirstOption(this)
    final def last: A = Last(this)
    def lastOption: Option[A] = LastOption(this)
    final def init: Vector[A] = Init(this)

    final def head: A = Head(this)
    final def tail: Vector[A] = Tail(this)
    final def isNil: Boolean = IsNil(this)

    final def filter(p: A => Boolean): Vector[A] = Filter(this, p)
    final def remove(p: A => Boolean): Vector[A] = Remove(this, p)
    def step(n: Int): Vector[A] = Step(this, n)

    final def flatMap[B](f: A => Vector[B]): Vector[B] = FlatMap(this, f)
    def map[B](f: A => B): Vector[B] = Map(this, f)

    def foreach(f: A => Unit): Unit = Foreach(this, f)
    def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = Loop(this, i, j, f)

    final def contains(e: Any): Boolean = Contains(this, e)
    def find(p: A => Boolean): Option[A] = Find(this, p)
    final def forall(p: A => Boolean): Boolean = Forall(this, p)
    final def exists(p: A => Boolean): Boolean = Exists(this, p)

    def fold(z: A)(op: (A, A) => A): A = Fold(this, z, op)
    final def foldLeft[B](z: B)(op: (B, A) => B): B = FoldLeft(this, z, op)
    final def foldRight[B](z: B)(op: (A, B) => B): B = FoldRight(this, z, op)
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)
    final def :\[B](z: B)(op: (A, B) => B): B = foldRight(z)(op)

    def reduce(op: (A, A) => A): A = Reduce(this, op)
    final def reduceLeft[B >: A](op: (B, A) => B): B = ReduceLeft(this, op)
    final def reduceRight[B >: A](op: (A, B) => B): B = ReduceRight(this, op)

    final def isDefinedAt(x: Int): Boolean = IsDefinedAt(this, x)
    final def isEmpty: Boolean = IsEmpty(this)

    def parallel: Vector[A] = Parallel(this)
    def parallel(grainSize: Int): Vector[A] = Parallel(this, grainSize)
    final def unparallel: Vector[A] = Unparallel(this)

    final def apply(n: Int, m: Int): Vector[A] = window(n, m)
    final def ++(that: Vector[A]): Vector[A] = append(that)
    final def -->(p: Peg[A]): (Vector[A], Peg[A]) = (this, p)

    final def identity: Vector[A] = Identity(this)
    def reverse: Vector[A] = Reverse(this)
    final def rotate(i: Int): Vector[A] = Rotate(this, i)
    final def permutation(iv: Vector[Int]): Vector[A] = Permutation(this, iv)

    final def always[B](that: Vector[B]): Vector[B] = Always(this, that)
    final def append(that: Vector[A]): Vector[A] = Append(this, that)
    final def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)
    def bounds: Vector[A] = Bounds(this)
    final def clear: Vector[A] = Clear(this)
    override def clone: Vector[A] = Clone(this)
    def copyTo[B >: A](that: Vector[B]): Vector[A] = CopyTo(this, that)
    def count(p: A => Boolean): Int = Count(this, p)
    final def cut: Vector[A] = Cut(this)
    def cycle(n: Int): Vector[A] = Cycle(this, n)
    def force: Vector[A] = Force(this)
    final def indices: Vector[Int] = Indices(this)
    def lazyValues : Vector[A] = LazyValues(this)
    final def length: Int = size
    def readOnly: Vector[A] = ReadOnly(this)
    def sort(lt: (A, A) => Boolean): Vector[A] = Sort(this, lt)
    final def stream: Stream[A] = VectorStream(this)
    final def writer(i: Int): (A => Unit) = Writer(this, i)
    final def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)
}
