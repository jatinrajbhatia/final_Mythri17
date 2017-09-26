package com.jss.jssatenmythri2017_18.activity.admin;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.jss.jssatenmythri2017_18.R;
import com.jss.jssatenmythri2017_18.activity.LoginActivity;
import com.jss.jssatenmythri2017_18.activity.RegisterActivity;
import com.jss.jssatenmythri2017_18.activity.registeration.F8Activity;
import com.jss.jssatenmythri2017_18.helper.AccessServiceAPI;
import com.jss.jssatenmythri2017_18.util.Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashwa on 9/26/2017.
 */

public class RegisterCoordinator extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    final String LOGIN_KEY = "logged_in";
    final String PREF_NAME = "mythri-2016";
    Button btnnext;
    private String coordinator_type=Common.GAME_BADMINTON;

    Spinner spinner_games, spinner_types, spinner_gender;
    ArrayAdapter gamesAdapter, typesAdapter, genderAdapter;

    String seleccted_games, selected_types, selected_gender;

    String[] gender = new String[]{
            "Boys"
    };

    String[] gender_singles = new String[]{
            "Boys",
            "Girls"
    };

    String[] gender_mixed = new String[]{
            "Boy(s) & Girl(s)"
    };


    String[] games = new String[]{
            "Badminton",
            "Table Tennis",
            "Carrom",
            "Tug Of War",
            "Cricket",
            "Football",
            "Volleyball",
            "Chess",
            "Basketball",
            "Shot Put",
            "Athelitics",
            "Kabaddi",
            "Fun Games"
    };


    String[] badminton_types = new String[]{
            "Singles",
            "Doubles",
            "Mixed Doubles",
            "Team Event"
    };

    String[] table_tennis_types = new String[]{
            "Singles",
            "Doubles",
            "Mixed Doubles"
    };

    String[] carrom_types = new String[]{
            "Singles",
            "Doubles",
            "Mixed Doubles"
    };

    String[] basketball_types = new String[]{
            "5 On 5",
            "3 On 3"
    };
    String[] football_types = new String[]{
            "none"
    };
    String[] volleyball_types = new String[]{
            "none"
    };
    String[] chess_types = new String[]{
            "none"
    };
    String[] tugofwar_types = new String[]{
            "none"
    };
    String[] cricket_types = new String[]{
            "none"
    };
    String[] shotput_types = new String[]{
            "none"
    };
    String[] athelitics_types = new String[]{
            "100mts",
            "200mts",
            "4 x 100mts relay",
            "100mts Three Legged Race",
    };
    String[] kabaddi_types = new String[]{
            "none"
    };
    String[] fungames_types = new String[]{
            "Blind Shoot",
            "Dart",
            "3 Shots",
            "Basket Shoot",
            "Ball Bounce"
    };

    private EditText txtUsername;
    private EditText txtMobile;
    private EditText txtAdmNo;
    private EditText txtPassword1;
    private EditText txtPassword2;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private String txtGender;
    private ProgressDialog m_ProgresDialog;
    private AccessServiceAPI m_AccessServiceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_register);

        btnnext = (Button) findViewById(R.id.btnRegister);
        m_AccessServiceAPI = new AccessServiceAPI();
        txtUsername = (EditText)findViewById(R.id.editTextName);
        txtMobile = (EditText)findViewById(R.id.editTextMobile);
        txtAdmNo = (EditText)findViewById(R.id.admis_no);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);

        txtPassword1 = (EditText)findViewById(R.id.editTextPassword);
        txtPassword2 = (EditText) findViewById(R.id.editTextConfirmPassword);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        genderAdapter = new ArrayAdapter(this, R.layout.spinner_item, gender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(genderAdapter);

        spinner_games = (Spinner) findViewById(R.id.spinner_games);
        gamesAdapter = new ArrayAdapter(this, R.layout.spinner_item, games);
        gamesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_games.setAdapter(gamesAdapter);

        spinner_types = (Spinner) findViewById(R.id.spinner_types);
        typesAdapter = new ArrayAdapter(this, R.layout.spinner_item, badminton_types);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_types.setAdapter(typesAdapter);

        spinner_games.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, badminton_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        coordinator_type = Common.GAME_BADMINTON;
                        spinner_types.setAdapter(typesAdapter);
                        break;
                    case 1:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, table_tennis_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_TT;
                        break;
                    case 2:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, carrom_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_CARROM;
                        break;
                    case 3:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, tugofwar_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_TOW;
                        break;
                    case 4:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, cricket_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_CRICKET;
                        break;
                    case 5:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, football_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_FOOTBALL;
                        break;
                    case 6:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, volleyball_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_VOLLEYBALL;
                        break;
                    case 7:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, chess_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_CHESS;
                        break;
                    case 8:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, basketball_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_BASKETBALL;
                        break;
                    case 9:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, shotput_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_SHOTPUT;
                        break;
                    case 10:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, athelitics_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_ATHELITICS;
                        break;
                    case 11:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, kabaddi_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_KABADDI;
                        break;
                    case 12:
                        typesAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, fungames_types); //just to show none in the beginning
                        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_types.setAdapter(typesAdapter);
                        coordinator_type = Common.GAME_FUNGAMES;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = String.valueOf(spinner_types.getSelectedItem());

                if (type.contains("Single") || type.equals("Doubles") || String.valueOf(spinner_games.getSelectedItem()).contains("Athelitics")) {
                    genderAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, gender_singles);
                    genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_gender.setAdapter(genderAdapter);
                } else if (type.contains("none") && (String.valueOf(spinner_games.getSelectedItem()).contains("Tug") ||
                        String.valueOf(spinner_games.getSelectedItem()).contains("Cricket"))) {
                    genderAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, gender_mixed);
                    genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_gender.setAdapter(genderAdapter);
                } else if (type.contains("none") && (String.valueOf(spinner_games.getSelectedItem()).contains("Football") ||
                        String.valueOf(spinner_games.getSelectedItem()).contains("Volleyball") ||
                        String.valueOf(spinner_games.getSelectedItem()).contains("Basketball") ||
                        String.valueOf(spinner_games.getSelectedItem()).contains("Chess") ||
                        String.valueOf(spinner_games.getSelectedItem()).contains("Shot Put"))) {
                    genderAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, gender_singles);
                    genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_gender.setAdapter(genderAdapter);
                } else if (type.contains("5 On 5") || type.contains("3 On 3")) {
                    genderAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, gender_singles);
                    genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_gender.setAdapter(genderAdapter);
                } else if (type.contains("none")) {
                    genderAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, gender);
                    genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_gender.setAdapter(genderAdapter);
                } else {
                    genderAdapter = new ArrayAdapter(RegisterCoordinator.this, R.layout.spinner_item, gender_mixed);
                    genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_gender.setAdapter(genderAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                seleccted_games = String.valueOf(spinner_games.getSelectedItem());
                selected_gender = String.valueOf(spinner_gender.getSelectedItem());
                selected_types = String.valueOf(spinner_types.getSelectedItem());

                //just as karan says.. ;)
                if(selected_gender.contains("Boy(s) & Girl(s)")) {
                    selected_gender = "none";
                }

                if("".equals(txtUsername.getText().toString())) {
                    txtUsername.setError("Username is required!");
                    return;
                }
                if("".equals(txtAdmNo.getText().toString())) {
                    txtUsername.setError("Username is required!");
                    return;
                }
                if("".equals(txtMobile.getText().toString())) {
                    txtMobile.setError("Mobile No. is required!");
                    return;
                }
                if("".equals(txtPassword1.getText().toString())) {
                    txtPassword1.setError("Password is required!");
                    return;
                }
                if("".equals(txtPassword2.getText().toString())) {
                    txtPassword2.setError("Confirm password is required!");
                    return;
                }

                txtGender = radioSexButton.getText().toString();

                if(txtPassword1.getText().toString().equals(txtPassword2.getText().toString())) {
                    //exec task register
                    new TaskRegister().execute(txtUsername.getText().toString(), txtPassword1.getText().toString(),
                            txtMobile.getText().toString(),txtGender,txtAdmNo.getText().toString(), coordinator_type);
                } else {
                    txtPassword2.setError("Confirm password not match!");
                }

            }


        });
    }

    public class TaskRegister extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgresDialog = ProgressDialog.show(RegisterCoordinator.this, "Please wait", "Registration processing...", true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String, String> postParam = new HashMap<>();

            postParam.put("name", params[0]);
            postParam.put("password", params[1]);
            postParam.put("mobile", params[2]);
            postParam.put("gender", params[3]);
            postParam.put("addno", params[4]);
            postParam.put("myth", "3000");
            postParam.put("login_type",params[5]);
            try{
                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_ADMIN_REGISTER, postParam);
                JSONObject jsonObject = new JSONObject(jsonString);
                return jsonObject.getInt("result");
            }catch (Exception e) {
                e.printStackTrace();
                return Common.RESULT_ERROR;
            }

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            m_ProgresDialog.dismiss();
            if(integer == Common.RESULT_SUCCESS) {
                Toast.makeText(RegisterCoordinator.this, "Registration success", Toast.LENGTH_LONG).show();
                Intent i ;
                String [] alldata = new String []{
                        txtMobile.getText().toString(),
                        txtPassword1.getText().toString()
                };
                i = new Intent().putExtra("data",alldata);
                setResult(1, i);
                finish();
            } else if(integer == Common.RESULT_USER_EXISTS) {
                Toast.makeText(RegisterCoordinator.this, "You are already registered kindly contact Admin for support!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(RegisterCoordinator.this, "Registration fail!", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement


        if (item.getItemId() == R.id.logout) {
            logout();
        }else if(item.getItemId() == R.id.calladmin){
            onCall("9456001138");
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCall(String number) {

        if(Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        123);
            } else {
                startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+number)));
            }
        }else{
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+number)));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                } else {
                    Log.d("TAG", "Call Permission Not Granted");
                }
                break;

            default:
                break;
        }
    }

    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_KEY, false);
        editor.apply();
        startActivity(new Intent(RegisterCoordinator.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
