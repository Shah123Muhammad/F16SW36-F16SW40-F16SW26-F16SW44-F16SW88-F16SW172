package com.example.shahmuhammad.informationactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    private ImageView ImageViewPic;
    private TextView nametv, rollnotv;
    private Button rbutton,lbutton;

    private int currentImage=0;
    private int currentName=0;
    private int currentroll=0;

    int[]images={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};
    String[]names={"Shah Muhammad","Mir Hassan","Aqib Ali","Jawad Ali","Sajid Hussain"};
    String[]rolls={"F16SW36","F16SW40","F16SW26","F16SW44","F16SW88"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        ImageViewPic= (ImageView) findViewById(R.id.ImageViewPic);
        nametv=(TextView) findViewById(R.id.nametv);
        rollnotv=(TextView) findViewById(R.id.rollnotv);

        rbutton=(Button) findViewById(R.id.rightbtn);
        lbutton=(Button) findViewById(R.id.leftbtn);

        rbutton.setOnClickListener(rbuttonChangeImageListener);
        lbutton.setOnClickListener(lbuttonChangeImageListener);

    }

    View.OnClickListener rbuttonChangeImageListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                //Increase the counter to move to next image
                currentImage++;
                currentImage=currentImage%images.length;
                ImageViewPic.setImageResource(images[currentImage]);

                currentName++;
                currentName=currentName%names.length;
                nametv.setText(names[currentName]);

                currentroll++;
                currentroll=currentroll%rolls.length;
                rollnotv.setText(rolls[currentroll]);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    View.OnClickListener lbuttonChangeImageListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                currentImage--;
                currentImage=(currentImage+images.length)%images.length;
                ImageViewPic.setImageResource(images[currentImage]);


                currentName--;
                currentName=(currentName+names.length)%currentName;
                nametv.setText(names[currentName]);

                currentroll++;
                currentroll=(currentroll+rolls.length)%currentroll;
                rollnotv.setText(rolls[currentroll]);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };
}
