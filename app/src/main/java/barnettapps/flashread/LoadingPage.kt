package barnettapps.flashread

import android.app.Activity
import android.os.Bundle
import barnettapps.flashread.SpeedReadObjects.*
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_loading_page.*


class LoadingPage : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_page)

        val _input = "This is the test String. Hopefully, It will break down properly."
        val divided = SpeedReadGenerator().generate( _input)
        val flattened = SpeedReadGenerator().flatten( divided )

        MainText.text = _input
    }
}
