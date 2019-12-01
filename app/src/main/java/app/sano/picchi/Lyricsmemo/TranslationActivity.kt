package app.sano.picchi.Lyricsmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_translation.*

class TranslationActivity : AppCompatActivity() {

    //realm型の変数を宣言
    val realm: Realm by lazy {
        Realm.init(this)
        Realm.getDefaultInstance()
    }
    val memo by lazy { realm.where(Memo::class.java).equalTo(
        "updateDate",
        intent.getStringExtra("updateDate")
    ).findFirst() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        //realmを開く
        showData()
    }

    fun showData() {
        titleText.setText(memo.title)
        contentText.setText(memo.content)
        updateText.setText(memo.updateDate)
        kashiText.setText(memo.word1)
        //kashi2Text.setText(memo.word2)
    }

    //詳細に画面遷移
     fun changeActivity(v: View) {
        //画面遷移
        val intent = Intent(this@TranslationActivity, Translation2Activity::class.java)
        intent.putExtra("updateDate", memo.updateDate)
        startActivity(intent)
    }



    //こっちも同じように
    fun ChangeWritteActivity(v: View){

            val intent = Intent(this@TranslationActivity, DetailActivity::class.java)
            intent.putExtra("updateDate", memo.updateDate)
            startActivity(intent)
    }

    override fun onDestroy() {
        Log.d("title", titleText.text.toString())
        Log.d("updateDate", updateText.text.toString())
        Log.d("content", contentText.text.toString())
        Log.d("word", kashiText.text.toString())
        //Log.d("word2", kashi2Text.text.toString())

        super.onDestroy()

        //realmを閉じる
        realm.close()
    }
}
