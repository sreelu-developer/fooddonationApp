package locationtrack;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erp.R;
import com.google.android.gms.location.LocationListener;

import java.util.List;
import java.util.Locale;

public class Locations extends AppCompatActivity {
    TextView newTxt;
    Double latitude = 0.0;
    Double longitude = 0.0;
    static String TAG = "Locations";
    Location gps_loc = null, network_loc = null, final_loc = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        newTxt = (TextView) findViewById(R.id.txtloc);
        LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(getApplicationContext(), "not granted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Granted", Toast.LENGTH_LONG).show();
        }

        try {
            gps_loc=locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc=locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(gps_loc!=null)
        {
            final_loc=gps_loc;
            latitude=gps_loc.getLatitude();
            longitude=gps_loc.getLongitude();

        }
        else  if(network_loc!=null)
        {
            final_loc=gps_loc;
            latitude=gps_loc.getLatitude();
            longitude=gps_loc.getLongitude();
        }

        else {
            latitude=0.0;
            longitude=0.0;
        }

        try
        {
            Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address>addresses=geocoder.getFromLocation(latitude,longitude,1);

            if(addresses!=null&&addresses.size()>0)
            {
                String address=addresses.get(0).getAddressLine(0);
                String city=addresses.get(0).getLocality();
                String state=addresses.get(0).getAdminArea();
                String country=addresses.get(0).getCountryName();
                String post_code=addresses.get(0).getPostalCode();
                String known_name=addresses.get(0).getFeatureName();

                newTxt.setText("Address :"+address+"\n\n"+

                        "City :"+city+"\n\n"+
                        "State :"+state+"\n\n"+
                        "Country :"+country+"\n\n"+
                        "PostCode :"+post_code+"\n\n"+
                        "Known_Name :"+known_name);


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
