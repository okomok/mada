
package mada.rng


import java.io.{File, RandomAccessFile}


// RandomAccessFiles

class IntRandomAccessFile(val base: RandomAccessFile) {
    def this(_1: File, _2: String) = this(new RandomAccessFile(_1, _2))
    def this(_1: String, _2: String) = this(new RandomAccessFile(_1, _2))

    def _indexAccess = new IndexAccess[Int] {
        override def _set(i: Long, e: Int) = { base.seek(i * 4); base.write(e) }
        override def _get(i: Long) = { base.seek(i); base.read }
        override def _size = base.length / 4
    }
}

class LongRandomAccessFile(val base: RandomAccessFile) {
    def this(_1: File, _2: String) = this(new RandomAccessFile(_1, _2))
    def this(_1: String, _2: String) = this(new RandomAccessFile(_1, _2))

    def _indexAccess = new IndexAccess[Long] {
        override def _set(i: Long, e: Long) = { base.seek(i * 8); base.writeLong(e) }
        override def _get(i: Long) = { base.seek(i); base.readLong }
        override def _size = base.length / 8
    }
}


/*
class RandomAccessFileOf[A](val base: RandomAccessFile) {
    def this(_1: File, _2: String) = this(new RandomAccessFile(_1, _2))
    def this(_1: String, _2: String) = this(new RandomAccessFile(_1, _2))

    def _indexAccess = new IndexAccess[A] {
        override def _set(i: Long, e: A) = { base.seek(i * sizeOfA); writerOfA(e) }
        override def _get(i: Long) = { base.seek(i); readerOfA() }
        override def _size = base.length / sizeOfA
    }

    private val sizeOfA: Long = {
        null.asInstanceOf[A] match {
            case _: java.lang.Byte => 1L
            case _: java.lang.Short => 2L
            case _: java.lang.Integer => 4L
            case _: java.lang.Long => 8L
            case _: java.lang.Character => 2L
        }
    }

    private val readerOfA: Unit => A = {
        null.asInstanceOf[A] match {
            case _: java.lang.Byte => base.readByte
            case _: java.lang.Short => base.readShort
            case _: java.lang.Integer => base.read
            case _: java.lang.Long => base.readLong
            case _: java.lang.Character => base.readChar
        }
    }

    private val writerOfA: A => Unit = {
        null.asInstanceOf[A] match {
            case _: java.lang.Byte => base.writeByte(_)
            case _: java.lang.Short => base.writeShort(_)
            case _: java.lang.Integer => base.write(_)
            case _: java.lang.Long => base.writeLong(_)
            case _: java.lang.Character => base.writeChar(_)
        }
    }
}
*/


//  RandomAccessFile <-> Expr[Rng[A]]

object RandomAccessFileCompatible extends RandomAccessFileCompatible; trait RandomAccessFileCompatible {
    implicit def toMadaIntRandomAccessFileRngExpr(from: IntRandomAccessFile): ExprV2.Of[Rng[Int]] = FromIntRandomAccessFileExpr(ExprV2.Constant(from)).expr
    implicit def toMadaLongRandomAccessFileRngExpr(from: LongRandomAccessFile): ExprV2.Of[Rng[Long]] = FromLongRandomAccessFileExpr(ExprV2.Constant(from)).expr
}


// toRng

object RandomAccessFileToRng extends RandomAccessFileToRng; trait RandomAccessFileToRng extends Predefs {
    // Int
    class MadaRngIntRandomAccessFileToRng(_1: ExprV2.Of[IntRandomAccessFile]) {
        def toRng = FromIntRandomAccessFileExpr(_1).expr
    }
    implicit def toMadaRngIntRandomAccessFileToRng(_1: ExprV2.Of[IntRandomAccessFile]): MadaRngIntRandomAccessFileToRng = new MadaRngIntRandomAccessFileToRng(_1)
    // Long
    class MadaRngLongRandomAccessFileToRng(_1: ExprV2.Of[LongRandomAccessFile]) {
        def toRng = FromLongRandomAccessFileExpr(_1).expr
    }
    implicit def toMadaRngLongRandomAccessFileToRng(_1: ExprV2.Of[LongRandomAccessFile]): MadaRngLongRandomAccessFileToRng = new MadaRngLongRandomAccessFileToRng(_1)
}


// RandomAccessFileExprs

case class FromIntRandomAccessFileExpr(_1: ExprV2.Of[IntRandomAccessFile]) extends ExprV2.Alias[IntRandomAccessFile, Rng[Int]] {
    override protected def _alias = IndexAccessRngExpr(_1.eval._indexAccess)
}

case class FromLongRandomAccessFileExpr(_1: ExprV2.Of[LongRandomAccessFile]) extends ExprV2.Alias[LongRandomAccessFile, Rng[Long]] {
    override protected def _alias = IndexAccessRngExpr(_1.eval._indexAccess)
}
