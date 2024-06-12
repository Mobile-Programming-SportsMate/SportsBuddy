package com.example.sportsbuddy

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.sportsbuddy.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> get() = _user

    fun onIdChange(newId: String) {
        _user.value = _user.value.copy(id = newId)
    }

    fun onNicknameChange(newNickname: String) {
        _user.value = _user.value.copy(nickname = newNickname)
    }

    fun onPasswordChange(newPassword: String) {
        _user.value = _user.value.copy(password = newPassword)
    }

    fun onPasswordConfirmChange(newPasswordConfirm: String) {
        _user.value = _user.value.copy(passwordConfirm = newPasswordConfirm)
    }

    fun onGenderChange(newGender: String) {
        _user.value = _user.value.copy(gender = newGender)
    }

    fun onBirthDateChange(newBirthDate: String) {
        _user.value = _user.value.copy(birthDate = newBirthDate)
    }

    fun onInterestsChange(newInterests: List<String>) {
        _user.value = _user.value.copy(selectedInterests = newInterests)
    }

    fun onCityChange(newCity: String) {
        _user.value = _user.value.copy(selectedCity = newCity)
    }

    fun onDistrictChange(newDistrict: String) {
        _user.value = _user.value.copy(selectedDistrict = newDistrict)
    }

    fun onNeighborhoodChange(newNeighborhood: String) {
        _user.value = _user.value.copy(selectedNeighborhood = newNeighborhood)
    }


    // Functions to update MatchingPost details
    fun onMatchingPostTitleChange(newTitle: String) {
        _user.value =
            _user.value.copy(matchingPost = _user.value.matchingPost.copy(title = newTitle))
    }

    fun onMatchingPostSportChange(newSport: String) {
        _user.value =
            _user.value.copy(matchingPost = _user.value.matchingPost.copy(sport = newSport))
    }

    fun onMatchingPostCareerChange(newCareer: String) {
        _user.value =
            _user.value.copy(matchingPost = _user.value.matchingPost.copy(career = newCareer))
    }
    fun onMatchingPostPeopleChange(newPeople: String) {
        _user.value =
            _user.value.copy(matchingPost = _user.value.matchingPost.copy(people = newPeople))
    }

    fun onMatchingPostCityChange(newCity: String) {
        _user.value =
            _user.value.copy(matchingPost = _user.value.matchingPost.copy(selectedCity = newCity))
    }

    fun onMatchingPostDistrictChange(newDistrict: String) {
        _user.value =
            _user.value.copy(matchingPost = _user.value.matchingPost.copy(selectedDistrict = newDistrict))
    }

    fun onMatchingPostNeighborhoodChange(newNeighborhood: String) {
        _user.value =
            _user.value.copy(matchingPost = _user.value.matchingPost.copy(selectedNeighborhood = newNeighborhood))
    }

    fun onMatchingPostTimeChange(newTime: String) {
        _user.value = _user.value.copy(matchingPost = _user.value.matchingPost.copy(time = newTime))
    }

    fun onMatchingPostDetailChange(newDetail: String) {
        _user.value =
            _user.value.copy(matchingPost = _user.value.matchingPost.copy(detail = newDetail))
    }

    // Function to add/update matching post
    fun addMatchingPost(navController: NavController, context: Context) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users").child(_user.value.id).child("matchingPost")

        usersRef.setValue(_user.value.matchingPost).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "매칭 글이 성공적으로 등록되었습니다!", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            } else {
                Toast.makeText(context, "매칭 글 등록 실패: ${it.exception?.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun signUp(navController: NavController, context: Context) {
        if (_user.value.password != _user.value.passwordConfirm) {
            Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users").child(_user.value.id)

        usersRef.setValue(_user.value).addOnCompleteListener {
            if (it.isSuccessful) {
                navController.popBackStack()
                Toast.makeText(context, "성공적으로 가입되었습니다!", Toast.LENGTH_SHORT).show()
                navController.navigate("login")
            } else {
                Toast.makeText(context, "회원가입 실패: ${it.exception?.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    fun signIn(id: String, password: String, context: Context, navController: NavController) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users").child(id)

        Log.d("UserViewModel", "Attempting to sign in with id: $id")

        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("UserViewModel", "onDataChange called")
                if (snapshot.exists()) {
                    val storedPassword = snapshot.child("password").getValue(String::class.java)
                    Log.d("UserViewModel", "Stored password: $storedPassword")
                    if (storedPassword == password) {
                        val user = snapshot.getValue(User::class.java)
                        user?.let {
                            viewModelScope.launch {
                                _user.emit(it)
                            }
                        }
                        navController.popBackStack()
                        Toast.makeText(context, "성공적으로 로그인되었습니다!", Toast.LENGTH_SHORT).show()
                        navController.navigate("navigation_bar")
                    } else {
                        Toast.makeText(context, "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "해당 아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("UserViewModel", "onCancelled called with error: ${error.message}")
                Toast.makeText(context, "로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun checkIdDuplicate(context: Context) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users").child(_user.value.id)

        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(context, "사용할 수 없는 아이디입니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "중복 확인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun checkNicknameDuplicate(context: Context) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")

        usersRef.orderByChild("nickname").equalTo(_user.value.nickname)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        Toast.makeText(context, "사용할 수 없는 닉네임입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "중복 확인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun editProfile(context: Context) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users").child(_user.value.id)

        usersRef.setValue(_user.value).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "성공적으로 변경되었습니다!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "정보 변경 실패: ${it.exception?.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}
