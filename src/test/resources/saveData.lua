GameData = {
  ["save_version"] = 1,
  ["network"] = 2,
  ["networkMax"] = 7,
  ["overflow"] = 0,
  ["seed"] = 570991529,
  ["difficulty"] = 1,
  ["ach_info"] = {
    ["squad"] = "Archive_A",
    ["trackers"] = {
      ["Detritus_B_2"] = 0,
      ["Global_Challenge_Power"] = 2,
      ["Archive_A_1"] = 0,
      ["Archive_B_2"] = 0,
      ["Rust_A_2"] = 0,
      ["Rust_A_3"] = 0,
      ["Pinnacle_A_3"] = 0,
      ["Archive_B_1"] = 0,
      ["Pinnacle_B_3"] = 0,
      ["Detritus_B_1"] = 0,
      ["Pinnacle_B_1"] = 0,
      ["Global_Island_Mechs"] = 0,
      ["Global_Island_Building"] = 3,
    },
  },
  ["current"] = {
    ["score"] = 0,
    ["time"] = 1409995.625000,
    ["kills"] = 0,
    ["damage"] = 0,
    ["failures"] = 0,
    ["difficulty"] = 1,
    ["victory"] = false,
    ["squad"] = 0,
    ["mechs"] = { "PunchMech", "TankMech", "ArtiMech", },
    ["colors"] = { 0, 0, 0, },
    ["weapons"] = { "Prime_Punchmech", "", "Brute_Tankmech", "", "Ranged_Artillerymech", "", },
    ["pilot0"] = {
      ["id"] = "Pilot_Original",
      ["name"] = "Ralph Karlsson",
      ["skill1"] = 1,
      ["skill2"] = 3,
      ["exp"] = 0,
      ["level"] = 0,
      ["travel"] = 0,
      ["final"] = 0,
      ["starting"] = true,
    },
    ["pilot1"] = {
      ["id"] = "Pilot_Detritus",
      ["name"] = "Willow Kim",
      ["skill1"] = 2,
      ["skill2"] = 1,
      ["exp"] = 0,
      ["level"] = 0,
      ["travel"] = 0,
      ["final"] = 0,
      ["starting"] = true,
    },
    ["pilot2"] = {
      ["id"] = "Pilot_Pinnacle",
      ["name"] = "Calypso",
      ["skill1"] = 1,
      ["skill2"] = 2,
      ["exp"] = 0,
      ["level"] = 0,
      ["travel"] = 0,
      ["final"] = 0,
      ["starting"] = true,
    },
  },
  ["current_squad"] = 0,
}

RegionData = {
  ["sector"] = 0,
  ["island"] = 0,
  ["secret"] = false,

  ["island0"] = {
    ["corporation"] = "Corp_Grass",
    ["id"] = 0,
    ["secured"] = false,
  },
  ["island1"] = {
    ["corporation"] = "Corp_Desert",
    ["id"] = 1,
    ["secured"] = false,
  },
  ["island2"] = {
    ["corporation"] = "Corp_Snow",
    ["id"] = 2,
    ["secured"] = false,
  },
  ["island3"] = {
    ["corporation"] = "Corp_Factory",
    ["id"] = 3,
    ["secured"] = false,
  },

  ["turn"] = 0,
  ["iTower"] = 1,

  ["quest_tracker"] = 0,
  ["quest_id"] = 0,

  ["podRewards"] = {
    CreateEffect({ pilot = "random", cores = 1, }),
  },

  ["region0"] = {
    ["mission"] = "Mission1",
    ["state"] = 0,
    ["name"] = "Archival Flats",
  },

  ["region1"] = {
    ["mission"] = "",
    ["state"] = 2,
    ["name"] = "Corporate HQ",
    ["objectives"] = {},
  },

  ["region2"] = {
    ["mission"] = "Mission5",
    ["player"] = {
      ["battle_type"] = 0,
      ["iCurrentTurn"] = 2,
      ["iTeamTurn"] = 1,
      ["iState"] = 0,
      ["sMission"] = "Mission5",
      ["podReward"] = CreateEffect({}),
      ["secret"] = false,
      ["spawn_needed"] = false,
      ["env_time"] = 1000,
      ["actions"] = 0,
      ["iUndoTurn"] = 0,
      ["aiState"] = 3,
      ["aiDelay"] = 0.000000,
      ["aiSeed"] = 804279056,
      ["victory"] = 2,
      ["undo_pawns"] = {},
      ["map_data"] = {
        ["version"] = 7,
        ["dimensions"] = Point(8, 8),
        ["name"] = "train0",
        ["map"] = {
          {
            ["loc"] = Point(0, 1),
            ["terrain"] = 2,
            ["health_max"] = 1,
            ["health_min"] = 0,
            ["rubble_type"] = 0,
          },
          {
            ["loc"] = Point(0, 2),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 118,
            ["people2"] = 0,
            ["health_max"] = 1,
          },
          {
            ["loc"] = Point(0, 4),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(0, 7),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(1, 2),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(1, 4),
            ["terrain"] = 0,
            ["undo_state"] = {
              ["active"] = true,
            },
          },
          {
            ["loc"] = Point(1, 6),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 114,
            ["people2"] = 0,
            ["health_max"] = 1,
          },
          {
            ["loc"] = Point(1, 7),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 116,
            ["people2"] = 0,
            ["health_max"] = 1,
          },
          {
            ["loc"] = Point(2, 2),
            ["terrain"] = 0,
            ["undo_state"] = {
              ["active"] = true,
            },
          },
          {
            ["loc"] = Point(2, 3),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 228,
            ["people2"] = 0,
            ["health_max"] = 2,
          },
          {
            ["loc"] = Point(2, 4),
            ["terrain"] = 0,
            ["undo_state"] = {
              ["active"] = true,
            },
          },
          {
            ["loc"] = Point(2, 5),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(2, 6),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 169,
            ["people2"] = 0,
            ["health_max"] = 2,
          },
          {
            ["loc"] = Point(2, 7),
            ["terrain"] = 4,
          },
          {
            ["loc"] = Point(3, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(3, 5),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(3, 6),
            ["terrain"] = 4,
          },
          {
            ["loc"] = Point(3, 7),
            ["terrain"] = 4,
          },
          {
            ["loc"] = Point(4, 0),
            ["terrain"] = 0,
            ["custom"] = "ground_rail.png",
          },
          {
            ["loc"] = Point(4, 1),
            ["terrain"] = 0,
            ["custom"] = "ground_rail.png",
          },
          {
            ["loc"] = Point(4, 2),
            ["terrain"] = 0,
            ["custom"] = "ground_rail.png",
          },
          {
            ["loc"] = Point(4, 3),
            ["terrain"] = 0,
            ["custom"] = "ground_rail.png",
            ["grapple_targets"] = { 2, },
          },
          {
            ["loc"] = Point(4, 4),
            ["terrain"] = 0,
            ["custom"] = "ground_rail.png",
            ["grappled"] = 1,
          },
          {
            ["loc"] = Point(4, 5),
            ["terrain"] = 0,
            ["custom"] = "ground_rail.png",
          },
          {
            ["loc"] = Point(4, 6),
            ["terrain"] = 0,
            ["custom"] = "ground_rail.png",
          },
          {
            ["loc"] = Point(4, 7),
            ["terrain"] = 0,
            ["custom"] = "ground_rail.png",
          },
          {
            ["loc"] = Point(5, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(5, 2),
            ["terrain"] = 0,
          },
          {
            ["loc"] = Point(5, 3),
            ["terrain"] = 2,
            ["health_max"] = 2,
            ["health_min"] = 0,
            ["rubble_type"] = 0,
          },
          {
            ["loc"] = Point(5, 5),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(5, 6),
            ["terrain"] = 4,
          },
          {
            ["loc"] = Point(5, 7),
            ["terrain"] = 4,
          },
          {
            ["loc"] = Point(6, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(6, 1),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(6, 3),
            ["terrain"] = 0,
          },
          {
            ["loc"] = Point(6, 5),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(6, 6),
            ["terrain"] = 4,
          },
          {
            ["loc"] = Point(6, 7),
            ["terrain"] = 4,
          },
          {
            ["loc"] = Point(7, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(7, 1),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(7, 6),
            ["terrain"] = 4,
          },
          {
            ["loc"] = Point(7, 7),
            ["terrain"] = 4,
          },
        },
        ["pod"] = Point(5, 4),
        ["spawns"] = { "Hornet1", },
        ["spawn_ids"] = { 83, },
        ["spawn_points"] = { Point(5, 3), },
        ["zones"] = {},
        ["tags"] = { "train", "any_sector", },

        ["pawn1"] = {
          ["type"] = "PunchMech",
          ["name"] = "",
          ["id"] = 0,
          ["mech"] = true,
          ["offset"] = 0,
          ["reactor"] = {
            ["iNormalPower"] = 0,
            ["iUsedPower"] = 1,
            ["iBonusPower"] = 0,
            ["iUsedBonus"] = 0,
            ["iUndoPower"] = 0,
            ["iUsedUndo"] = 0,
          },

          ["movePower"] = { 0, },
          ["healthPower"] = { 0, },

          ["primary"] = "Prime_Punchmech",
          ["primary_power"] = { 1, },
          ["primary_power_class"] = false,
          ["primary_mod1"] = { 0, 0, },
          ["primary_mod2"] = { 0, 0, 0, },

          ["primary_uses"] = 1,
          ["primary_damaged"] = false,
          ["primary_starting"] = true,

          ["pilot"] = {
            ["id"] = "Pilot_Original",
            ["name"] = "Ralph Karlsson",
            ["skill1"] = 1,
            ["skill2"] = 3,
            ["exp"] = 0,
            ["level"] = 0,
            ["travel"] = 0,
            ["final"] = 0,
            ["starting"] = true,
          },

          ["iTeamId"] = 1,
          ["iFaction"] = 0,
          ["health"] = 3,
          ["max_health"] = 3,
          ["undo_state"] = {
            ["health"] = 5,
            ["max_health"] = 5,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(-1, -1),
          ["iMissionDamage"] = 0,
          ["location"] = Point(2, 2),
          ["last_location"] = Point(2, 2),
          ["bActive"] = true,
          ["iCurrentWeapon"] = 0,
          ["iTurnCount"] = 2,
          ["undoPosition"] = Point(-1, -1),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iOwner"] = 0,
          ["piTarget"] = Point(-1, -1),
          ["piOrigin"] = Point(-1, -1),
          ["piQueuedShot"] = Point(-1, -1),
          ["iQueuedSkill"] = -1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(-1, -1),
        },

        ["pawn2"] = {
          ["type"] = "TankMech",
          ["name"] = "",
          ["id"] = 1,
          ["mech"] = true,
          ["offset"] = 0,
          ["reactor"] = {
            ["iNormalPower"] = 0,
            ["iUsedPower"] = 1,
            ["iBonusPower"] = 0,
            ["iUsedBonus"] = 0,
            ["iUndoPower"] = 0,
            ["iUsedUndo"] = 0,
          },
          ["movePower"] = { 0, },
          ["healthPower"] = { 0, },
          ["primary"] = "Brute_Tankmech",
          ["primary_power"] = { 1, },
          ["primary_power_class"] = false,
          ["primary_mod1"] = { 0, 0, },
          ["primary_mod2"] = { 0, 0, 0, },
          ["primary_uses"] = 1,
          ["primary_damaged"] = false,
          ["primary_starting"] = true,
          ["pilot"] = {
            ["id"] = "Pilot_Detritus",
            ["name"] = "Willow Kim",
            ["skill1"] = 2,
            ["skill2"] = 1,
            ["exp"] = 0,
            ["level"] = 0,
            ["travel"] = 0,
            ["final"] = 0,
            ["starting"] = true,
          },
          ["iTeamId"] = 1,
          ["iFaction"] = 0,
          ["health"] = 3,
          ["max_health"] = 3,
          ["undo_state"] = {
            ["health"] = 5,
            ["max_health"] = 5,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(-1, -1),
          ["iMissionDamage"] = 0,
          ["location"] = Point(2, 4),
          ["last_location"] = Point(2, 4),
          ["bActive"] = true,
          ["iCurrentWeapon"] = 0,
          ["iTurnCount"] = 2,
          ["undoPosition"] = Point(-1, -1),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iOwner"] = 1,
          ["piTarget"] = Point(2, 4),
          ["piOrigin"] = Point(2, 4),
          ["piQueuedShot"] = Point(-1, -1),
          ["iQueuedSkill"] = -1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(-1, -1),
        },

        ["pawn3"] = {
          ["type"] = "ArtiMech",
          ["name"] = "",
          ["id"] = 2,
          ["mech"] = true,
          ["offset"] = 0,
          ["reactor"] = {
            ["iNormalPower"] = 0,
            ["iUsedPower"] = 1,
            ["iBonusPower"] = 0,
            ["iUsedBonus"] = 0,
            ["iUndoPower"] = 0,
            ["iUsedUndo"] = 0,
          },
          ["movePower"] = { 0, },
          ["healthPower"] = { 0, },
          ["primary"] = "Ranged_Artillerymech",
          ["primary_power"] = { 1, },
          ["primary_power_class"] = false,
          ["primary_mod1"] = { 0, },
          ["primary_mod2"] = { 0, 0, 0, },
          ["primary_uses"] = 1,
          ["primary_damaged"] = false,
          ["primary_starting"] = true,
          ["pilot"] = {
            ["id"] = "Pilot_Pinnacle",
            ["name"] = "Calypso",
            ["skill1"] = 1,
            ["skill2"] = 2,
            ["exp"] = 0,
            ["level"] = 0,
            ["travel"] = 0,
            ["final"] = 0,
            ["starting"] = true,
          },
          ["iTeamId"] = 1,
          ["iFaction"] = 0,
          ["health"] = 2,
          ["max_health"] = 2,
          ["undo_state"] = {
            ["health"] = 2,
            ["max_health"] = 2,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(1, 4),
          ["iMissionDamage"] = 0,
          ["location"] = Point(1, 4),
          ["last_location"] = Point(2, 4),
          ["bActive"] = true,
          ["iCurrentWeapon"] = 0,
          ["iTurnCount"] = 2,
          ["undoPosition"] = Point(1, 4),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iOwner"] = 2,
          ["piTarget"] = Point(2, 4),
          ["piOrigin"] = Point(1, 4),
          ["piQueuedShot"] = Point(-1, -1),
          ["iQueuedSkill"] = -1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(-1, -1),
        },

        ["pawn4"] = {
          ["type"] = "Firefly1",
          ["name"] = "",
          ["id"] = 63,
          ["mech"] = false,
          ["offset"] = 0,
          ["primary"] = "FireflyAtk1",
          ["iTeamId"] = 6,
          ["iFaction"] = 0,
          ["health"] = 4,
          ["max_health"] = 4,
          ["undo_state"] = {
            ["health"] = 5,
            ["max_health"] = 5,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(-1, -1),
          ["iMissionDamage"] = 0,
          ["location"] = Point(5, 2),
          ["last_location"] = Point(5, 1),
          ["iCurrentWeapon"] = 1,
          ["iTurnCount"] = 2,
          ["undoPosition"] = Point(-1, -1),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iMutation"] = 1,
          ["iOwner"] = 63,
          ["piTarget"] = Point(4, 2),
          ["piOrigin"] = Point(5, 2),
          ["piQueuedShot"] = Point(4, 2),
          ["iQueuedSkill"] = 1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(4, 2),
        },

        ["pawn5"] = {
          ["type"] = "Hornet1",
          ["name"] = "",
          ["id"] = 64,
          ["mech"] = false,
          ["offset"] = 0,
          ["primary"] = "HornetAtk1",
          ["iTeamId"] = 6,
          ["iFaction"] = 0,
          ["health"] = 3,
          ["max_health"] = 3,
          ["undo_state"] = {
            ["health"] = 5,
            ["max_health"] = 5,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(-1, -1),
          ["iMissionDamage"] = 0,
          ["location"] = Point(4, 6),
          ["last_location"] = Point(4, 5),
          ["iCurrentWeapon"] = 1,
          ["iTurnCount"] = 2,
          ["undoPosition"] = Point(-1, -1),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iMutation"] = 1,
          ["iOwner"] = 64,
          ["piTarget"] = Point(4, 5),
          ["piOrigin"] = Point(4, 6),
          ["piQueuedShot"] = Point(4, 5),
          ["iQueuedSkill"] = 1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(4, 5),
        },

        ["pawn6"] = {
          ["type"] = "Scorpion1",
          ["name"] = "",
          ["id"] = 65,
          ["mech"] = false,
          ["offset"] = 0,
          ["primary"] = "ScorpionAtk1",
          ["iTeamId"] = 6,
          ["iFaction"] = 0,
          ["health"] = 4,
          ["max_health"] = 4,
          ["undo_state"] = {
            ["health"] = 5,
            ["max_health"] = 5,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(-1, -1),
          ["iMissionDamage"] = 0,
          ["location"] = Point(4, 3),
          ["last_location"] = Point(5, 3),
          ["iCurrentWeapon"] = 1,
          ["iTurnCount"] = 2,
          ["undoPosition"] = Point(-1, -1),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iMutation"] = 1,
          ["iOwner"] = 65,
          ["piTarget"] = Point(4, 4),
          ["piOrigin"] = Point(4, 3),
          ["piQueuedShot"] = Point(4, 4),
          ["iQueuedSkill"] = 1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(4, 4),
        },

        ["pawn7"] = {
          ["type"] = "Hornet1",
          ["name"] = "",
          ["id"] = 69,
          ["mech"] = false,
          ["offset"] = 0,
          ["primary"] = "HornetAtk1",
          ["iTeamId"] = 6,
          ["iFaction"] = 0,
          ["health"] = 3,
          ["max_health"] = 3,
          ["undo_state"] = {
            ["health"] = 5,
            ["max_health"] = 5,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(-1, -1),
          ["iMissionDamage"] = 0,
          ["location"] = Point(3, 5),
          ["last_location"] = Point(3, 4),
          ["iCurrentWeapon"] = 1,
          ["iTurnCount"] = 0,
          ["undoPosition"] = Point(-1, -1),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iMutation"] = 1,
          ["iOwner"] = 69,
          ["piTarget"] = Point(4, 5),
          ["piOrigin"] = Point(3, 5),
          ["piQueuedShot"] = Point(4, 5),
          ["iQueuedSkill"] = 1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(4, 5),
        },

        ["pawn8"] = {
          ["type"] = "Jelly_Health1",
          ["name"] = "",
          ["id"] = 70,
          ["mech"] = false,
          ["offset"] = 4,
          ["not_attacking"] = true,
          ["iTeamId"] = 6,
          ["iFaction"] = 0,
          ["health"] = 2,
          ["max_health"] = 2,
          ["undo_state"] = {
            ["health"] = 5,
            ["max_health"] = 5,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(-1, -1),
          ["iMissionDamage"] = 0,
          ["location"] = Point(6, 3),
          ["last_location"] = Point(6, 2),
          ["iCurrentWeapon"] = 1,
          ["iTurnCount"] = 0,
          ["undoPosition"] = Point(-1, -1),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iOwner"] = 70,
          ["piTarget"] = Point(-2147483647, -2147483647),
          ["piOrigin"] = Point(7, 2),
          ["piQueuedShot"] = Point(-1, -1),
          ["iQueuedSkill"] = -1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(-1, -1),
        },

        ["pawn9"] = {
          ["type"] = "Train_Pawn",
          ["name"] = "",
          ["id"] = 62,
          ["mech"] = false,
          ["offset"] = 0,
          ["primary"] = "Train_Move",
          ["pilot"] = {
            ["id"] = "Pilot_Archive",
            ["name"] = "Philip Acharya",
            ["skill1"] = 0,
            ["skill2"] = 1,
            ["exp"] = 0,
            ["level"] = 0,
            ["travel"] = 0,
            ["final"] = 0,
            ["starting"] = false,
          },
          ["iTeamId"] = 1,
          ["iFaction"] = 0,
          ["health"] = 1,
          ["max_health"] = 1,
          ["undo_state"] = {
            ["health"] = 5,
            ["max_health"] = 5,
          },
          ["undo_ready"] = false,
          ["undo_point"] = Point(-1, -1),
          ["iMissionDamage"] = 0,
          ["location"] = Point(4, 4),
          ["last_location"] = Point(4, 4),
          ["iCurrentWeapon"] = 1,
          ["iTurnCount"] = 2,
          ["undoPosition"] = Point(-1, -1),
          ["undoReady"] = false,
          ["iKillCount"] = 0,
          ["iOwner"] = 62,
          ["piTarget"] = Point(4, 3),
          ["piOrigin"] = Point(4, 4),
          ["piQueuedShot"] = Point(4, 3),
          ["iQueuedSkill"] = 1,
          ["priorityTarget"] = Point(-1, -1),
          ["targetHistory"] = Point(4, 3),
        },
        ["pawn_count"] = 9,
        ["blocked_points"] = {},
        ["blocked_type"] = {},
      },
    },
    ["state"] = 1,
    ["name"] = "Antiquity Row",
  },

  ["region3"] = {
    ["mission"] = "Mission6",
    ["player"] = {
      ["battle_type"] = 0,
      ["iCurrentTurn"] = 0,
      ["iTeamTurn"] = 1,
      ["iState"] = 4,
      ["sMission"] = "Mission6",
      ["podReward"] = CreateEffect({}),
      ["secret"] = false,
      ["spawn_needed"] = false,
      ["env_time"] = 1000,
      ["actions"] = 0,
      ["iUndoTurn"] = 1,
      ["aiState"] = 0,
      ["aiDelay"] = 0.000000,
      ["aiSeed"] = 1512218077,
      ["victory"] = 2,
      ["undo_pawns"] = {},
      ["map_data"] = {
        ["version"] = 7,
        ["dimensions"] = Point(8, 8),
        ["name"] = "mix2",
        ["map"] = {
          {
            ["loc"] = Point(0, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(0, 1),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(0, 2),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 112,
            ["people2"] = 0,
            ["health_max"] = 1,
          },
          {
            ["loc"] = Point(0, 3),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 73,
            ["people2"] = 0,
            ["health_max"] = 1,
          },
          {
            ["loc"] = Point(0, 4),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 114,
            ["people2"] = 0,
            ["health_max"] = 1,
          },
          {
            ["loc"] = Point(0, 5),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 107,
            ["people2"] = 0,
            ["health_max"] = 1,
          },
          {
            ["loc"] = Point(0, 6),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 185,
            ["people2"] = 0,
            ["health_max"] = 2,
          },
          {
            ["loc"] = Point(1, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(1, 3),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(2, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(2, 4),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(2, 7),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(3, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(3, 1),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(3, 2),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["unique"] = "str_power1",
            ["people1"] = 77,
            ["people2"] = 0,
            ["health_max"] = 1,
          },
          {
            ["loc"] = Point(3, 4),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 163,
            ["people2"] = 0,
            ["health_max"] = 2,
          },
          {
            ["loc"] = Point(3, 6),
            ["terrain"] = 1,
            ["populated"] = 1,
            ["people1"] = 169,
            ["people2"] = 0,
            ["health_max"] = 2,
          },
          {
            ["loc"] = Point(4, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(4, 2),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(4, 4),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(4, 6),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(5, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(5, 2),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(5, 3),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(6, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(6, 1),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(6, 3),
            ["terrain"] = 6,
          },
          {
            ["loc"] = Point(7, 0),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(7, 2),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(7, 4),
            ["terrain"] = 3,
          },
          {
            ["loc"] = Point(7, 6),
            ["terrain"] = 3,
          },
        },
        ["spawns"] = { "Scorpion1", "Scorpion1", "Jelly_Health1", },
        ["spawn_ids"] = { 66, 67, 68, },
        ["spawn_points"] = { Point(6, 2), Point(6, 4), Point(5, 5), },
        ["zones"] = {},
        ["tags"] = { "generic", "grass", "snow", "acid", },
        ["pawn_count"] = 0,
        ["blocked_points"] = {},
        ["blocked_type"] = {},
      },


    },
    ["state"] = 1,
    ["name"] = "Chronology Hall",
  },

  ["region4"] = {
    ["mission"] = "Mission3",
    ["state"] = 0,
    ["name"] = "Remembrance Point",
  },

  ["region5"] = {
    ["mission"] = "Mission7",
    ["state"] = 0,
    ["name"] = "The Pasture",
  },

  ["region6"] = {
    ["mission"] = "Mission2",
    ["state"] = 0,
    ["name"] = "Old Town",
  },

  ["region7"] = {
    ["mission"] = "Mission4",
    ["state"] = 0,
    ["name"] = "Central Museums",
  },
  ["iBattleRegion"] = 2,
}

GAME = {
  ["SeenPilots"] = {
    [1] = "Pilot_Original",
    [2] = "Pilot_Detritus",
    [4] = "Pilot_Repairman",
    [3] = "Pilot_Pinnacle"
  },

  ["Island"] = 1,

  ["WeaponDeck"] = {
    [31] = "Ranged_ScatterShot",
    [2] = "Prime_Lasermech",
    [8] = "Prime_Areablast",
    [32] = "Ranged_BackShot",
    [33] = "Ranged_SmokeBlast",
    [34] = "Ranged_RainingVolley",
    [35] = "Ranged_Wide",
    [9] = "Prime_Spear",
    [36] = "Ranged_Dual",
    [37] = "Science_Pullmech",
    [38] = "Science_Gravwell",
    [39] = "Science_Swap",
    [10] = "Prime_Leap",
    [40] = "Science_Repulse",
    [41] = "Science_AcidShot",
    [42] = "Science_Confuse",
    [43] = "Science_SmokeDefense",
    [11] = "Prime_SpinFist",
    [44] = "Science_Shield",
    [45] = "Science_FireBeam",
    [46] = "Science_FreezeBeam",
    [3] = "Prime_ShieldBash",
    [12] = "Prime_Sword",
    [48] = "Science_PushBeam",
    [49] = "Support_Boosters",
    [50] = "Support_Smoke",
    [51] = "Support_Refrigerate",
    [13] = "Prime_Smash",
    [52] = "DeploySkill_ShieldTank",
    [53] = "DeploySkill_Tank",
    [54] = "DeploySkill_AcidTank",
    [55] = "DeploySkill_PullTank",
    [14] = "Brute_Jetmech",
    [56] = "Support_Force",
    [57] = "Support_SmokeDrop",
    [58] = "Support_Repair",
    [59] = "Support_Missiles",
    [15] = "Brute_Mirrorshot",
    [60] = "Support_Wind",
    [61] = "Support_Blizzard",
    [62] = "Passive_FlameImmune",
    [1] = "Prime_Lightning",
    [4] = "Prime_Rockmech",
    [16] = "Brute_PhaseShot",
    [64] = "Passive_Leech",
    [65] = "Passive_MassRepair",
    [66] = "Passive_Defenses",
    [17] = "Brute_Grapple",
    [68] = "Passive_AutoShields",
    [69] = "Passive_Psions",
    [70] = "Passive_Boosters",
    [18] = "Brute_Shrapnel",
    [72] = "Passive_ForceAmp",
    [73] = "Passive_CritDefense",
    [19] = "Brute_Sniper",
    [5] = "Prime_RocketPunch",
    [20] = "Brute_Shockblast",
    [21] = "Brute_Beetle",
    [22] = "Brute_Unstable",
    [23] = "Brute_Heavyrocket",
    [6] = "Prime_Shift",
    [24] = "Brute_Splitshot",
    [25] = "Brute_Bombrun",
    [26] = "Brute_Sonic",
    [27] = "Ranged_Rockthrow",
    [7] = "Prime_Flamethrower",
    [28] = "Ranged_Defensestrike",
    [29] = "Ranged_Rocket",
    [71] = "Passive_Medical",
    [67] = "Passive_Burrows",
    [30] = "Ranged_Ignite",
    [63] = "Passive_Electric",
    [47] = "Science_LocalShield"
  },

  ["Missions"] = {
    [6] = {
      ["BonusObjs"] = {
        [1] = 6,
        [2] = 1
      },
      ["SpawnsPerTurn"] = {
        [1] = 2,
        [2] = 3
      },
      ["SpawnStart"] = {
        [1] = 3,
        [2] = 3,
        [4] = 4,
        [3] = 4
      },
      ["InfiniteSpawn"] = true,
      ["SpawnsPerTurn_Easy"] = {
        [1] = 2,
        [2] = 1
      },
      ["Spawner"] = {
        ["curr_weakRatio"] = {
          [1] = 2,
          [2] = 2
        },
        ["pawn_counts"] = {
          ["Jelly_Health"] = 1,
          ["Scorpion"] = 2
        },
        ["curr_upgradeRatio"] = {
          [1] = 0,
          [2] = 2
        },
        ["num_spawns"] = 3,
        ["upgrade_streak"] = 0
      },
      ["LiveEnvironment"] = {
        ["Locations"] = { },
        ["Planned"] = { }
      },
      ["AssetId"] = "Str_Power",
      ["AssetLoc"] = Point(3, 2),
      ["ID"] = "Mission_Airstrike",
      ["VoiceEvents"] = { },
      ["BonusPool"] = {
        [1] = 3,
        [2] = 4,
        [4] = 6,
        [3] = 5
      },
      ["SpawnStart_Easy"] = {
        [1] = 2,
        [2] = 2,
        [4] = 3,
        [3] = 3
      }
    },
    [2] = {
      ["Spawner"] = { },
      ["AssetId"] = "Str_Battery",
      ["ID"] = "Mission_Mines",
      ["LiveEnvironment"] = { },
      ["BonusObjs"] = {
        [1] = 6,
        [2] = 1
      }
    },
    [8] = {
      ["Spawner"] = { },
      ["AssetId"] = "Str_Tower",
      ["ID"] = "Mission_HornetBoss",
      ["LiveEnvironment"] = { },
      ["BonusObjs"] = {
        [1] = 1
      }
    },
    [3] = {
      ["BonusObjs"] = {
        [1] = 1
      },
      ["SpawnStart"] = {
        [1] = 3,
        [2] = 3,
        [4] = 4,
        [3] = 4
      },
      ["InfiniteSpawn"] = true,
      ["SpawnsPerTurn_Easy"] = {
        [1] = 2,
        [2] = 1
      },
      ["Spawner"] = { },
      ["AssetId"] = "Str_Nimbus",
      ["LiveEnvironment"] = { },
      ["ID"] = "Mission_Volatile",
      ["SpawnsPerTurn"] = {
        [1] = 2,
        [2] = 3
      },
      ["BonusPool"] = {
        [1] = 3,
        [2] = 4,
        [4] = 6,
        [3] = 5
      },
      ["SpawnStart_Easy"] = {
        [1] = 2,
        [2] = 2,
        [4] = 3,
        [3] = 3
      }
    },
    [1] = {
      ["ID"] = "Mission_Dam",
      ["BonusObjs"] = {
        [1] = 4
      },
      ["LiveEnvironment"] = { },
      ["Spawner"] = {
      }
    },
    [4] = {
      ["Spawner"] = { },
      ["AssetId"] = "Str_Power",
      ["ID"] = "Mission_Survive",
      ["LiveEnvironment"] = { },
      ["DiffMod"] = 1,
      ["BonusObjs"] = {
        [1] = 1
      }
    },
    [5] = {
      ["Train"] = 62,
      ["LiveEnvironment"] = { },
      ["BonusObjs"] = { },
      ["TrainLoc"] = Point(4, 4),
      ["ID"] = "Mission_Train",
      ["VoiceEvents"] = { },
      ["Spawner"] = {
        ["curr_weakRatio"] = {
          [1] = 3,
          [2] = 4
        },
        ["pawn_counts"] = {
          ["Firefly"] = 1,
          ["Hornet"] = 3,
          ["Scorpion"] = 1,
          ["Jelly_Health"] = 1
        },
        ["curr_upgradeRatio"] = {
          [1] = 0,
          [2] = 4
        },
        ["num_spawns"] = 6,
        ["upgrade_streak"] = 0
      },
      ["PowerStart"] = 5
    },

    [7] = {
      ["ID"] = "Mission_Tanks",
      ["BonusObjs"] = { },
      ["LiveEnvironment"] = { },
      ["Spawner"] = { }
    }
  },

  ["Bosses"] = {
    [1] = "Mission_HornetBoss",
    [2] = "Mission_FireflyBoss",
    [4] = "Mission_SpiderBoss",
    [3] = "Mission_BeetleBoss"
  },

  ["PodDeck"] = {
    [7] = {
      ["cores"] = 1,
      ["weapon"] = "random"
    },
    [1] = {
      ["cores"] = 1
    },
    [2] = {
      ["cores"] = 1
    },
    [4] = {
      ["cores"] = 1,
      ["weapon"] = "random"
    },
    [8] = {
      ["cores"] = 1,
      ["pilot"] = "random"
    },
    [9] = {
      ["cores"] = 1,
      ["pilot"] = "random"
    },
    [5] = {
      ["cores"] = 1,
      ["weapon"] = "random"
    },
    [3] = {
      ["cores"] = 1
    },
    [6] = {
      ["cores"] = 1,
      ["weapon"] = "random"
    }
  },

  ["PilotDeck"] = {
    [7] = "Pilot_Genius",
    [1] = "Pilot_Soldier",
    [2] = "Pilot_Youth",
    [4] = "Pilot_Aquatic",
    [8] = "Pilot_Miner",
    [9] = "Pilot_Recycler",
    [5] = "Pilot_Medic",
    [10] = "Pilot_Assassin",
    [3] = "Pilot_Warrior",
    [6] = "Pilot_Hotshot",
    [11] = "Pilot_Leader"
  },

  ["Enemies"] = {
    [1] = {
      [6] = "Crab",
      [2] = "Hornet",
      [3] = "Firefly",
      [1] = "Scorpion",
      [4] = "Jelly_Health",
      [5] = "Blobber",
      ["island"] = 1
    },
    [2] = {
      [6] = "Blobber",
      [2] = "Scorpion",
      [3] = "Hornet",
      [1] = "Scarab",
      [4] = "Jelly_Explode",
      [5] = "Beetle",
      ["island"] = 2
    },
    [4] = {
      [6] = "Beetle",
      [2] = "Firefly",
      [3] = "Hornet",
      [1] = "Scarab",
      [4] = "Jelly_Regen",
      [5] = "Spider",
      ["island"] = 4
    },
    [3] = {
      [6] = "Centipede",
      [2] = "Firefly",
      [3] = "Leaper",
      [1] = "Scarab",
      [4] = "Jelly_Armor",
      [5] = "Digger",
      ["island"] = 3
    }
  }
}

SquadData = {
  ["money"] = 0,
  ["cores"] = 0,
  ["bIsFavor"] = false,
  ["repairs"] = 0,
  ["CorpReward"] = {
    CreateEffect({ weapon = "Passive_FriendlyFire", }),
    CreateEffect({ pilot = "Pilot_Repairman", }),
    CreateEffect({ power = 2, }),
  },
  ["RewardClaimed"] = false,
  ["skip_pawns"] = true,
  ["storage_size"] = 3,
  ["CorpStore"] = {
    CreateEffect({ weapon = "Support_Destruct", money = -2, }),
    CreateEffect({ weapon = "Prime_RightHook", money = -1, }),
    CreateEffect({ weapon = "Ranged_Fireball", money = -2, }),
    CreateEffect({ weapon = "Ranged_Ice", money = -2, }),
    CreateEffect({ money = -3, stock = -1, cores = 1, }),
    CreateEffect({ money = -1, power = 1, stock = -1, }),
  },
}
