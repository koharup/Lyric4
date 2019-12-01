package app.sano.picchi.Lyricsmemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_translation2.*
import kotlinx.android.synthetic.main.activity_translation2.contentText
import kotlinx.android.synthetic.main.activity_translation2.titleText
import kotlinx.android.synthetic.main.activity_translation2.updateText

class Translation2Activity : AppCompatActivity() {

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
        setContentView(R.layout.activity_translation2)


        backButton.setOnClickListener{
            finish()
        }

        DataShow()

    }

    fun DataShow(){
        val memo = realm.where(Memo::class.java).equalTo(
            "updateDate",
            intent.getStringExtra("updateDate")
        ).findFirst()

        titleText.setText(memo.title)
        contentText.setText(memo.content)
        updateText.setText(memo.updateDate)
        kashi2Text.setText(memo.word2)



    }




    override fun onDestroy() {
        super.onDestroy()

        //realmを閉じる
        realm.close()
    }

}
