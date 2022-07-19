package com.csk.ondemanddynamicdelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.csk.toastkotlinlibrary.ToasterLibrary
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class MainActivity : AppCompatActivity(), SplitInstallStateUpdatedListener {

    private val splitInstallManager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUiComponents()
    }


    override fun onDestroy() {
        super.onDestroy()
        splitInstallManager.unregisterListener(this)
    }

    private fun initUiComponents() {

        val onInstall = findViewById<Button>(R.id.btn_onInstall)
        val onDemand = findViewById<Button>(R.id.btn_onDemand)

        onInstall.setOnClickListener {
            val intent = Intent()

            // we have to call class reference instead of Activity name

            intent.setClassName(BuildConfig.APPLICATION_ID, "com.csk.oninstall.OnInstallActivity")
            startActivity(intent)
        }

        onDemand.setOnClickListener {

            val request = SplitInstallRequest.newBuilder()
                .addModule(DYNAMIC_MODULE_NAME)
                .build()

            splitInstallManager.registerListener {
                when (it.status()) {
                    SplitInstallSessionStatus.DOWNLOADING -> ToasterLibrary.useToastAnywhere(
                        this,
                        "Downloading Dynamic Module"
                    )

                    SplitInstallSessionStatus.CANCELED -> {
                        TODO()
                    }
                    SplitInstallSessionStatus.CANCELING -> {
                        TODO()
                    }
                    SplitInstallSessionStatus.DOWNLOADED -> {
                        TODO()
                    }
                    SplitInstallSessionStatus.FAILED -> {
                        TODO()
                    }
                    SplitInstallSessionStatus.INSTALLED -> {
                        ToasterLibrary.useToastAnywhere(this, "Dynamic Module is ready")
                        val intent = Intent()

                        // we have to call class reference instead of Activity name
                        // calling on demand

                        intent.setClassName(
                            BuildConfig.APPLICATION_ID,
                            "com.csk.ondemand.OnDemandActivity"
                        )
                        startActivity(intent)
                    }
                    SplitInstallSessionStatus.INSTALLING -> {
                        TODO()
                    }
                    SplitInstallSessionStatus.PENDING -> {
                        TODO()
                    }
                    SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                        TODO()
                    }
                    SplitInstallSessionStatus.UNKNOWN -> {
                        TODO()
                    }
                }
            }

            splitInstallManager.startInstall(request)
        }


    }

    companion object {
        private const val DYNAMIC_MODULE_NAME = "onDemand"
    }

    override fun onStateUpdate(p0: SplitInstallSessionState) {
        TODO("Not yet implemented")
    }


}