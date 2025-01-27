package com.example.firebaseapp

sealed class ekran (val droga: String){
    object  ekran1 : ekran("ekran1")
    object  ekran2 : ekran("ekran2")
    object  ekran3 : ekran("ekran3")

}