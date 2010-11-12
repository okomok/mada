

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private[vector]
class Common {


// aliases

    @aliasOf("Vector[A] => B")
    type Func[-A, B] = Vector[A] => B

    @aliasOf("(Vector[A], Int, Int) => B")
    type Func3[-A, B] = (Vector[A], Int, Int) => B

    @aliasOf("Func[A, Boolean]")
    type Pred[-A] = Func[A, Boolean]

    @aliasOf("Func3[A, Boolean]")
    type Pred3[-A] = Func3[A, Boolean]

    @aliasOf("Adapter[A, A]")
    type TransformAdapter[+A] = Adapter[A, A]


// constants

    /**
     * @return  <code>java.lang.Math.MIN_INT</code>, which is the reserved index by <code>mada.Vector</code>.
     */
    final val SINGULAR = 0x80000000


// constructors

    @returnThat
    def from[A](to: Vector[A]): Vector[A] = to

    /**
     * @return  an uninitialized vector with the specified size.
     */
    def allocate[A](c: Int): Vector[A] = Allocate[A](c)

    /**
     * @return  an empty vector.
     */
    val empty: Vector[Nothing] = Empty()

    /**
     * Concatenate all argument sequences into a single vector.
     *
     * @param   vs  the given argument sequences
     * @return  the projection vector created from the concatenated arguments
     */
    def concat[A](vs: Vector[A]*): Vector[A] = Concat(vs)

    /**
     * Creates a vector containing of successive integers.
     *
     * @param   i   the value of the first element of the vector
     * @param   j   the value of the last element of the vector plus 1
     * @return  the sorted vector of all integers in range [i, j).
     */
    def range(i: Int, j: Int): Vector[Int] = Range(i, j)

    /**
     * @return  <code>range(i, java.lang.Integer.MAX_VALUE)</code>.
     */
    def range(i: Int, u: Unit): Vector[Int] = Range(i, java.lang.Integer.MAX_VALUE)

    /**
     * @param   e   the element
     * @return  the writable vector with one single element.
     */
    def single[A](e: A): Vector[A] = Single(e)

    /**
     * Returns by-lazy vector.
     */
    def `lazy`[A](v: => Vector[A]) = Lazy(v)

    /**
     * Returns by-name vector.
     */
    def byName[A](v: => Vector[A]) = ByName(v)


// triplify

    /**
     * Converts a <code>Func</code> to <code>Func3</code>.
     */
    def triplify[A, B](f: Func[A, B]): Func3[A, B] = Triplify(f)

    /**
     * Converts a <code>Func3</code> to <code>Func</code>.
     */
    def untriplify[A, B](f: Func3[A, B]): Func[A, B] = Untriplify(f)


// file

    /**
     * Creates a file vector with the specified primitive type.
     */
    def file[A](f: java.io.RandomAccessFile)(implicit rm: scala.reflect.Manifest[A]): Arm[Vector[A]] = {
        (rm.toString match {
            case "Char" => CharFile(f)
            case "Int" => IntFile(f)
            case "Long" => LongFile(f)
            case _ => throw new UnsupportedOperationException("coming soon: " + rm.toString)
        }).asInstanceOf[Arm[Vector[A]]]
    }

    @equivalentTo("file[A](new java.io.RandomAccessFile(f, m))(rm)")
    def file[A](f: java.io.File, m: String)(implicit rm: scala.reflect.Manifest[A]): Arm[Vector[A]] = file[A](new java.io.RandomAccessFile(f, m))(rm)

    @equivalentTo("file[A](new java.io.RandomAccessFile(n, m))(rm)")
    def file[A](n: String, m: String)(implicit rm: scala.reflect.Manifest[A]): Arm[Vector[A]] = file[A](new java.io.RandomAccessFile(n, m))(rm)


// pattern matching

    @aliasOf("Of.apply")
    def apply[A](from: A*): Vector[A] = Of.apply(from: _*)

    @aliasOf("Of.unapplySeq")
    def unapplySeq[A](from: Vector[A]): Option[Seq[A]] = Of.unapplySeq(from)

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Vector[A] = Iterative.from(from).toVector
        def unapplySeq[A](from: Vector[A]): Option[Seq[A]] = Some(from.toSIndexedSeq)
    }


// Ordering

    def lexicographicalOrdering[A](implicit c: Ordering[A]): Ordering[Vector[A]] = LexicographicalOrdering(c)

}
