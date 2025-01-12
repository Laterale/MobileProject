package com.example.partyapp.ui

enum class LevelThreshold(val requiredXp: Int, val level: Int) {
    LV_0(0, 0),
    LV_1(100, 1),
    LV_2(250, 2),
    LV_3(450, 3),
    LV_4(750, 4),
    LV_5(1100, 5),
    LV_6(1500, 6),
    LV_7(2000, 7),
    LV_8(3000, 8),
    LV_9(4500, 9),
    LV_10(7000, 10);

    companion object {
        fun getLevelForXp(xp: Long): LevelThreshold {
            // Iterate through the levels in reverse order (highest to lowest)
            for (level in values().reversed()) {
                if (xp >= level.requiredXp) {
                    return level
                }
            }
            return LV_0
        }

        fun getPercentageToNextLevel(xp: Long): Float {
            val currentLevel = getLevelForXp(xp)
            val nextLevel = values()[currentLevel.ordinal + 1]
            val deltaXp = (nextLevel.requiredXp - currentLevel.requiredXp).toFloat()
            val reducedXp = (xp - currentLevel.requiredXp).toFloat()
            // d : 100 = r : x
            return reducedXp / deltaXp
        }
    }
}
