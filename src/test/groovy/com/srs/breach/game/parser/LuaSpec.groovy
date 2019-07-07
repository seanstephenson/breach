package com.srs.breach.game.parser


import org.luaj.vm2.lib.jse.JsePlatform
import spock.lang.Specification

class LuaSpec extends Specification {

  def 'Run Luaj program'() {
    setup:
    def globals = JsePlatform.standardGlobals()

    def out = new ByteArrayOutputStream()
    globals.STDOUT = new PrintStream(out)

    when:
    def chunk = globals.load("print 'hello, world'")
    chunk.call()

    then:
    out.toString() == 'hello, world\n'
  }

}
