package com.bawei.mymvp.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.bawei.mymvp.R;

/**
 * ValueAnimator  先改变值，然后 手动赋值 给对象的属性从而实现动画；是 间接 对对象属性进行操作；
 * ObjectAnimator 先改变值，然后 自动赋值 给对象的属性从而实现动画；是 直接 对对象属性进行操作；
 */
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private View mViewTranslate, mViewProperty, mViewPropertyOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        init();
    }

    private void init() {
        mViewTranslate = findViewById(R.id.view_translate);
        mViewProperty = findViewById(R.id.view_property);
        mViewPropertyOther = findViewById(R.id.view_property_other);

        mViewTranslate.setOnClickListener(this);
        mViewProperty.setOnClickListener(this);
        findViewById(R.id.button_translate).setOnClickListener(this);
        findViewById(R.id.button_property).setOnClickListener(this);
        findViewById(R.id.button_property_other).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_translate:
                Toast.makeText(this, "click view_translate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.view_property:
                Toast.makeText(this, "click view_property", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_translate:
                //补间动画
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 500,
                        1.0f, 1.0f);
                translateAnimation.setFillAfter(true);
                translateAnimation.setDuration(3000);
                mViewTranslate.startAnimation(translateAnimation);
                break;
            case R.id.button_property:
                //属性动画
                //第一步 ObjectAnimator 实例
                //实例通过ObjectAnimator.ofFloat得来
                //第一个参数，我想要移动的view
                //第二个参数，我想要执行动画的名称
                //第三个参数，起点，每一个节点,按照坐标点依次移动
                final ObjectAnimator translationX = ObjectAnimator.ofFloat(mViewProperty, "translationX", 0, 500, 300, 100, 500);
                //持续时间
                translationX.setDuration(3000);
                //重复次数。。。。。
                translationX.setRepeatCount(0);
                //开始动画
                translationX.start();
                break;
            case R.id.button_property_other:
                //X轴平移
                ObjectAnimator translation_X = ObjectAnimator.ofFloat(mViewPropertyOther, "translationX", 0,500);
                //Y轴平移
                ObjectAnimator translation_Y = ObjectAnimator.ofFloat(mViewPropertyOther, "translationY", 0,50);
                //透明度
                ObjectAnimator alpha = ObjectAnimator.ofFloat(mViewPropertyOther, "alpha", 1f, 0f);
                //X轴缩放
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(mViewPropertyOther, "scaleX", 1f, 2f);
                //Y轴缩放
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(mViewPropertyOther, "scaleY", 1f, 2f);
                //旋转
                ObjectAnimator rotation = ObjectAnimator.ofFloat(mViewPropertyOther, "rotation", 1f, 360f);

                //注意：这里是AnimatorSet
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(3000);
                //大家一起来
                animatorSet.playTogether(translation_X, translation_Y, alpha, scaleX, scaleY, rotation);

                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });


//                //play的动画将在after动画完成后执行
//                animatorSet.play(translation_X).after(translation_Y);
//                //play的动画将在before动画之前执行
//                animatorSet.play(translation_X).before(translation_Y);
//                //play的动画将和with动画一起执行
//                animatorSet.play(translation_X).with(translation_Y);

                animatorSet.start();



                //通过AnimatorInflater.loadAnimator拿到动画
//                Animator anim = AnimatorInflater.loadAnimator(this,R.animator.view_animation);
//                //通过setTarget方法将要执行动画的view放置进去
//                anim.setTarget(mViewPropertyOther);
//                anim.start();
                break;
            default:
                break;
        }
    }
}
