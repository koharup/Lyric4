package app.sano.picchi.Lyricsmemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import io.realm.Realm

class DetailActivity : AppCompatActivity() {

    //realm型の変数を宣言
    //realm型の変数を宣言
    val realm: Realm by lazy {
        Realm.init(this)
        Realm.getDefaultInstance()
    }
    val memo by lazy { realm.where(Memo::class.java).equalTo(
        "updateDate",
        intent.getStringExtra("updateDate")
    ).findFirst() }


    //EditText型の変数宣言
    lateinit var titleEditText: EditText
    lateinit var contentEditText: EditText
    lateinit var word1EditText: EditText
    lateinit var word2EditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        //関連付け
        titleEditText = findViewById(R.id.titleEditText) as EditText
        contentEditText = findViewById(R.id.contentEditText) as EditText
        word1EditText = findViewById(R.id.word1EditText) as EditText
        word2EditText = findViewById(R.id.word2EditText) as EditText

        showData()


    }

    fun showData() {

        //ここでエラー(Listのintentから情報を取ろうとして得るから)
        val memo = realm.where(Memo::class.java).equalTo(
            "updateDate",
            this.intent.getStringExtra("updateDate")
        ).findFirst()

        titleEditText.setText(memo.title)
        contentEditText.setText(memo.content)
        word1EditText.setText(memo.word1)
        word2EditText.setText(memo.word2)

    }

    fun update(view: View) {

        //showDataと同じ
        val memo = realm.where(Memo::class.java).equalTo(
            "updateDate",
            this.intent.getStringExtra("updateDate")
        ).findFirst()

        //更新する
        //なんでtitleText入れてないのに題名まで変更されてるのかわかんないけどできてる
        realm.executeTransaction {
            //memo.title = titleText.text.toString()
            memo.title = titleEditText.text.toString()
            //memo.content = contentText.text.toString()
            memo.content = contentEditText.text.toString()
            //memo.word1 = kashiText.text.toString()
            memo.word1 = word1EditText.text.toString()
            memo.word2 = word2EditText.text.toString()
            //memo.word2 = kashi2Text.text.toString()
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
