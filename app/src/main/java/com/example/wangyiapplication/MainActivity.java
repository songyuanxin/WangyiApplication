package com.example.wangyiapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;

    private MediaPlayer mediaPlayer=new MediaPlayer();
//    private ImageView imageView;
//    private ImageSwitcher mImageSwitcher;
//    int[]images={R.drawable.lunbo1,R.drawable.lunbo2,R.drawable.lunbo3,R.drawable.lunbo4,
//            R.drawable.lunbo5,R.drawable.lunbo6,R.drawable.lunbo7,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.text_view);

        Button play=(Button) findViewById(R.id.weibabutton2);
        Button pause=(Button)findViewById(R.id.weibabutton1 );
        Button stop=(Button) findViewById(R.id.weibabutton3);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }else{
            initMediaPlayer();
        }

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

//        mImageSwitcher=(ImageSwitcher)findViewById(R.id.imagSwitcher);
//        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
//                return new ImageView(MainActivity.this);
//            }
//        });
//        mImageSwitcher.postDelayed(new Runnable() {
//            int currentIndex = 0;
//            @Override
//            public void run() {
//                mImageSwitcher.setImageResource(images[currentIndex]);
//                if (currentIndex==(images.length-1))
//                    currentIndex=0;
//                else
//                    currentIndex++;
//                mImageSwitcher.postDelayed(this,1500);
//            }
//        },2500);

    }

    private void initMediaPlayer() {
        try{
            Uri uri=Uri.parse("android.resource://com.wangyiapplication/"+R.raw.music);
            mediaPlayer.setDataSource(this,uri);
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weibabutton2:
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case R.id.weibabutton3:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.weibabutton1:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }

//   public void onRequestPermissionsResult(int requestCode,String[] permission,int[] grantResults){
//        switch (requestCode){
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initMediaPlayer();
//                }else {
//                    Toast.makeText(this,"拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                break;
//            default:
//        }
//   }

    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

}

