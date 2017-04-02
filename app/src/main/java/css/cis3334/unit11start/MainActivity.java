package css.cis3334.unit11start;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    EditText etTemp;
    EditText etWind;
    EditText etVis;
    public String weatherStrURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up our edit text variables
        etTemp =  (EditText) findViewById(R.id.textTemp);
        etWind =  (EditText) findViewById(R.id.textWind);
        etVis =  (EditText) findViewById(R.id.textVis);
    }

    // Use a fixed weather string because the android emulator has issues with internet connetions.
    String xmlWeatherString = "<current_observation>"
            +"<temperature_string>39.0 F (3.9 C)</temperature_string>"
            +"<temp_f>39.0</temp_f>"
            +"<temp_c>3.9</temp_c>"
            +"<wind_mph>4.6</wind_mph>"
            +"<visibility_mi>10.00</visibility_mi>"
            +"</current_observation>";

    public void btnClick1(View v) throws XmlPullParserException, URISyntaxException, IOException {
        // create the XML Pull Parser
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = factory.newPullParser();

        xmlPullParser.setInput( new StringReader( xmlWeatherString) );

        String xmlText="";
        int eventType = xmlPullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            // explore the tage and eventTypes here

            eventType = xmlPullParser.next();
        }
    }

    public void btnClick2 (View v) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xmlWeatherString)));

        Log.d("Unit11", doc.getElementsByTagName("temperature_string").item(0).getTextContent());

    }

    public void setTemp(String newTemp) {
        etTemp.setText(newTemp);
    }

    public void setWind(String newWind) {
        etWind.setText(newWind);
    }

    public void setVis(String newVis) {
        etVis.setText(newVis);
    }


    public void setStatus(String newStatus) {
        Toast toast=Toast.makeText(getApplicationContext(), newStatus,Toast.LENGTH_LONG );
        toast.show();
    }


}
