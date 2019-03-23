package com.example.fileprovideropenfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openTheFile(View view)
    {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, BuildConfig.APPLICATION_ID);
        folder.mkdir();


        File targetFolder = new File(folder, "pdf");

        targetFolder.mkdir();

        int PICKFILE_RESULT_CODE=1;
        Uri mydir = Uri.parse("content://"+folder);
        Uri uri = Uri.fromFile(folder);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri fileUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID+".fileprovider",folder);

        intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID+".fileprovider",folder),"file/*");
//        intent.setDataAndType(uri,"file/*");
        intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

//        intent.setType("file/*");
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("file/*");

        startActivityForResult(intent,PICKFILE_RESULT_CODE);
    }
}
