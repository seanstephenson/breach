package com.srs.breach.game.parser.lua


import org.luaj.vm2.lib.jse.JsePlatform
import spock.lang.Specification

class LuaSpec extends Specification {

  def globals = JsePlatform.standardGlobals()

  def 'Run Luaj program'() {
    setup:
    def out = new ByteArrayOutputStream()
    globals.STDOUT = new PrintStream(out)

    when:
    def chunk = globals.load("print 'hello, world'")
    chunk.call()

    then:
    out.toString() == 'hello, world\n'
  }

  def 'Assign global table'() {
    when:
    def chunk = globals.load('''
      Data = {
        ["one"] = 1,
        ["two"] = 2, 
        ["child"] = {
          ["a"] = "a",
          [3] = 3,
        },
      }
    ''')
    chunk.call()

    then:
    def data = globals.get('Data')
    data.istable()

    data.get('invalid').isnil()

    data.get('one').isint()
    data.get('one').toint() == 1

    data.get('two').isint()
    data.get('two').toint() == 2

    def child = data.get('child')
    child.istable()

    child.get('a').isstring()
    child.get('a').tojstring() == 'a'

    child.get(3).isint()
    child.get(3).toint() == 3
  }

}
