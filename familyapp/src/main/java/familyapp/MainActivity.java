package familyapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.familyapp.R;


public class MainActivity extends FragmentActivity implements Context{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public void changeToRegisterFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RegisterFragment())
                .commit();
    }
    public void backToLoginFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }
    public void goToMapFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MapFragment())
                .commit();
    }

    @Override
    public void MakeToast(String message) {

    }

    @Override
    public void onComplete() {
        goToMapFragment();
    }
}
