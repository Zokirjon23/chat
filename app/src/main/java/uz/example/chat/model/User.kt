package uz.example.chat.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var number: String,
    var name: String,
    var lastname: String = ""
) : Serializable