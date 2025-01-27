package com.example.firebaseapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.NonNull
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.firebaseapp.ui.theme.FireBaseAppTheme
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : ComponentActivity() {



    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FireBaseAppTheme {
                Nawigacja()




                }
            }
        }
    }
@Composable
fun Nawigacja(){
    val kontroler = rememberNavController()
    NavHost(navController = kontroler, startDestination = ekran.ekran1.droga){
        composable ( route = ekran.ekran1.droga){
            ekran1(navController = kontroler)
        }
        composable (route = ekran.ekran2.droga){

            ekran2(navController = kontroler)
        }
        composable (route = ekran.ekran3.droga){

            ekran3(navController = kontroler)
        }

    }
}
@Composable

fun ekran1(navController: NavController){
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var Email by remember {
        mutableStateOf("")
    }
    var haslo by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray))

    {

        Text(text = "Logowanie", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        TextField(

            value = Email,
            label = {
                Text(
                    text = "Email")
            },
            onValueChange = {
                Email = it
            },
            singleLine = true,

            modifier = Modifier

                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(

            value = haslo,
            label = {
                Text(
                    text = "Hasło")
            },
            onValueChange = {
                haslo = it
            },
            singleLine = true,

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)

        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {

            val emails = Email.toString()
            val haslos = haslo.toString()
            if(TextUtils.isEmpty(emails)){
                Toast.makeText(context, "wpisz email", Toast.LENGTH_SHORT).show()
            }
            else{
                if(TextUtils.isEmpty(haslos)){
                    Toast.makeText(context, "wpisz haslo", Toast.LENGTH_SHORT).show()
                }
                else{
                    auth.signInWithEmailAndPassword(Email, haslo)
                        .addOnCompleteListener{task ->
                            if (task.isSuccessful){
                                navController.navigate(ekran.ekran3.droga)
                            }
                            else{
                                Toast.makeText(context, "coś poszło nie tak", Toast.LENGTH_SHORT).show()
                            }
                        }


                }
            }

        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xffff5c00)
        )) {
            Text(text ="Zaloguj")
        }
        Button(onClick = {

            navController.navigate(ekran.ekran2.droga)


        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xffff5c00)
        )) {
            Text(text ="Zarejestruj")
        }
    }

}

@Composable

fun ekran2(navController: NavController) {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var Email by remember {
        mutableStateOf("")
    }
    var haslo by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    )

    {

        Text(
            text = "Rejestracja",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(

            value = Email,
            label = {
                Text(
                    text = "Email"
                )
            },
            onValueChange = {
                Email = it
            },
            singleLine = true,

            modifier = Modifier

                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(

            value = haslo,
            label = {
                Text(
                    text = "Hasło"
                )
            },
            onValueChange = {
                haslo = it
            },
            singleLine = true,

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)

        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

                val emails = Email.toString()
                val haslos = haslo.toString()
                if (TextUtils.isEmpty(emails)) {
                    Toast.makeText(context, "wpisz email", Toast.LENGTH_SHORT).show()
                } else {
                    if (TextUtils.isEmpty(haslos)) {
                        Toast.makeText(context, "wpisz haslo", Toast.LENGTH_SHORT).show()
                    } else {
                        auth.createUserWithEmailAndPassword(Email, haslo)
                            .addOnCompleteListener{ task ->
                                if (task.isSuccessful) {


                                    val user = auth.currentUser
                                    navController.navigate(ekran.ekran1.droga)
                                } else {


                                    Toast.makeText(
                                        context,
                                        "Rejestracja nieudana.",
                                        Toast.LENGTH_SHORT,
                                    ).show()

                                }
                            }




                    }
                }

            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffff5c00)
            )
        ) {
            Text(text = "Zarejestruj")
        }
    }

}



@Composable
fun ekran3(navController: NavController){


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)){



        Text(
            text = "Logowanie udane",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )


    }
}


