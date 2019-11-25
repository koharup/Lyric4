package app.sano.picchi.lyric3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.util.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val handler = Handler()
        val timer = Timer(false)
        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    handler.post {
                        //メインへ
                        val intent = Intent(this@FirstActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            },
            1800
        )
    }
}
