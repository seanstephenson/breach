package com.srs.breach.game.board

enum Terrain {

  Normal,

  // Water kills normal units, and prevents massive units from attacking.
  Water,

  // Lava is like water, but also sets units on fire.
  Lava,

  // Chasms kill non-flying units.
  Chasm,

  // Ice turns into damaged ice, then water when damaged.
  Ice,
  IceDamaged,

  // Forests catch fire when damaged.
  Forest,

  // Sand is covered in smoke when damaged.
  Sand

}
