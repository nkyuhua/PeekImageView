package nk.peekimageview.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nk.peekimageview.PeekImageView;

public class MainActivity extends AppCompatActivity {
    private PeekImageView peekImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peekImageView= (PeekImageView) findViewById(R.id.peek);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        peekImageView.dispatchActivityResult(requestCode,resultCode,data);
    }
}
