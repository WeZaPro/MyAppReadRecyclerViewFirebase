package example.com.myappreadrecyclerviewfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();

        if(savedInstanceState == null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentContainer_main,mainFragment)
                    .commit();


        }
    }
}
