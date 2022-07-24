package com.gabo.rvwithsearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val char_id: Int,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val appearance: List<String>,
    val nickname: String,
    val portrayed: String
) : Parcelable
