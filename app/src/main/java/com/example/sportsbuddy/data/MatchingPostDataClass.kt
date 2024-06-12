package com.example.sportsbuddy.data

data class MatchingPost(
    var title: String = "",
    var sport: String = "",
    var career: String = "",
    var people: String = "1",
    var selectedCity: String = "",
    var selectedDistrict: String = "",
    var selectedNeighborhood: String = "",
    var time: String = "",
    var detail: String = ""
)