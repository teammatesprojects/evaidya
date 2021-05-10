package evidya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import evidya.myapplication.databinding.ActivityMain2Binding;

public class MainActivity extends AppCompatActivity {
    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        apicall();
    }

    public void apicall(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://clstorageapp.blob.core.windows.net/assignment/booking.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            binding.tvContext.setText(jsonObject.getString("context"));
                            binding.tvType.setText(jsonObject.getString("type"));
                            binding.tvStatus.setText(jsonObject.getString("status"));
                            binding.tvSource.setText("MobileApp");
                            JSONObject custobject=jsonObject.getJSONObject("customer");
                            binding.tvName.setText(custobject.getString("fullName"));
                            binding.tvEmail.setText(custobject.getString("email"));
                            binding.tvMobile.setText(custobject.getString("mobile"));
                            JSONObject sourceobject=jsonObject.getJSONObject("source");
                            binding.tvSName.setText(sourceobject.getString("name"));
                            JSONObject sourceaddress=sourceobject.getJSONObject("address");
                            binding.tvAddrsource.setText(sourceaddress.getString("address")+","
                                    +sourceaddress.getString("location")+","
                                    +sourceaddress.getString("city")+","
                                    +sourceaddress.getString("state")+","
                                    +sourceaddress.getString("postalCode")+","
                                    +sourceaddress.getString("country")+","
                                    +sourceobject.getString("latitude")+","
                                    +sourceobject.getString("longitude"));
                            JSONObject vendorobject=jsonObject.getJSONObject("vendor");
                            binding.tvVName.setText(vendorobject.getString("fullName"));
                            binding.tvVModel.setText(vendorobject.getString("vehicleModel"));
                            binding.tvVNo.setText(vendorobject.getString("vehicleNumber"));
                            JSONObject destinationobject=jsonObject.getJSONObject("destination");
                            binding.tvDName.setText(destinationobject.getString("name"));
                            JSONObject destinationaddress=destinationobject.getJSONObject("address");
                            JSONObject destinationcoordinate=destinationaddress.getJSONObject("coordinates");
                            binding.tvAddressDest.setText(destinationaddress.getString("address")+","
                                                            +destinationaddress.getString("location")+","
                                                            +destinationaddress.getString("city")+","
                                                            +destinationaddress.getString("state")+","
                                                            +destinationaddress.getString("postalCode")+","
                                                            +destinationaddress.getString("country")+","
                                                            +destinationcoordinate.getString("latitude")+","
                                                            +destinationcoordinate.getString("longitude")
                                                            );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}