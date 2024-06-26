package com.example.sportsbuddy.data

data class User(
    var id: String = "",
    var nickname: String = "",
    var password: String = "",
    var passwordConfirm: String = "",
    var gender: String = "",
    var birthDate: String = "",
    var selectedInterests: List<String> = emptyList(),
    var selectedCity: String = "",
    var selectedDistrict: String = "",
    var selectedNeighborhood: String = ""
)
