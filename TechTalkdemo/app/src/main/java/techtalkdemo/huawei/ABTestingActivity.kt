package techtalkdemo.huawei

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.huawei.agconnect.remoteconfig.AGConnectConfig
import kotlinx.android.synthetic.main.activity_a_b_testing.*

class ABTestingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_b_testing)

        val agConnectConfig = AGConnectConfig.getInstance()
        agConnectConfig.applyDefault(R.xml.abtesting_config)

        btn_ABTesting?.setOnClickListener {
            agConnectConfig.fetch(0).addOnSuccessListener {

                agConnectConfig.apply(it)
                val abTestResponse = agConnectConfig.getValueAsString("ABTesting")

                if (abTestResponse == "a") {
                    txt_ABTesting.text="A/B Testing response: a"
                } else if (abTestResponse == "b") {
                    txt_ABTesting.text="A/B Testing response: b"
                }

            }.addOnFailureListener {
                // fail fetching from remote config
                it.printStackTrace()
            }
        }
    }
}
