package solutionz.professionhub.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import solutionz.professionhub.menu_class.MenuBaseActivity;
import solutionz.professionhub.R;

public class ActivityButtons extends MenuBaseActivity implements View.OnClickListener {

    Context parent;

    Button btn_userSignUp;
    Button btn_workerSignUp;
    Button btn_login;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        setViews();
        setAnimation();
    }

    private void setViews() {
        btn_userSignUp = (Button) findViewById(R.id.btn_user);
        btn_workerSignUp = (Button) findViewById(R.id.btn_worker);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_userSignUp.setOnClickListener(this);

        btn_workerSignUp.setOnClickListener(this);

        btn_login.setOnClickListener(this);
    }


    public void setAnimation() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        //ImageView img_pi=(ImageView) findViewById(R.id.pichart_img);
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.down);

        btn_login.clearAnimation();
        btn_login.startAnimation(anim2);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
      /*  ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);*/
        btn_userSignUp.clearAnimation();
        btn_userSignUp.startAnimation(anim);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms


                Animation anim3 = AnimationUtils.loadAnimation(ActivityButtons.this, R.anim.bounce);

                btn_workerSignUp.clearAnimation();
                btn_workerSignUp.startAnimation(anim3);


            }
        }, 2000);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {



            case R.id.btn_user:

                Intent intentUser = new Intent(ActivityButtons.this,MainActivityUser.class);
                startActivity(intentUser);


                break;
            case R.id.btn_worker:

                Intent intentWorker = new Intent(ActivityButtons.this,ActivityButtons.class);
                startActivity(intentWorker);
                break;
            case R.id.btn_login:
                Intent intentLogin = new Intent(ActivityButtons.this,MapsActivity.class);
                startActivity(intentLogin);
                break;

        }
    }
}
