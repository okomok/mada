

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.{auto, Auto}
import junit.framework.Assert._


object MyFile {
    implicit object myauto extends auto.Type[MyFile] {
        override def end(x: MyFile) = x.disposed = true
    }
}
class MyFile {
    var disposed = false
    def read: Unit = { }
}


class HisFile[A] extends auto.Interface {
    var disposed = false
    override def autoEnd = disposed = true
    def read: Unit = { }
}


object HerFile {
/*
    // "implicit class" might have been better.
    implicit object myauto extends auto.Type[HerFile[_]] {
        override def end(x: HerFile[_]) = x.disposed = true
    }
*/
    implicit def toautoType[A]/*(implicit c: A => A)*/: auto.Type[HerFile[A]] = new auto.Type[HerFile[A]] {
        override def end(x: HerFile[A]) = x.disposed = true
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
        auto.using(file){ f =>
            f.read
        }
        assertTrue(file.disposed)
    }

    def testHis: Unit = {
        val file = new HisFile[Int]
        assertFalse(file.disposed)
        val tmp = auto.using(file){ f =>
            f.read; 3
        }
        assertTrue(file.disposed)
        assertEquals(3, tmp)
    }

    def testHer: Unit = {
        val file = new HerFile[Int]
        assertFalse(file.disposed)
        Auto(file){
            _.read
        }//(file)
        assertTrue(file.disposed)
    }

    def testThrow: Unit = {
        val file = new MyFile
        var thrown = false
        assertFalse(file.disposed)
        assertFalse(thrown)
        try {
            auto.using(file){ f =>
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
        auto.using(new java.io.StringReader("abc")){ r =>
            ()
        }
    }

    def testVarArg: Unit = {
        val file1, file2, file3 = new MyFile
        auto.using(file1, file2, file3) { (f1, f2, f3) =>
            ()
        }
        assertTrue(file1.disposed)
        assertTrue(file2.disposed)
        assertTrue(file3.disposed)
    }
}
