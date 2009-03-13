

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Auto
import junit.framework.Assert._


object MyFile {
    implicit object myAuto extends Auto[MyFile] {
        override def dispose(x: MyFile) = x.disposed = true
    }
}
class MyFile {
    var disposed = false
    def read: Unit = { }
}


class HisFile[A] extends Auto.Interface {
    var disposed = false
    override def dispose = disposed = true
    def read: Unit = { }
}


object HerFile {
/*
    // "implicit class" might have been better.
    implicit object myAuto extends Auto[HerFile[_]] {
        override def dispose(x: HerFile[_]) = x.disposed = true
    }
*/
    implicit def toAuto[A]/*(implicit c: A => A)*/: Auto[HerFile[A]] = new Auto[HerFile[A]] {
        override def dispose(x: HerFile[A]) = x.disposed = true
    }
}

class HerFile[A] {
    var disposed = false
    def read: Unit = { }
}


/*
class LalalaTest {
      trait Show[T] { def show(t: T): String }

  implicit object ShowString extends Show[String] { def show(s: String) = s }

  def willShow[T](t: T)(implicit showT: Show[T]) = showT.show(t)

  def testMe {

  implicit def showInstList[T](implicit showT: Show[T]): Show[List[T]] =
    new Show[List[T]] {
      def show(ts: List[T]) = ts.map(showT.show(_)).mkString("[", ",", "]")
    }

  println(willShow("hi"))
  println(willShow(List("hi", "there")))
  println(willShow(List(List("hi", "there"), List("blah"))))
  }
}
*/


class AutoTest {
    def testTrivial: Unit = {
        val file = new MyFile
        assertFalse(file.disposed)
        Auto(file){ f =>
            f.read
        }
        assertTrue(file.disposed)
    }

    def testHis: Unit = {
        val file = new HisFile[Int]
        assertFalse(file.disposed)
        Auto(file){ f =>
            f.read
        }
        assertTrue(file.disposed)
    }

    def testHer: Unit = {
        val file = new HerFile[Int]
        assertFalse(file.disposed)
        Auto(file){ f =>
            f.read
        }//(file)
        assertTrue(file.disposed)
    }

    def testThrow: Unit = {
        val file = new MyFile
        var thrown = false
        assertFalse(file.disposed)
        assertFalse(thrown)
        try {
            Auto(file){ f =>
                f.read
                throw new Error("wow")
            }
        } catch {
            case _: Error => thrown = true
        }
        assertTrue(thrown)
        assertTrue(file.disposed)
    }

    def testEligibles: Unit = {
        Auto(new java.io.StringReader("abc")){ r =>
            ()
        }
    }
}
