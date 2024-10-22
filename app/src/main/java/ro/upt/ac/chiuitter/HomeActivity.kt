package ro.upt.ac.chiuitter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat

class HomeActivity : AppCompatActivity() {

    /**
     * Take a moment to observe how an activity registers for an activity result using the
     * ActivityResultContract API.
     */
    private val getChiuitLauncher = registerForActivityResult(GetChiuitResultContract()) { result ->
        setChiuitText(result)
    }

    private val chiuitText = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chiuitText.value = getText(R.string.chiuit_example).toString()
        setContent { HomeScreen() }
    }

    @Composable
    private fun HomeScreen() {
        val text by remember { chiuitText }

        Surface(color = Color.White) {
            Box(modifier = Modifier.fillMaxSize()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            modifier = Modifier
                                .weight(0.8f)
                                .padding(8.dp),
                            text = text,
                        )
                        Button(
                            modifier = Modifier
                                .weight(0.2f)
                                .padding(8.dp),
                            onClick = { shareChiuit(text) }) {
                            Icon(
                                Icons.Filled.Send,
                                stringResource(R.string.send_action_icon_content_description)
                            )
                        }
                    }
                }
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    onClick = { composeChiuit() },
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        stringResource(R.string.edit_action_icon_content_description)
                    )
                }
            }
        }
    }

    /**
     * Defines text sharing/sending *implicit* intent, opens the application chooser menu,
     * and starts a new activity which supports sharing/sending text.
     */
    private fun shareChiuit(chiuitText: String) {
        val shareIntent = ShareCompat.IntentBuilder(this)
        // TODO 1: Configure shareIntent to support text sending and set the text extra to chiuitText.
            .setType("text/plain")
            .setText(chiuitText)
        shareIntent.startChooser()
    }

    /*

     */
    /**
     * Launches the composing activity using the previously defined activity launcher.
     */
    private fun composeChiuit() {
        // TODO 3: Start the ComposeActivity using getChiuitLauncher.
        getChiuitLauncher.launch(Unit)
    }

    /**
     * Checks and sets the new text value for the chiuit field.
     */
    private fun setChiuitText(resultText: String?) {
        // TODO 6: Check if text is not null or empty, then set the new "chiuitText".
        if(!resultText.isNullOrEmpty()){
            chiuitText.value = resultText
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun DefaultPreview() {
        HomeScreen()
    }

}
