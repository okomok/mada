

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package scopedtest


package scopeA {
    private[this] class PrivateClass1
    package scopeA2 {
        private class PrivateClass2
        private object k extends PrivateClass1
    }
    //class PrivateClass3 extends PrivateClass1 // ERROR
    //protected class PrivateClass4 extends PrivateClass1 // ERROR
    private class PrivateClass5 extends PrivateClass1
    private[this] class PrivateClass6 extends PrivateClass1
   // private[this] class PrivateClass7 extends scopeA2.PrivateClass2 // ERROR //

}
package scopeB {
  //  class PrivateClass1B extends scopeA.PrivateClass1 // ERROR // pass

}
