package com.example.sportsbuddy

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel : ViewModel() {
    private val _id = MutableStateFlow("")
    val id: StateFlow<String> get() = _id

    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> get() = _nickname

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm: StateFlow<String> get() = _passwordConfirm

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> get() = _gender

    private val _birthDate = MutableStateFlow("")
    val birthDate: StateFlow<String> get() = _birthDate

    private val _selectedInterests = MutableStateFlow(listOf<String>())
    val selectedInterests: StateFlow<List<String>> get() = _selectedInterests

    fun onIdChange(newId: String) {
        _id.value = newId
    }

    fun onNicknameChange(newNickname: String) {
        _nickname.value = newNickname
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onPasswordConfirmChange(newPasswordConfirm: String) {
        _passwordConfirm.value = newPasswordConfirm
    }

    fun onGenderChange(newGender: String) {
        _gender.value = newGender
    }

    fun onBirthDateChange(newBirthDate: String) {
        _birthDate.value = newBirthDate
    }

    fun onInterestsChange(newInterests: List<String>) {
        _selectedInterests.value = newInterests
    }

    fun signUp(navController: NavController, context: Context) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users").child(_id.value)

        val userInfo = mapOf(
            "id" to _id.value,
            "nickname" to _nickname.value,
            "password" to _password.value,
            "gender" to _gender.value,
            "birthDate" to _birthDate.value,
            "selectedInterests" to _selectedInterests.value
        )

        usersRef.setValue(userInfo).addOnCompleteListener {
            if (it.isSuccessful) {
                navController.popBackStack()
                Toast.makeText(context, "성공적으로 가입되었습니다!", Toast.LENGTH_SHORT).show()
                navController.navigate("login")
            } else {
                Toast.makeText(context, "회원가입 실패: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signIn(id: String, password: String, context: Context, navController: NavController) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users").child(id)

        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val storedPassword = snapshot.child("password").getValue(String::class.java)
                if (storedPassword == password) {
                    navController.popBackStack()
                    Toast.makeText(context, "성공적으로 로그인되었습니다!", Toast.LENGTH_SHORT).show()
                    navController.navigate("navigation_bar")
                } else {
                    Toast.makeText(context, "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
