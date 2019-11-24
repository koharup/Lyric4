package app.sano.picchi.lyric3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import io.realm.Realm
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

    }

    fun ChangeActivity(v: View) {
        val intent = Intent(this, Translation2Activity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

        //realmを閉じる
        realm.close()
    }
}
