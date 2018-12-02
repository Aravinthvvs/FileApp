package com.dev.aravinthvvs.fileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.uploadButton);
        tv = (TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent,7);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Uri uri = null;

        switch (requestCode)
        {
            case 7:
                if(resultCode == RESULT_OK)
                {
                    if(data.getData() != null)
                    {
                       uri = data.getData();
                    }
                    String pathHolder = data.getData().getPath();
                    tv.append(pathHolder);
                    //Toast.makeText(MainActivity.this,pathHolder,Toast.LENGTH_LONG).show();

                }
                break;
        }

        try
        {
            readTextFromURI(uri);
        }
        catch (IOException err)
        {
            System.out.print(err);
        }

    }

    private  String readTextFromURI(Uri uri) throws IOException
    {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
        builder.append(line);
        }
        inputStream.close();
        reader.close();
        //tv.append(builder.toString());
        //Toast.makeText(MainActivity.this,builder.toString(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),ShowFileMetaData.class);
        intent.putExtra("metaData",builder.toString());
        startActivity(intent);
        return builder.toString();
    }
}
