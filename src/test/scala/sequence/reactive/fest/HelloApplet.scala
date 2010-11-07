


// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest.reactivetest.festtest.helloapplet


import javax.swing.JApplet
import javax.swing.SwingUtilities
import javax.swing.JLabel

import com.github.okomok.mada
import org.fest.swing.applet.AppletViewer
import org.fest.swing.fixture.FrameFixture
import org.fest.swing.launcher.AppletLauncher
import org.testng.annotations._


class HelloApplet extends JApplet {
    override def init {
        try {
            SwingUtilities.invokeAndWait {
                mada.util.ByName {
                    add(new JLabel("Hello World"))
                }
            }
        } catch {
            case e: Exception => System.err.println("createGUI didn't complete successfully");
        }
    }
}


class HelloAppletTezt {//Test extends org.scalatest.testng.TestNGSuite {
    private var viewer: AppletViewer = null
    private var applet: FrameFixture = null

    @Test def testUntitled {
        applet = new FrameFixture(viewer)
        applet.show()
    }

    @BeforeMethod def setup {
        viewer = AppletLauncher.applet(classOf[HelloApplet]).start()
    }
}

