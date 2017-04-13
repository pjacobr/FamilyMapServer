package familyapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.res.ConfigurationHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import android.R.*;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familyapp.R;

import request.LoginRequest;
import result.LoginResult;

/**
 * Created by jacob on 3/22/2017.
 */

public class LoginFragment extends Fragment implements familyapp.Context{
    private EditText mUsername;
    private EditText mPassword;
    private RadioButton mGender;
    private Button submit;
    private Button register;
    private EditText mHostPort;
    private EditText mIPAddress;
    private String hostPort;
    private String ipAddress;

    public LoginFragment() {
        hostPort = null;
        ipAddress = null;
    }

    public String getHostPort() {
        return hostPort;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        //set the username listener

        submit = (Button) v.findViewById(R.id.input_login_submit);
        register = (Button) v.findViewById(R.id.input_login_register);
        mUsername = (EditText) v.findViewById(R.id.input_login_username);
        mPassword = (EditText) v.findViewById(R.id.input_login_password);
        mHostPort = (EditText) v.findViewById(R.id.input_login_host_port);
        mIPAddress =(EditText) v.findViewById(R.id.input_login_host_address);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ModelContainer m = ModelContainer.getModelInstance();
                m.setHostPort(mHostPort.getText().toString());
                m.setIpAddress(mIPAddress.getText().toString());
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity main = (MainActivity) getActivity();
                main.changeToRegisterFragment();
            }
        });

        return v;
    }

    @Override
    public void MakeToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();Toast.makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onComplete() {
        MainActivity main = (MainActivity) getActivity();
        main.onComplete();
    }

    public void login(){
        LoginTask lg = new LoginTask(this);
        LoginRequest loginR = new LoginRequest(mUsername.getText().toString(), mPassword.getText().toString());
        lg.execute(loginR);
    }
}
