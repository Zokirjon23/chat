package uz.example.chat.repository

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import uz.example.chat.db.local.MyPreferences
import uz.example.chat.model.User
import java.util.concurrent.TimeUnit


class AuthRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val preference: MyPreferences
) : AuthRepository {
    private var sentCode: String = "123456"

    override fun sendSms(
        builder: PhoneAuthOptions.Builder,
        onSent: (String) -> Unit,
        onVerify: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            builder.setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onCodeSent(
                        p0: String,
                        p1: PhoneAuthProvider.ForceResendingToken
                    ) {
                        sentCode = p0
                        onSent(p0)
                    }

                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        auth.signInWithCredential(credential).addOnCompleteListener { p0 ->
                            if (p0.isSuccessful) {
                                onVerify(credential.smsCode!!)
                            } else {
                                onError("Error")
                            }
                        }
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        onError(p0.message!!)
                    }
                })
        } catch (e: Exception) {
            onError(e.message!!)
        }

        PhoneAuthProvider.verifyPhoneNumber(builder.build())
    }

    override fun checkCode(smsCode: String, onComplete: (Unit) -> Unit, onError: (String) -> Unit) {
        try {
            val phoneAuthCredential =
                PhoneAuthProvider.getCredential(sentCode, smsCode)
            auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener { p0 ->
                if (p0.isSuccessful) {
                    onComplete(Unit)
                } else {
                    onError("Error code")
                }
            }
        } catch (e: Exception) {
            onError(e.message!!)
            Log.d("CCC", e.message!!)
        }
    }

    override fun addUserToServer(user: User, onSuccess: (Unit) -> Unit, onError: (String) -> Unit) {
        try {
            fireStore.collection("user")
                .add(user)
                .addOnSuccessListener {
                    onSuccess(Unit)
                    preference.setCurrentAuth(user)
                }.addOnFailureListener {
                    onError(it.message!!)
                }
        } catch (e: Exception) {
            onError(e.message!!)
        }
    }

    override fun checkNumberExist(
        phoneNumber: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            fireStore.collection("user")
                .whereEqualTo("number", phoneNumber)
                .get()
                .addOnSuccessListener {
                    onSuccess(!it.isEmpty)
                    for (i in it) {
                        Log.d(
                            "XXX", i.toObject(User::class.java).name
                        )
                        preference.setCurrentAuth(i.toObject(User::class.java))
                    }
                }
                .addOnFailureListener {
                    onError(it.message!!)
                }
        } catch (e: Exception) {
            onError(e.message!!)

        }
    }

    override fun getAuth(): User? = preference.getCurrentAuth()

    override fun removeCurrentAuth() {
        Firebase.auth.signOut()
        preference.removeAuth()
    }

    override fun getUsersServer(onSuccess: (QuerySnapshot) -> Unit, onError: (String) -> Unit) {
        fireStore.collection("user").get()
            .addOnFailureListener {
                onError(it.message!!)
            }.addOnSuccessListener {
                onSuccess(it)
            }
    }
}