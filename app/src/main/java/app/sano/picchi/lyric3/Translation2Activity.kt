package app.sano.picchi.lyric3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_translation.*
import kotlinx.android.synthetic.main.activity_translation2.*

class Translation2Activity : AppCompatActivity() {

    //realm型の変数を宣言
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation2)

        //realmを開く
        Realm.init(this)
        realm = Realm.getDefaultInstance()





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

        textView1.setText(memo.updateDate)


    }




    override fun onDestroy() {
        super.onDestroy()

        //realmを閉じる
        realm.close()
    }

}
