package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun CreateTopic(title: String, description: String , information : String , userId : String , imageLogo : String){
        val topic = hashMapOf(
            "name" to title,
            "autherId" to userId,
            "description" to description,
            "information" to information,
            "logo" to imageLogo,
            "hidden" to false,
            "subscription" to false,
        )
        db.collection("topics")
            .add(topic)
            .addOnSuccessListener {documentReference ->
                topicId = documentReference.id
                uploadImageToFirebase()
                log.d("add success" , "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener{ e ->
                log.w("add falid" , "Error adding document " , e)
            }
    }


    private fun UpdateTopic(topicId: String, title: String, description: String, information: String, imageLogo: String) {
        val topic = hashMapOf(
            "name" to title,
            "description" to description,
            "information" to information,
            "logo" to imageLogo
        )

        db.collection("topics")
            .document(topicId)
            .update(topic)
            .addOnSuccessListener {
                log.d("update success", "تم تحديث الموضوع بنجاح")
            }
            .addOnFailureListener { e ->
                log.w("update error", "حدث خطأ أثناء تحديث الموضوع", e)
            }
    }

    private fun DeleteTopic(topicId: String) {
        db.collection("topics")
            .document(topicId)
            .delete()
            .addOnSuccessListener {
                log.d("delete success", "تم حذف الموضوع بنجاح")
            }
            .addOnFailureListener { e ->
                log.w("delete error", "حدث خطأ أثناء حذف الموضوع", e)
            }
    }
}


}