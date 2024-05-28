package com.example.sportsbuddy.data

//val cities = listOf("서울", "부산", "인천")
//val districts = listOf(
//    listOf("강남구", "서초구", "송파구"),
//    listOf("해운대구", "부산진구", "동래구"),
//    listOf("남구", "연수구", "서구")
//)
//val neighborhoods = listOf(
//    listOf(
//        listOf("삼성동", "역삼동", "청담동"),
//        listOf("서초동", "반포동", "잠원동"),
//        listOf("잠실동", "가락동", "신천동")
//    ),
//    listOf(
//        listOf("재송동", "반여동", "우동"),
//        listOf("부전동", "양정동", "범천동"),
//        listOf("수안동", "명장동", "사직동")
//    ),
//    listOf(
//        listOf("주안동", "도화동", "관교동"),
//        listOf("송도동", "청학동", "연희동"),
//        listOf("검단동", "청라동", "마전동")
//    )
//)

val cities = listOf(
    City("서울", listOf(
        District("강남구", listOf(
            Neighborhood("역삼동"),
            Neighborhood("논현동"),
        )),
        District("마포구", listOf(
            Neighborhood("합정동"),
            Neighborhood("서교동")
        ))
    )),
    City("부산", listOf(
        District("해운대구", listOf(
            Neighborhood("우동"),
            Neighborhood("중동")
        )),
        District("동래구", listOf(
            Neighborhood("온천동"),
            Neighborhood("명륜동")
        ))
    ))
)

data class City(
    val name: String,
    val districts: List<District>
)

data class District(
    val name: String,
    val neighborhoods: List<Neighborhood>
)

data class Neighborhood(
    val name: String
)
