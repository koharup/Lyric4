package app.sano.picchi.lyric3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_translation.*
import kotlinx.android.synthetic.main.activity_translation.contentText
import kotlinx.android.synthetic.main.activity_translation.titleText
import kotlinx.android.synthetic.main.layout_item_memo.*

class DetailActivity : AppCompatActivity() {

    //realm型の変数を宣言
    lateinit var realm: Realm

    //EditText型の変数宣言
    lateinit var titleEditText: EditText
    lateinit var contentEditText: EditText
    lateinit var word1EditText: EditText
    lateinit var word2EditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //realmを開く
        Realm.init(this)
        realm = Realm.getDefaultInstance()

        //関連付け
        titleEditText = findViewById(R.id.titleEditText) as EditText
        contentEditText = findViewById(R.id.contentEditText) as EditText
        word1EditText = findViewById(R.id.word1EditText) as EditText
        word2EditText = findViewById(R.id.word2EditText) as EditText

        //showData()


    }

    fun showData() {

        //ここでエラー(Listのintentから情報を取ろうとして得るから)
        val memo = realm.where(Memo::class.java).equalTo(
            "updateDate",
            this.intent.getStringExtra("updateDate")
        ).findFirst()

        titleText.setText(memo.title)
        contentText.setText(memo.content)
        updateText.setText(memo.updateDate)
        kashiText.setText(memo.word1)
        kashi2Text.setText(memo.word2)

    }

    fun update(view: View) {

        //showDataと同じ
        val memo = realm.where(Memo::class.java).equalTo(
            "updateDate",
            this.intent.getStringExtra("updateDate")
        ).findFirst()

        //更新する
        realm.executeTransaction {
            memo.title = titleText.text.toString()
            memo.content = contentText.text.toString()
            memo.word1 = kashiText.text.toString()
            memo.word2 = kashi2Text.text.toString()
        }

        //画面を閉じる
        finish()
    }




    override fun onDestroy() {
        super.onDestroy()

        //realmを閉じる
        realm.close()
    }
}
