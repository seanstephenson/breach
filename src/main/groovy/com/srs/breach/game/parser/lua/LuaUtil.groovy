package com.srs.breach.game.parser.lua

import org.luaj.vm2.LuaTable
import org.luaj.vm2.LuaValue

class LuaUtil {

  static Object toJava(LuaValue value) {
    if (value.isnil()) {
      null
    } else if (value.type() == LuaValue.TSTRING) {
      value.tojstring()
    } else if (value.isint()) {
      value.toint()
    } else if (value.isnumber()) {
      value.todouble()
    } else if (value.isboolean()) {
      value.toboolean()
    } else if (value.istable()) {
      def table = value.checktable()
      if (table.keys().every { it.isint() }) {
        toList(table)
      } else {
        toMap(table)
      }
    } else {
      value.tojstring()
    }
  }

  static Map<String, Object> toMap(LuaTable table) {
    table.keys().collectEntries { key ->
      [toJava(key), toJava(table.get(key))]
    }
  }

  static List<LuaValue> toList(LuaTable table) {
    def varargs = table.unpack()
    (1..varargs.narg()).collect { i ->
      toJava(varargs.arg(i))
    }
  }

}
