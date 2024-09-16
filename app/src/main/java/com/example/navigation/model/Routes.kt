package com.example.navigation.model

sealed class Routes(val route:String) {
    object Pantalla1: Routes("screen1")
    object Pantalla2: Routes("screen2")
    object Pantalla3: Routes("screen3")
    object Pantalla4: Routes("screen4/{name}"){
        fun createRoute(name:String) = "screen4/$name"
    }
    object Pantalla5: Routes("screen5?age={age}"){
        fun createRoute(age:Int) = "screen5?age=$age"
    }
}