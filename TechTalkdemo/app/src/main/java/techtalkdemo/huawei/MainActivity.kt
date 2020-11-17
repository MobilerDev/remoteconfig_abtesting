package techtalkdemo.huawei

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huawei.agconnect.remoteconfig.AGConnectConfig
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val agConnectConfig = AGConnectConfig.getInstance()
        agConnectConfig.applyDefault(R.xml.remote_config)

        btn_LanguageRemoteConfig?.setOnClickListener {
            agConnectConfig.fetch(0).addOnSuccessListener {

                agConnectConfig.apply(it)

                val languageResponse = agConnectConfig.getValueAsString("language")

                Toast.makeText(this, languageResponse, Toast.LENGTH_LONG).show()

                if (languageResponse == "TR"){
                    txt_AppLanguage.text="Language is Turkish"
                }
                else if(languageResponse == "EN"){
                    txt_AppLanguage.text="Language is English"
                }

            }.addOnFailureListener {
                // fail fetching from remote config
                it.printStackTrace()
            }
        }
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, ABTestingActivity::class.java)
            startActivity(intent)
        }
    }
}
