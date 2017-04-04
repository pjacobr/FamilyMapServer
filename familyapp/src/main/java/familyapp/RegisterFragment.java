package familyapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.familyapp.R;

import request.RegisterRequest;

/**
 * Created by jacob on 3/23/2017.
 */

public class RegisterFragment extends Fragment implements Context{


    public void setmHostNumber(String mHostNumber) {
        this.mHostNumber = mHostNumber;
    }

    public void setmHostAddress(String mHostAddress) {
        this.mHostAddress = mHostAddress;
    }

    private String mHostNumber;
    private String mHostAddress;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mUsername;
    private EditText ipAddress;
    private EditText hostPort;
    private EditText mPassword;
    private RadioButton mGender;
    private Button submit;
    private ImageButton mToLogin;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        //set the username listener
        submit = (Button)v.findViewById(R.id.input_register);
        mFirstName = (EditText)v.findViewById(R.id.input_first_name);
        mLastName = (EditText)v.findViewById(R.id.input_last_name);
        mEmail = (EditText)v.findViewById(R.id.input_email);
        mUsername = (EditText)v.findViewById(R.id.input_username);
        mPassword = (EditText)v.findViewById(R.id.input_password);
        hostPort = (EditText)v.findViewById(R.id.input_host_port);
        ipAddress = (EditText)v.findViewById(R.id.input_host_address);
        //set the gender by getting the group and then the inner radio button
        RadioGroup mGroup = (RadioGroup)v.findViewById(R.id.input_gender);
        mGender = (RadioButton)v.findViewById(mGroup.getCheckedRadioButtonId());
        mToLogin = (ImageButton)v.findViewById(R.id.go_back);
        mToLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                ((MainActivity)getActivity()).backToLoginFragment();
            }
        });
        //set the listener for when to gather the data. Nothing should be null, if there is, what to report?
        submit.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        //Set the data
                        ModelContainer m = ModelContainer.getModelInstance();
                        m.setPassword(mPassword.getText().toString());
                        m.setUsername(mUsername.getText().toString());
                        m.setEmail(mEmail.getText().toString());
                        m.setHostPort(hostPort.getText().toString());
                        m.setIpAddress(ipAddress.getText().toString());
                        m.setFirstName(mFirstName.getText().toString());
                        m.setLastName(mLastName.getText().toString());
                        String gender = ((mGender.getText().toString()).equals("Male")) ? "m" : "f";
                        m.setGender(gender);
                        register();

                    }
                });
        return v;
    }

    @Override
    public void MakeToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onComplete() {
        MainActivity main = (MainActivity) getActivity();
        main.onComplete();
    }

    public void register(){
        RegisterTask rg = new RegisterTask(this);
        ModelContainer m = ModelContainer.getModelInstance();
        RegisterRequest registerRequest = new RegisterRequest(m.getUsername(), m.getPassword(),
                m.getEmail(),m.getFirstName(),m.getLastName(),m.getGender());
        rg.execute(registerRequest);
    }
}
