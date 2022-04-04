package uz.example.chat.model

import android.graphics.Bitmap
import java.io.Serializable

class MyMassage : Serializable {
    var id = 0
    var text: String
    var type: Int = 0
    var image: Bitmap? = null

    constructor(text: String, type: Int) {
        this.text = text
        this.type = type
    }

    constructor(id: Int, text: String, type: Int, image: Bitmap?) {
        this.id = id
        this.text = text
        this.type = type
        this.image = image
    }

}