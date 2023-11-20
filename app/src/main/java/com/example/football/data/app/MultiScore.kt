package com.example.football.data.app

data class MultiScore(
    //val number: Int,
    val menuDropDown: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MultiScore

        if (!menuDropDown.contentEquals(other.menuDropDown)) return false

        return true
    }

    override fun hashCode(): Int {
        return menuDropDown.contentHashCode()
    }

}
