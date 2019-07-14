package com.srs.breach.ai

import com.srs.breach.game.board.Point

class BitBoard implements Iterable<Point> {

  long bits

  BitBoard() {
  }

  BitBoard(long bits) {
    this.bits = bits
  }

  BitBoard copy() {
    new BitBoard(bits)
  }

  boolean get(Point point) {
    get(point.x, point.y)
  }

  boolean get(int x, int y) {
    (bits & mask(x, y)) != 0
  }

  void set(Point point) {
    set(point.x, point.y)
  }

  void set(int x, int y) {
    bits |= mask(x, y)
  }

  void clear(int x, int y) {
    bits &= ~mask(x, y)
  }

  void setRow(int y) {
    bits |= rowMask(y)
  }

  void clearRow(int y) {
    bits &= ~rowMask(y)
  }

  void setColumn(int x) {
    bits |= columnMask(x)
  }

  void clearColumn(int x) {
    bits &= ~columnMask(x)
  }

  void setAdjacent(int x, int y) {
    bits |= adjacentMask(x, y)
  }

  void clearAdjacent(int x, int y) {
    bits &= ~adjacentMask(x, y)
  }

  void setAlignedWith(int x, int y) {
    bits |= alignedSquaresMask(x, y)
  }

  void clearAlignedWith(int x, int y) {
    bits &= ~alignedSquaresMask(x, y)
  }

  void setDistance(int x, int y, int distance) {
    bits |= distanceMask(x, y, distance)
  }

  void clearDistance(int x, int y, int distance) {
    bits &= ~distanceMask(x, y, distance)
  }

  int count() {
    Long.bitCount(bits)
  }

  boolean isEmpty() {
    bits == 0
  }

  @Override
  Iterator<Point> iterator() {
    toPoints().iterator()
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

  static BitBoard empty() {
    new BitBoard()
  }

  static BitBoard full() {
    new BitBoard(-1L)
  }

  static BitBoard of(int x, int y) {
    new BitBoard(mask(x, y))
  }

  static BitBoard of(Point point) {
    of(point.x, point.y)
  }

  static BitBoard of(List<Point> points) {

    def board = new BitBoard()
    for (point in points) {
      board.set(point.x, point.y)
    }
    board
  }

  static BitBoard adjacent(int x, int y) {
    new BitBoard(adjacentMask(x, y))
  }

  static BitBoard alignedWith(int x, int y) {
    new BitBoard(alignedSquaresMask(x, y))
  }

  static BitBoard distance(int x, int y, int distance) {
    new BitBoard(distanceMask(x, y, distance))
  }

  static BitBoard from(String text) {

    def lines = text.readLines()*.trim().findAll() as List<String>

    assert lines.size() <= 8: "expected <= 8 non-empty lines, but got [${lines.size()}] in [$text]"

    def board = new BitBoard()

    lines.eachWithIndex { line, y ->
      def tokens = line.tokenize()
      assert tokens.size() <= 8: "expected <= 8 tokens per line, but got [${tokens.size()}] in [$line]"

      tokens.eachWithIndex { token, x ->
        if (token != '.') {
          board.set(x, y)
        } else {
          board.clear(x, y)
        }
      }
    }

    board
  }


  /////////////
  //  Masks  //
  /////////////

  private static long mask(int x, int y) {
    checkRange(x, y) ?
      1L << index(x, y) :
      EMPTY_MASK
  }

  private static long rowMask(int y) {
    checkRange(0, y) ? ROW_MASK[y] : EMPTY_MASK
  }

  private static long columnMask(int x) {
    checkRange(x, 0) ? COLUMN_MASK[x] : EMPTY_MASK
  }

  private static long topRowsMask(int y) {
    y < 0 ? EMPTY_MASK :
    y >= 8 ? FULL_MASK :
    TOP_ROWS_MASK[y]
  }

  private static long bottomRowsMask(int y) {
    y < 0 ? FULL_MASK :
    y >= 8 ? EMPTY_MASK :
    BOTTOM_ROWS_MASK[y]
  }

  private static long leftColumnsMask(int x) {
    x < 0 ? EMPTY_MASK :
    x >= 8 ? FULL_MASK :
    LEFT_COLUMNS_MASK[x]
  }

  private static long rightColumnsMask(int x) {
    x < 0 ? FULL_MASK :
    x >= 8 ? EMPTY_MASK :
    RIGHT_COLUMNS_MASK[x]
  }

  private static long adjacentMask(int x, int y) {
    x == -1 ? mask(0, y) :
    x == 8 ? mask(7, y) :
    y == -1 ? mask(x, 0) :
    y == 8 ? mask(x, 7) :
    checkRange(x, y) ? ADJACENT_SQUARES_MASK[index(x, y)] : EMPTY_MASK
  }

  private static long leftSquaresMask(int x, int y) {
    x < 0 ? EMPTY_MASK :
    x >= 8 ? rowMask(y) :
    LEFT_SQUARES_MASK[index(x, y)]
  }

  private static long rightSquaresMask(int x, int y) {
    x < 0 ? rowMask(y) :
    x >= 8 ? EMPTY_MASK :
    RIGHT_SQUARES_MASK[index(x, y)]
  }

  private static long topSquaresMask(int x, int y) {
    y < 0 ? EMPTY_MASK :
    y >= 8 ? columnMask(x) :
    TOP_SQUARES_MASK[index(x, y)]
  }

  private static long bottomSquaresMask(int x, int y) {
    y < 0 ? EMPTY_MASK :
    y >= 8 ? columnMask(x) :
    BOTTOM_SQUARES_MASK[index(x, y)]
  }

  private static long alignedSquaresMask(int x, int y) {
    x < 0 || x >= 8 ? rowMask(y) :
    y < 0 || y >= 8 ? columnMask(x) :
    ALIGNED_SQUARES_MASK[index(x, y)]
  }

  private static long distanceMask(int x, int y, int distance) {
    x < 0 || x >= 8 || y < 0 || y >= 8 || distance >= 8 ?
    filterMask { filterX, filterY ->
      (x != filterX || y != filterY) &&
        (Math.abs(filterX - x) + Math.abs(filterY - y)) <= distance
    } :
    DISTANCE_MASK[distance][index(x, y)]
  }

  private static boolean checkRange(int x, int y) {
    x >= 0 && x < 8 && y >= 0 && y < 8
  }

  private static int index(int x, int y) {
    x + (y * 8)
  }

  private static <T> T[] eachSquare(Closure<T> closure) {
    (0..<64).collect { index ->
      int x = index % 8, y = index / 8
      closure(x, y)
    }
  }

  private static long filterMask(Closure<Boolean> filter) {
    (0..<64).inject(0L) { bits, index ->
      int x = index % 8, y = index / 8
      if (filter(x, y)) {
        bits |= mask(x, y)
      }
      bits
    }
  }

  private static final long EMPTY_MASK = 0L
  private static final long FULL_MASK = -1L

  private static final long FIRST_ROW_MASK = (0..<8).inject(0L) { bits, x -> bits | mask(x, 0) }
  private static final long FIRST_COLUMN_MASK = (0..<8).inject(0L) { bits, y -> bits | mask(0, y) }

  private static final long[] ROW_MASK = (0..<8).collect { y -> FIRST_ROW_MASK << (y * 8) }
  private static final long[] COLUMN_MASK = (0..<8).collect { x -> FIRST_COLUMN_MASK << x }

  private static final long EVEN_ROW_MASK = rowMask(0) | rowMask(2) | rowMask(4) | rowMask(6)
  private static final long ODD_ROW_MASK = rowMask(1) | rowMask(3) | rowMask(5) | rowMask(7)

  // Rows above the given row index.
  private static final long[] TOP_ROWS_MASK = (0..<8).collect { row ->
    (0..<row).inject(0L) { bits, x -> bits | rowMask(x) }
  }

  // Rows below the given row index.
  private static final long[] BOTTOM_ROWS_MASK = (0..<8).collect { row ->
    ((row+1)..<8).inject(0L) { bits, x -> bits | rowMask(x) }
  }

  // Columns to the left of the given column index.
  private static final long[] LEFT_COLUMNS_MASK = (0..<8).collect { column ->
    (0..<column).inject(0L) { bits, x -> bits | columnMask(x) }
  }

  // Columns to the right of the given column index.
  private static final long[] RIGHT_COLUMNS_MASK = (0..<8).collect { column ->
    ((column+1)..<8).inject(0L) { bits, x -> bits | columnMask(x) }
  }

  // Immediately adjacent squares of the given square.
  private static final long[] ADJACENT_SQUARES_MASK = eachSquare { x, y ->
    mask(x - 1, y) | mask(x + 1, y) | mask(x, y - 1) | mask(x, y + 1)
  }

  // Squares in a line directly left of the given square.
  private static final long[] LEFT_SQUARES_MASK = eachSquare { x, y ->
    rowMask(y) & leftColumnsMask(x)
  }

  // Squares in a line directly right of the given square.
  private static final long[] RIGHT_SQUARES_MASK = eachSquare { x, y ->
    rowMask(y) & rightColumnsMask(x)
  }

  // Squares in a line directly above the given square.
  private static final long[] TOP_SQUARES_MASK = eachSquare { x, y ->
    columnMask(x) & topRowsMask(y)
  }

  // Squares in a line directly below the given square.
  private static final long[] BOTTOM_SQUARES_MASK = eachSquare { x, y ->
    columnMask(x) & bottomRowsMask(y)
  }

  // Squares in a line in any direction from the given square.
  private static final long[] ALIGNED_SQUARES_MASK = eachSquare { x, y ->
    leftSquaresMask(x, y) | rightSquaresMask(x, y) | topSquaresMask(x, y) | bottomSquaresMask(x, y)
  }

  // Squares within a given manhattan distance of the given square (not including the origin square).
  private static final long[][] DISTANCE_MASK = (0..7).collect { distance ->
    eachSquare { originX, originY ->
      filterMask { x, y ->
        (x != originX || y != originY) &&
        (Math.abs(originX - x) + Math.abs(originY - y)) <= distance
      }
    }
  }

}
