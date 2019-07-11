package com.srs.breach.ai

import com.srs.breach.game.board.Point

class BitBoard {

  private static final long EVEN_ROW_MASK = rowMask(0) | rowMask(2) | rowMask(4) | rowMask(6)
  private static final long ODD_ROW_MASK = rowMask(1) | rowMask(3) | rowMask(5) | rowMask(7)

  long bits

  BitBoard() {
  }

  BitBoard(long bits) {
    this.bits = bits
  }

  boolean get(int x, int y) {
    (bits & mask(x, y)) != 0
  }

  void set(int x, int y, boolean value) {
    if (value) {
      bits |= mask(x, y)
    } else {
      bits &= ~mask(x, y)
    }
  }

  int count() {
    Long.bitCount(bits)
  }

  boolean isEmpty() {
    bits == 0
  }

  BitBoard shift(int offsetX, int offsetY) {
    def shifted = new BitBoard(bits)
    shifted.shiftX(offsetX)
    shifted.shiftY(offsetY)
    shifted
  }

  BitBoard union(BitBoard other) {
    new BitBoard(bits | other.bits)
  }

  BitBoard plus(BitBoard other) {
    union(other)
  }

  BitBoard intersect(BitBoard other) {
    new BitBoard(bits & other.bits)
  }

  BitBoard and(BitBoard other) {
    intersect(other)
  }

  BitBoard minus(BitBoard other) {
    new BitBoard(bits & ~other.bits)
  }

  private void shiftX(int offset) {

    if (offset == 0) return

    if (offset >= 8 || offset <= -8) {
      bits = 0L
      return
    }

    if (offset > 0) {
      bits = (((bits & EVEN_ROW_MASK) << offset) & EVEN_ROW_MASK) |
        (((bits & ODD_ROW_MASK) << offset) & ODD_ROW_MASK)
    } else {
      bits = (((bits & EVEN_ROW_MASK) >> -offset) & EVEN_ROW_MASK) |
        (((bits & ODD_ROW_MASK) >> -offset) & ODD_ROW_MASK)
    }
  }

  private void shiftY(int offset) {
    if (offset == 0) return

    if (offset >= 8 || offset <= -8) {
      bits = 0L
      return
    }

    if (offset > 0) {
      bits <<= (offset * 8)
    } else {
      bits >>= (-offset * 8)
    }
  }

  List<Point> toPoints() {
    def points = []
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        if (get(x, y)) {
          points << new Point(x, y)
        }
      }
    }
    points
  }

  @Override
  boolean equals(Object other) {
    other instanceof BitBoard &&
      bits == other.bits
  }

  @Override
  int hashCode() {
    Long.hashCode(bits)
  }

  @Override
  String toString() {
    def text = Long.toString(bits, 2)
      .padLeft(64, '0')
      .reverse()

    text = text.replaceAll(/([0-1]{8})/, '$1\n')
    text = text.replace('0', '. ')
    text = text.replace('1', 'X ')
    text = text.readLines()*.trim().join('\n')

    text
  }

  private static long mask(int x, int y) {
    assertRange(x, y)
    1L << (x + (y * 8))
  }

  private static long rowMask(int y) {
    assertRange(0, y)
    0xFFL << (y * 8)
  }

  private static void assertRange(int x, int y) {
    assert x >= 0 && x < 8
    assert y >= 0 && y < 8
  }

  static BitBoard empty() {
    new BitBoard()
  }

  static BitBoard full() {
    new BitBoard(-1L)
  }

  static BitBoard from(String text) {

    def lines = text.readLines()*.trim().findAll() as List<String>

    assert lines.size() <= 8: "expected <= 8 non-empty lines, but got [${lines.size()}] in [$text]"

    def board = new BitBoard()

    lines.eachWithIndex { line, y ->
      def tokens = line.tokenize()
      assert tokens.size() <= 8: "expected <= 8 tokens per line, but got [${tokens.size()}] in [$line]"

      tokens.eachWithIndex { token, x ->
        board.set(x, y, token != '.')
      }
    }

    board
  }

  static BitBoard from(List<Point> points) {

    def board = new BitBoard()
    for (point in points) {
      board.set(point.x, point.y, true)
    }
    board
  }

}
