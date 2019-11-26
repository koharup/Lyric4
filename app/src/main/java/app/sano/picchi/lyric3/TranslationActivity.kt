package app.sano.picchi.lyric3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_translation.*

class TranslationActivity : AppCompatActivity() {

    //realm型の変数を宣言
    lateinit var realm: Realm


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)

        //realmを開く
        Realm.init(this)
        realm = Realm.getDefaultInstance()

        showData()

    }

    fun showData() {
        val memo = realm.where(Memo::class.java).equalTo(
            "updateDate",
            intent.getStringExtra("updateDate")
        ).findFirst()

        titleText.setText(memo.title)
        contentText.setText(memo.content)
        updateText.setText(memo.updateDate)
        kashiText.setText(memo.word1)
        kashi2Text.setText(memo.word2)

    }


    fun ChangeActivity(v: View) {
            //val memo = parent.getItemAtPosition(position) as Memo
            val intent = Intent(this@TranslationActivity, Translation2Activity::class.java)
            //intent.putExtra("updateDate", memo.updateDate)
            startActivity(intent)

    }

    fun ChangeWritteActivity(v: View){

            //val memo = parent.getItemAtPosition(position) as Memo
            val intent = Intent(this@TranslationActivity, DetailActivity::class.java)
            //intent.putExtra("updateDate", memo.updateDate)
            startActivity(intent)
    }

    override fun onDestroy() {
        Log.d("title", titleText.text.toString())
        Log.d("updateDate", updateText.text.toString())
        Log.d("content", contentText.text.toString())
        Log.d("word", kashiText.text.toString())
        Log.d("word2", kashi2Text.text.toString())

        super.onDestroy()

        //realmを閉じる
        realm.close()
    }
}


//    //ここでmemoの情報を渡せたらtranslation2が表示されるはず
//    fun ChangeActivity(v: View) {
//        val memo = parent.intent as Memo
//        //val memo = parent.getItemAtPosition(position) as Memo
//        val intent = Intent(this, Translation2Activity::class.java)
//        intent.putExtra("updateDate", memo.updateDate)
//        startActivity(intent)
//
//    }
