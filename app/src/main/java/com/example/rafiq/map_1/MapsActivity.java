package com.example.rafiq.map_1;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private static final LatLng Railway = new LatLng(24.88204945, 91.86877683);
    private static final LatLng Railway1 = new LatLng(24.88224897, 91.86898336);
    private static final LatLng Railway2 = new LatLng(24.88261882, 91.86938569);
    private static final LatLng Railway3 = new LatLng(24.88324659, 91.86878219);
    private static final LatLng Railway4 = new LatLng(24.88377703, 91.86849251);
    private static final LatLng Railway5 = new LatLng(24.88466271, 91.86829939);
    private static final LatLng Railway6 = new LatLng(24.88599122, 91.86820284);
    private static final LatLng Railway7 = new LatLng(24.88696935, 91.86804727);
    private static final LatLng KeaneBridge = new LatLng(24.88751924, 91.86798826);
    private static final LatLng courtPoint = new LatLng(24.88926378, 91.86789304);
    private static final LatLng courtPoint1 = new LatLng(24.89045112, 91.86797887);
    private static final LatLng courtPoint2 = new LatLng(24.89095233, 91.86903566);
    private static final LatLng Bandarbazar = new LatLng(24.89117131, 91.8700549);
    private static final LatLng Bandarbazar1 = new LatLng(24.89194502, 91.87035263);
    private static final LatLng Bandarbazar2 = new LatLng(24.89308367, 91.86946213);
    private static final LatLng Zindabazar = new LatLng(24.89487436, 91.8687433);
    private static final LatLng Zindabazar1 = new LatLng(24.89620763, 91.86835706);
    private static final LatLng Zindabazar2 = new LatLng(24.89808586, 91.86831415);
    private static final LatLng Chowhatta = new LatLng(24.89939963, 91.86873257);
    private static final LatLng Chowhatta1 = new LatLng(24.90135566, 91.86939776);
    private static final LatLng Chowhatta2 = new LatLng(24.90299053, 91.86967671);
    private static final LatLng Ambarkhana = new LatLng(24.90508274, 91.86991274);
    private static final LatLng Ambarkhana1 = new LatLng(24.90518978, 91.87001601);
    private static final LatLng Ambarkhana2 = new LatLng(24.90500732, 91.87117741);
    private static final LatLng Ambarkhana3 = new LatLng(24.90501705, 91.87138662);
    private static final LatLng Ambarkhana4 = new LatLng(24.90501705, 91.87138662);
    private static final LatLng Ambarkhana5 = new LatLng(24.90542332, 91.87275723);
    private static final LatLng Ambarkhana6 = new LatLng(24.90558632, 91.87327757);
    private static final LatLng Ambarkhana7 = new LatLng(24.90561308, 91.8735753);
    private static final LatLng Ambarkhana8 = new LatLng(24.90533818, 91.87493786);
    private static final LatLng Ambarkhana9 = new LatLng(24.90535764, 91.87554404);
    private static final LatLng Ambarkhana10 = new LatLng(24.90544522, 91.87584177);
    private static final LatLng Ambarkhana11 = new LatLng(24.90574688, 91.87637016);
    private static final LatLng Ambarkhana12 = new LatLng(24.90621397, 91.87692538);
    private static final LatLng Ambarkhana13 = new LatLng(24.90643779, 91.8772848);
    private static final LatLng Ambarkhana14 = new LatLng(24.90656186, 91.87793925);
    private static final LatLng Ambarkhana15 = new LatLng(24.90674675, 91.87957004);
    private static final LatLng Ambarkhana16 = new LatLng(24.90665917, 91.87992141);
    private static final LatLng ShahiEdgha = new LatLng(24.90624073, 91.88129202);
    private static final LatLng ShahiEdgha1 = new LatLng(24.90621884, 91.88171312);
    private static final LatLng ShahiEdgha2 = new LatLng(24.90654969, 91.88371405);
    private static final LatLng ShahiEdgha3 = new LatLng(24.90659835, 91.88424781);
    private static final LatLng TA_Road = new LatLng(24.9069438, 91.88620314);
    private static final LatLng TA_Road1 = new LatLng(24.90709706, 91.8866913);
    private static final LatLng TA_Road2 = new LatLng(24.90755928, 91.88769713);
    private static final LatLng TA_Road3 = new LatLng(24.90756415, 91.88794389);
    private static final LatLng TA_Road4 = new LatLng(24.90739629, 91.88814506);
    private static final LatLng TA_Road5 = new LatLng(24.90690244, 91.88857689);
    private static final LatLng TA_Road6 = new LatLng(24.9065424, 91.88914016);
    private static final LatLng TA_Road7 = new LatLng(24.90506814, 91.89078972);
    private static final LatLng TA_Road8 = new LatLng(24.90477134, 91.89115182);
    private static final LatLng TA_Road9 = new LatLng(24.90454752, 91.89173788);
    private static final LatLng TA_Road10 = new LatLng(24.90441615, 91.8923977);
    private static final LatLng TA_Road11 = new LatLng(24.90430424, 91.89281613);
    private static final LatLng TA_Road12 = new LatLng(24.90351115, 91.89376563);
    private static final LatLng TA_Road13 = new LatLng(24.90350628, 91.8947795);
    private static final LatLng BalocharPoint = new LatLng(24.90300512, 91.89593285);
    private static final LatLng BalocharPoint1 = new LatLng(24.9028105, 91.89667851);
    private static final LatLng BalocharPoint2 = new LatLng(24.90280563, 91.89819664);
    private static final LatLng BalocharPoint3 = new LatLng(24.90264506, 91.8986097);
    private static final LatLng TilagorEdgha = new LatLng(24.90172059, 91.899409);
    private static final LatLng Alortol = new LatLng(24.90265966, 91.90077156);
    private static final LatLng Alortol1 = new LatLng(24.9026402, 91.90115243);
    private static final LatLng Alortol2 = new LatLng(24.9035744, 91.9032231);
    private static final LatLng Alortol3 = new LatLng(24.90384201, 91.9034484);
    private static final LatLng Alortol4 = new LatLng(24.90468862, 91.90331429);
    private static final LatLng Alortol5 = new LatLng(24.90567147, 91.90348059);
    private static final LatLng Alortol6 = new LatLng(24.90630399, 91.9039312);
    private static final LatLng Alortol7 = new LatLng(24.90736953, 91.9039312);
    private static final LatLng Alortol8 = new LatLng(24.90886809, 91.90336794);
    private static final LatLng Alortol9 = new LatLng(24.90950059, 91.90324455);
    private static final LatLng Alortol10 = new LatLng(24.91054664, 91.90304607);
    private static final LatLng Alortol11 = new LatLng(24.91085316, 91.9031319);
    private static final LatLng Alortol12 = new LatLng(24.91163161, 91.90342695);
    private static final LatLng Hostel = new LatLng(24.91106723, 91.90381318);
    private static final LatLng College = new LatLng(24.91159755, 91.9039312);
    private static final LatLng Kadamtoli = new LatLng(24.88265045, 91.8698591);
    private static final LatLng Kadamtoli1 = new LatLng(24.88213461, 91.87036335);
    private static final LatLng Kadamtoli2 = new LatLng(24.88202755, 91.8708998);
    private static final LatLng moktiZoddha = new LatLng(24.88131704, 91.87543273);
    private static final LatLng HumayonChottor = new LatLng(24.87749191, 91.87539518);
    private static final LatLng HumayonChottor1 = new LatLng(24.87774498, 91.87594235);
    private static final LatLng HumayonChottor2 = new LatLng(24.87835817, 91.87691867);
    private static final LatLng HumayonChottor3 = new LatLng(24.87939962, 91.87783062);
    private static final LatLng HumayonChottor4 = new LatLng(24.88495469, 91.88094869);
    private static final LatLng RoseView = new LatLng(24.88574547, 91.8809849);
    private static final LatLng Uposhohor = new LatLng(24.8875168, 91.88456833);
    private static final LatLng Uposhohor1 = new LatLng(24.88802289, 91.88526571);
    private static final LatLng Uposhohor2 = new LatLng(24.88836353, 91.88606769);
    private static final LatLng Uposhohor3 = new LatLng(24.88844139, 91.88662022);
    private static final LatLng Uposhohor4 = new LatLng(24.88850465, 91.8898657);
    private static final LatLng Uposhohor5 = new LatLng(24.88856304, 91.89018756);
    private static final LatLng Uposhohor6 = new LatLng(24.88867497, 91.89033777);
    private static final LatLng Uposhohor7 = new LatLng(24.8914876, 91.89130336);
    private static final LatLng Uposhohor8 = new LatLng(24.89270412, 91.89134091);
    private static final LatLng Uposhohor9 = new LatLng(24.8934243, 91.89102978);
    private static final LatLng Uposhohor10 = new LatLng(24.89380871, 91.89095467);
    private static final LatLng Uposhohor11 = new LatLng(24.89469919, 91.89093322);
    private static final LatLng Uposhohor12 = new LatLng(24.89521011, 91.89070791);
    private static final LatLng baloShib = new LatLng(24.89534149, 91.89209998);
    private static final LatLng baloShib1 = new LatLng(24.89622223, 91.8920812);
    private static final LatLng baloShib2 = new LatLng(24.89671368, 91.89192563);
    private static final LatLng baloShib3 = new LatLng(24.89685479, 91.89199671);
    private static final LatLng baloShib4 = new LatLng(24.89761874, 91.89201549);
    private static final LatLng baloShib5 = new LatLng(24.89799341, 91.89210936);
    private static final LatLng baloShib6 = new LatLng(24.89813695, 91.89235881);
    private static final LatLng baloShib7 = new LatLng(24.89842647, 91.8941237);
    private static final LatLng baloShib8 = new LatLng(24.89880113, 91.89420685);
    private static final LatLng baloShib9 = new LatLng(24.89891305, 91.89464673);
    private static final LatLng baloShib10 = new LatLng(24.89946775, 91.89475134);
    private static final LatLng baloShib11 = new LatLng(24.89990567, 91.89495251);
    private static final LatLng baloShib12 = new LatLng(24.90023654, 91.89502761);
    private static final LatLng baloShib13 = new LatLng(24.90042387, 91.89517513);
    private static final LatLng baloShib14 = new LatLng(24.90077177, 91.89523146);
    private static final LatLng baloShib15 = new LatLng(24.90109291, 91.89517513);
    private static final LatLng baloShib16 = new LatLng(24.90157705, 91.8950142);
    private static final LatLng baloShib17 = new LatLng(24.90178384, 91.89498469);
    private static final LatLng baloShib18 = new LatLng(24.90251856, 91.89461455);
    private static final LatLng baloShib19 = new LatLng(24.90277157, 91.89449385);
    private static final LatLng baloShib20 = new LatLng(24.90311217, 91.89455286);
    private static final LatLng TA_BaloShib = new LatLng(24.90348439, 91.89474329);
    private static final LatLng Tamabil = new LatLng(24.89526851, 91.89275175);
    private static final LatLng Tamabil1 = new LatLng(24.89476245, 91.89354569);
    private static final LatLng Tamabil2 = new LatLng(24.89481597, 91.89391047);
    private static final LatLng Tamabil3 = new LatLng(24.89559939, 91.89530522);
    private static final LatLng Tamabil4 = new LatLng(24.8955556, 91.89610988);
    private static final LatLng Tamabil5 = new LatLng(24.89539989, 91.89661413);
    private static final LatLng Tamabil6 = new LatLng(24.895069, 91.89733833);
    private static final LatLng Tamabil7 = new LatLng(24.8950836, 91.89779431);
    private static final LatLng Tamabil8 = new LatLng(24.89577456, 91.89898521);
    private static final LatLng Tamabil9 = new LatLng(24.89604705, 91.89994544);
    private static final LatLng Tilagor = new LatLng(24.8961687, 91.90023243);
    private static final LatLng Tilagor1 = new LatLng(24.89728299, 91.90032095);
    private static final LatLng Tilagor2 = new LatLng(24.89793015, 91.900675);
    private static final LatLng Tilagor3 = new LatLng(24.89889845, 91.90104514);
    private static final LatLng Tilagor4 = new LatLng(24.90001272, 91.90078765);
    private static final LatLng Tilagor5 = new LatLng(24.90118536, 91.90014929);
    private static final LatLng RoseView1 = new LatLng(24.88823701, 91.88019097);
    private static final LatLng RoseView2 = new LatLng(24.8890448, 91.87980473);
    private static final LatLng RoseView3 = new LatLng(24.89099126, 91.87808812);
    private static final LatLng IbnSina = new LatLng(24.89176984, 91.87792718);
    private static final LatLng Naiorpool = new LatLng(24.89377465, 91.87847435);
    private static final LatLng Naiorpool1 = new LatLng(24.89475758, 91.87864602);
    private static final LatLng Kumarpara = new LatLng(24.89613951, 91.87864602);
    private static final LatLng Kumarpara1 = new LatLng(24.89732679, 91.87833756);
    private static final LatLng Kumarpara2 = new LatLng(24.8978523, 91.87841803);
    private static final LatLng Kumarpara3 = new LatLng(24.89804693, 91.87840194);
    private static final LatLng Kumarpara4 = new LatLng(24.89945802, 91.87905639);
    private static final LatLng Kumarpara5 = new LatLng(24.89998839, 91.87933534);
    private static final LatLng Kazitula = new LatLng(24.90099073, 91.87936217);
    private static final LatLng Kazitula1 = new LatLng(24.90198333, 91.87957674);
    private static final LatLng Kazitula2 = new LatLng(24.90282996, 91.88007027);
    private static final LatLng Kazitula3 = new LatLng(24.90376903, 91.88045651);
    private static final LatLng Shahi = new LatLng(24.90500002, 91.88055843);
    private static final LatLng Shahi1 = new LatLng(24.90529196, 91.88162595);
    private static final LatLng Shahi2 = new LatLng(24.90622857, 91.8817158);
    private static final LatLng RoseViewMark = new LatLng(24.88618344, 91.8809098);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ConnectivityManager conMgr =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if ( conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

            // set the message to display
            alertbox.setMessage("সম্পুর্ন তথ্য পাওয়ার জন্য মোবাইল ডাটা অন করুন");

            // add a neutral button to the alert box and assign a click listener
            alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                // click listener on the alert box
                public void onClick(DialogInterface arg0, int arg1) {
                    // the button was clicked

                }
            });

            // show it
            alertbox.show();
        }
        else if( conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING )  {

            setUpMapIfNeeded();
            //Toast.makeText(getApplicationContext(),"Offline",Toast.LENGTH_LONG).show();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();
        switch (id) {
        /*    case R.id.action_settings:
                AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

                // set the message to display
                alertbox.setMessage("Md. Rafiqul Islam\nCSE 3rd Year\nmrafq709@gmail.com\nSylhet Engineering College.");

                // add a neutral button to the alert box and assign a click listener
                alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                    // click listener on the alert box
                    public void onClick(DialogInterface arg0, int arg1) {
                        // the button was clicked

                    }
                });

                // show it
                alertbox.show();

                break;*/
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /*public void onSearch(View view) {
        EditText location_TF = (EditText) findViewById(R.id.editText);
        String location = location_TF.getText().toString();

        List<Address> addressList = null;


            if (location != null || !location.equals("")) {
                Geocoder geocoder = new Geocoder(this);

                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address = addressList.get(0);

                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }

    }*/

    public void onType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                PolylineOptions polylineOptions = new PolylineOptions().add(Railway)
                        .add(Railway1).add(Railway2).add(Railway3).add(Railway4).add(Railway5)
                        .add(Railway6).add(Railway7).add(KeaneBridge)
                        .add(courtPoint).add(courtPoint1).add(courtPoint2).add(Bandarbazar)
                        .add(Bandarbazar).add(Bandarbazar1).add(Bandarbazar2).add(Zindabazar)
                        .add(Zindabazar1).add(Zindabazar2).add(Chowhatta).add(Chowhatta1)
                        .add(Chowhatta2).add(Ambarkhana).add(Ambarkhana1).add(Ambarkhana2)
                        .add(Ambarkhana3).add(Ambarkhana4).add(Ambarkhana5).add(Ambarkhana6)
                        .add(Ambarkhana7).add(Ambarkhana8).add(Ambarkhana9).add(Ambarkhana10)
                        .add(Ambarkhana11).add(Ambarkhana12).add(Ambarkhana13).add(Ambarkhana14)
                        .add(Ambarkhana15).add(Ambarkhana16).add(ShahiEdgha).add(ShahiEdgha1).add(ShahiEdgha2)
                        .add(ShahiEdgha3).add(TA_Road).add(TA_Road1).add(TA_Road2).add(TA_Road3).add(TA_Road4)
                        .add(TA_Road5).add(TA_Road6).add(TA_Road7).add(TA_Road8).add(TA_Road9).add(TA_Road10)
                        .add(TA_Road11).add(TA_Road12).add(TA_Road13).add(BalocharPoint).add(BalocharPoint1)
                        .add(BalocharPoint2).add(BalocharPoint3).add(TilagorEdgha).add(Alortol)
                        .add(Alortol1).add(Alortol2).add(Alortol3).add(Alortol4).add(Alortol5).add(Alortol6)
                        .add(Alortol7).add(Alortol8).add(Alortol9).add(Alortol10).add(Alortol11)
                        .add(Alortol12)
                        .width(10).color(Color.BLUE).geodesic(true);
                mMap.addPolyline(polylineOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Railway, 15));
                mMap.addMarker(new MarkerOptions().position(Railway).title("রেলওয়ে স্টেশন"));
                mMap.addMarker(new MarkerOptions().position(KeaneBridge).title("কীন ব্রিজ"));
                mMap.addMarker(new MarkerOptions().position(Bandarbazar).title("বন্দর বাজার"));
                mMap.addMarker(new MarkerOptions().position(Zindabazar).title("জিন্দাবাজার"));
                mMap.addMarker(new MarkerOptions().position(Chowhatta).title("চৌহাঁটটা"));
                mMap.addMarker(new MarkerOptions().position(Ambarkhana).title("আম্বরখানা"));
                mMap.addMarker(new MarkerOptions().position(ShahiEdgha).title("শাহী ঈদগা"));
                mMap.addMarker(new MarkerOptions().position(TA_Road).title("টিলাগর-আম্বরখানা রোড"));
                mMap.addMarker(new MarkerOptions().position(BalocharPoint).title("বালুচর পয়েন্ট"));
                mMap.addMarker(new MarkerOptions().position(TilagorEdgha).title("টিলাগর ঈদ্গা"));
                mMap.addMarker(new MarkerOptions().position(Alortol).title("আলুরতল রোড"));
                mMap.addMarker(new MarkerOptions().position(Hostel).title("ইঞ্জিনিয়ারিং কলেজ বালক ছাত্রাবাস"));
                mMap.addMarker(new MarkerOptions().position(College).title("সিলেট ইঞ্জিনিয়ারিং কলেজ"));
                mMap.addMarker(new MarkerOptions().position(Kadamtoli).title("কদমতলি বাস স্ট্যান্ড"));

                PolylineOptions polylineOptions1 = new PolylineOptions().add(Kadamtoli).add(Kadamtoli1).add(Kadamtoli2)
                        .add(moktiZoddha).add(HumayonChottor).add(HumayonChottor1).add(HumayonChottor2).add(HumayonChottor3)
                        .add(HumayonChottor4).add(RoseView).add(Uposhohor).add(Uposhohor1).add(Uposhohor2).add(Uposhohor3)
                        .add(Uposhohor4).add(Uposhohor5).add(Uposhohor6).add(Uposhohor7).add(Uposhohor8).add(Uposhohor9)
                        .add(Uposhohor10).add(Uposhohor11).add(Uposhohor12).add(baloShib).add(baloShib1).add(baloShib2)
                        .add(baloShib3).add(baloShib4).add(baloShib5).add(baloShib6).add(baloShib7).add(baloShib8)
                        .add(baloShib9).add(baloShib10).add(baloShib11).add(baloShib12).add(baloShib13).add(baloShib14)
                        .add(baloShib15).add(baloShib16).add(baloShib17).add(baloShib18).add(baloShib19).add(baloShib20)
                        .add(TA_BaloShib)
                        .width(10).color(Color.BLUE).geodesic(true);

                mMap.addPolyline(polylineOptions1);
                mMap.addMarker(new MarkerOptions().position(moktiZoddha).title("মুক্তিযোদ্ধা চত্তর"));
                mMap.addMarker(new MarkerOptions().position(HumayonChottor).title("হুমায়ুন রশিদ চত্তর"));
                mMap.addMarker(new MarkerOptions().position(RoseViewMark).title("রোজ ভিউ"));
                mMap.addMarker(new MarkerOptions().position(Uposhohor1).title("উপশহর"));
                mMap.addMarker(new MarkerOptions().position(baloShib1).title("বালুচর-শীবগঞ্জ রোড"));

                PolylineOptions polylineOptions2 = new PolylineOptions().add(baloShib).add(Tamabil).add(Tamabil1)
                        .add(Tamabil2).add(Tamabil3).add(Tamabil4).add(Tamabil5).add(Tamabil6).add(Tamabil7).add(Tamabil8)
                        .add(Tamabil9).add(Tilagor).add(Tilagor1).add(Tilagor2).add(Tilagor3).add(Tilagor4)
                        .add(Tilagor5).add(TilagorEdgha)
                        .width(10).color(Color.BLUE).geodesic(true);
                mMap.addPolyline(polylineOptions2);
                mMap.addMarker(new MarkerOptions().position(Tamabil1).title("তামাবিল রোড"));
                mMap.addMarker(new MarkerOptions().position(Tilagor).title("টিলাগর পয়েন্ট"));

                PolylineOptions polylineOptions3 = new PolylineOptions().add(RoseView).add(RoseView1).add(RoseView2)
                        .add(RoseView3).add(IbnSina).add(Naiorpool).add(Naiorpool1).add(Kumarpara).add(Kumarpara1)
                        .add(Kumarpara2).add(Kumarpara3).add(Kumarpara4).add(Kumarpara5).add(Kazitula).add(Kazitula1)
                        .add(Kazitula2).add(Kazitula3).add(Shahi).add(Shahi1).add(Shahi2)
                        .width(10).color(Color.BLUE).geodesic(true);
                mMap.addPolyline(polylineOptions3);
                mMap.addMarker(new MarkerOptions().position(IbnSina).title("ইবনে সিনা হাসপাতাল"));
                mMap.addMarker(new MarkerOptions().position(Naiorpool).title("নাইওর পুল"));
                mMap.addMarker(new MarkerOptions().position(Kumarpara).title("কুমার পারা রোড"));
                mMap.addMarker(new MarkerOptions().position(Kumarpara2).title("কুমার পারা "));
                mMap.addMarker(new MarkerOptions().position(Kazitula).title("কাজীটুলা"));
                mMap.addMarker(new MarkerOptions().position(Shahi).title("শাহী ঈদগা"));
            }
        }
    }


    private void setUpMap() {

        mMap.setMyLocationEnabled(true);
    }

    public void onZoomIn(View view) {
        if (view.getId() == R.id.btnZoomIn)
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        if (view.getId() == R.id.btnZoomOut)
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
    }

}
