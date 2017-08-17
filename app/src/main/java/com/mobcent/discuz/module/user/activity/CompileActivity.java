package com.mobcent.discuz.module.user.activity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.wheelview.WView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.compileBeans.CompileBeans;
import discuz.com.net.service.model.bean.editInfoBean.EditInfoBean;
import discuz.com.retrofit.library.HTTPSubscriber;
import library.component.actionbar.AppActionBar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.appbyme.dev.R.id.compile_back_image;

public class CompileActivity extends BasePopActivity implements View.OnClickListener {
    private String head;
    private String sign;
    private String mobile;
    private String qq;
    private String email;
    private String graduateschool;
    private String education;
    private String company;
    private String occupation;
    private String nickname;


    ImageView head_image;
    static File file;
    static String photoPath;
    private String edu;
    private  TextView textedu;
    private  TextView mWText;
    private ImageView image;
    private TextView compile_signature_text,compile_cellphone_text,compile_qq_text,compile_email_text,compile_graduate_institutions_text,
    compile_company_text,compile_profession_text,compile_nickname_text;


    public static final int PHOTORESOULT = 3;// 结果
    AppActionBar bar;

    public static final String IMAGE_UNSPECIFIED = "image/*";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netWork();
        initial();
    }

    private void netWork() {
        DiscuzRetrofit.getUserInfoService(this).compile(LoginUtils.getInstance().getUserId(),WebParamsMap.compile()).subscribe(new HTTPSubscriber<CompileBeans>() {
            @Override
            public void onSuccess(CompileBeans compileBean) {
                if (compileBean.getHead().getErrcode().equals("00000000")){
                    int number=compileBean.getList().size();
                    head=compileBean.getList().get(0).getField().get(0).getNowSet().toString();
                    sign=compileBean.getList().get(0).getField().get(1).getNowSet().toString();
                    mobile=compileBean.getList().get(1).getField().get(0).getNowSet().toString();
                    qq=compileBean.getList().get(1).getField().get(1).getNowSet().toString();
                    email=compileBean.getList().get(1).getField().get(2).getNowSet().toString();
                    graduateschool=compileBean.getList().get(2).getField().get(0).getNowSet().toString();
                    education=compileBean.getList().get(2).getField().get(1).getNowSet().toString();
                    company=compileBean.getList().get(3).getField().get(0).getNowSet().toString();
                    occupation=compileBean.getList().get(3).getField().get(1).getNowSet().toString();
                    nickname=compileBean.getList().get(4).getField().get(0).getNowSet().toString();
                    Glide.with(CompileActivity.this).load(head).asBitmap().centerCrop().into(new BitmapImageViewTarget(head_image) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(CompileActivity.this.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            head_image.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                    compile_signature_text.setText(sign);
                    compile_cellphone_text.setText(mobile);
                    compile_qq_text.setText(qq);
                    compile_email_text.setText(email);
                    compile_graduate_institutions_text.setText(graduateschool);
                    compile_company_text.setText(company);
                    compile_profession_text.setText(occupation);
                    compile_nickname_text.setText(nickname);
                }

            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("TAG", "失败");
                Log.i("TAG", "httpCode="+httpCode);
                Log.i("TAG", "errorUserCode="+errorUserCode);
                Log.i("TAG", "message="+message);
            }

        });
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    @Override
    public int initLayout() {
        return R.layout.activity_compile;
    }

    private void initial() {
        getAppActionBar().setTitle(R.string.mc_forum_user_my_info);
        head_image= (ImageView) findViewById(compile_back_image);
        head_image.setOnClickListener(this);
        findViewById(R.id.compile_head).setOnClickListener(this);
        findViewById(R.id.compile_signature).setOnClickListener(this);
        findViewById(R.id.compile_education).setOnClickListener(this);
        findViewById(R.id.compile_cellphone).setOnClickListener(this);
        findViewById(R.id.compile_qq).setOnClickListener(this);
        findViewById(R.id.compile_email).setOnClickListener(this);
        findViewById(R.id.compile_graduate_institutions).setOnClickListener(this);
        findViewById(R.id.compile_company).setOnClickListener(this);
        findViewById(R.id.compile_profession).setOnClickListener(this);
        findViewById(R.id.compile_nickname).setOnClickListener(this);
        textedu= (TextView) findViewById(R.id.compile_activity_text_edu);
        compile_signature_text= (TextView) findViewById(R.id.compile_signature_text);
        compile_cellphone_text= (TextView) findViewById(R.id.compile_cellphone_text);
        compile_qq_text= (TextView) findViewById(R.id.compile_qq_text);
        compile_email_text= (TextView) findViewById(R.id.compile_email_text);
        compile_graduate_institutions_text= (TextView) findViewById(R.id.compile_graduate_institutions_text);
        compile_company_text= (TextView) findViewById(R.id.compile_company_text);
        compile_profession_text= (TextView) findViewById(R.id.compile_profession_text);
        compile_nickname_text= (TextView) findViewById(R.id.compile_nickname_text);
    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch (id){
            case R.id.compile_head:
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),System.currentTimeMillis()+".jpg");
                photoPath = file.getAbsolutePath();
                Uri value = Uri.fromFile(file);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, value);

                Intent intent = Intent.createChooser(intent1,"请选择");
                intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent2});

                startActivityForResult(intent, 101);
                    break;


            //签名
            case R.id.compile_signature:
                Intent intent3=new Intent(this,EditActivity.class);
                intent3.putExtra("title","签名");
                intent3.putExtra("content",sign);
                intent3.putExtra("single",false);
                intent3.putExtra("from","signature");
                startActivityForResult(intent3,102);
                break;

            //手机
            case R.id.compile_cellphone:
                Intent intent4=new Intent(this,EditActivity.class);
                intent4.putExtra("title","手机");
                intent4.putExtra("content",mobile);
                intent4.putExtra("single",true);
                intent4.putExtra("phone",true);
                intent4.putExtra("from","cellphone");
                startActivityForResult(intent4,103);
                break;

            //QQ
            case R.id.compile_qq:
                Intent intent5=new Intent(this,EditActivity.class);
                intent5.putExtra("title","QQ");
                intent5.putExtra("content",qq);
                intent5.putExtra("single",true);
                intent5.putExtra("from","compile_qq");
                startActivityForResult(intent5,104);
                break;//compile_graduate_institutions
            //电子邮件
            case R.id.compile_email:
                Intent intent6=new Intent(this,EditActivity.class);
                intent6.putExtra("title","电子邮件");
                intent6.putExtra("content",email);
                intent6.putExtra("single",true);
                intent6.putExtra("from","compile_email");
                startActivityForResult(intent6,105);
                break;
            //毕业院校
            case R.id.compile_graduate_institutions:
                Intent intent7=new Intent(this,EditActivity.class);
                intent7.putExtra("title","毕业院校");
                intent7.putExtra("content",graduateschool);
                intent7.putExtra("single",false);
                intent7.putExtra("from","compile_graduate_institutions");
                startActivityForResult(intent7,106);
                break;
            //公司
            case R.id.compile_company:
                Intent intent8=new Intent(this,EditActivity.class);
                intent8.putExtra("title","公司");
                intent8.putExtra("content",company);
                intent8.putExtra("single",false);
                intent8.putExtra("from","compile_company");
                startActivityForResult(intent8,107);
                break;

            //职业
            case R.id.compile_profession:
                Intent intent9=new Intent(this,EditActivity.class);
                intent9.putExtra("title","职业");
                intent9.putExtra("content",occupation);
                intent9.putExtra("single",true);
                intent9.putExtra("from","compile_profession");
                startActivityForResult(intent9,108);
                break;

            //昵称
            case R.id.compile_nickname:
                Intent intent10=new Intent(this,EditActivity.class);
                intent10.putExtra("title","昵称");
                intent10.putExtra("content",nickname);
                intent10.putExtra("single",true);
                intent10.putExtra("from","compile_nickname");
                startActivityForResult(intent10,109);
                break;
            //学历
            case R.id.compile_education:
                View outerView = LayoutInflater.from(this).inflate(R.layout.layout_wheelview, null);
                TextView cancel= (TextView) outerView.findViewById(R.id.dialog_wheel_cancel);
                TextView positive= (TextView)outerView.findViewById(R.id.dialog_wheel_positive);
                outerView.setBackgroundColor(Color.WHITE);
                WView mWView= (WView) outerView.findViewById(R.id.view_wheel);

                mWView.setOnWheelChangeListener(new WView.OnWheelChangeListener() {
                    @Override
                    public void onWheelChange(int index, Object object) {
                        edu=object.toString();
                    }
                });
                List<Object> data = new ArrayList<>();
                data.add(getString(R.string.mc_forum_other));
                data.add(getString(R.string.mc_forum_primary));
                data.add(getString(R.string.mc_forum_middle));
                data.add(getString(R.string.mc_forum_junior));
                data.add(getString(R.string.mc_forum_regular));
                data.add(getString(R.string.mc_forum_master));
                data.add(getString(R.string.mc_forum_doctor));

                mWView.setData(data);

                final AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setView(outerView);
                dialog.show();


                Window dialogWindow = dialog.getWindow();

                dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
                dialogWindow.setGravity(Gravity.BOTTOM);
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                dialogWindow.setAttributes(lp);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textedu.setText(edu);
                        netWork(edu);
                        dialog.dismiss();
                    }
                });
                break;

            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null) {
            Uri uri = data.getData();
        }

        if(resultCode==RESULT_OK && requestCode==101){
            //从返回的结果intent中取需要的内容
            //是图片在SD卡保存的路径
            if(data!=null){
                //图库选图返回
                //uri是用户选择的图片在图库中的位置
                //要利用uri找图片真正在SD卡上的位置
                Uri uri =geturi(data);
                    startPhotoZoom(uri);
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 - 100)压缩文件
                    }


            }else{
                //拍照返回
               startPhotoZoom(Uri.fromFile(file));
            }
//            post:   userAvatar	1488249727841.jpg
//            http://forum.longquanzs.org//mobcent/app/web/index.php?r=user/uploadavatarex&packageName=com.appbyme.app178470&accessSecret=8fb255a208b7982bb159f5dffbc1e
//            &sdkVersion=2.4.3.0&imei=868029029800109&forumType=7&apphash=3a3f1998&accessToken=b8c746f3a931e0d0ffdbcc76c6360&appName=é¾æ³è®ºå&
//              forumKey=BW0L5ISVRsOTVLCTJx&platType=1&imsi=460001001651621
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        final MediaType MEDIA_TYPE_MARKDOWN
                                = MediaType.parse("text/html; charset=utf-8");
                        OkHttpClient mOkHttpClient=new OkHttpClient();
                        //File file = new File(file);
                        Request request = new Request.Builder()
                                .url("http://forum.longquanzs.org//mobcent/app/web/index.php?r=user/uploadavatarex&packageName=com.appbyme.app178470&accessSecret=8fb255a208b7982bb159f5dffbc1e" +
                                        "&sdkVersion=2.4.3.0&imei=868029029800109&forumType=7&apphash=3a3f1998&accessToken=b8c746f3a931e0d0ffdbcc76c6360&appName=é¾\u0099æ³\u0089è®ºå\u009D\u009B&" +
                                        "forumKey=BW0L5ISVRsOTVLCTJx&platType=1&imsi=460001001651621")
                                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                                .build();

                        mOkHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                Log.i("TAG","返回结果:"+response.body().string());
                            }
                        });


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();



        }else if (requestCode==102){
            //签名
            try {
                compile_signature_text.setText(data.getStringExtra("text"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }else if (requestCode==103){
            //手机
            try {
                compile_cellphone_text.setText(data.getStringExtra("text"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }else if (requestCode==104){
            //QQ
            try {
                compile_qq_text.setText(data.getStringExtra("text"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }else if (requestCode==105){
            //电子邮件
            try {
                compile_email_text.setText(data.getStringExtra("text"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }else if (requestCode==106){
            //毕业院校
            try {
                compile_graduate_institutions_text.setText(data.getStringExtra("text"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }else if (requestCode==107){
            //公司
            try {
                compile_company_text.setText(data.getStringExtra("text"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }else if (requestCode==108){
            //职业
            try {
                compile_profession_text.setText(data.getStringExtra("text"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }else if (requestCode==109){
            //昵称
            try {
                compile_nickname_text.setText(data.getStringExtra("text"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        // 处理结果及圆形图片处理

        try {
            if (requestCode == PHOTORESOULT) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 - 100)压缩文件
                    image.setImageBitmap(photo);
                }

            }
        } catch (NullPointerException e) {
            finish();
        }
    }


    /**
     * 图片裁剪
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 64);
        intent.putExtra("outputY", 64);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTORESOULT);
    }

    /**
     * 该方法解决小米(红米)手机出现Uri出错问题
     */
    public Uri geturi(Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }

    /*
  * 剪切图片
  */
//    private void crop(Uri uri) {
//        // 裁剪图片意图
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        // 裁剪框的比例，1：1
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // 裁剪后输出图片的尺寸大小
//        intent.putExtra("outputX", 250);
//        intent.putExtra("outputY", 250);
//
//        intent.putExtra("outputFormat", "JPEG");// 图片格式
//        intent.putExtra("noFaceDetection", true);// 取消人脸识别
//        intent.putExtra("return-data", true);
//        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
//        startActivityForResult(intent, PHOTO_REQUEST_CUT);
//    }

    private void netWork(final String param){
        String ecode= URLEncoder.encode(param);
        String num="%7B%22education%22%3A%22"+ecode+"%22%7D";
        DiscuzRetrofit.getUserInfoService(this).myselfInfoedit( WebParamsMap.myselfInfoedits(num)).subscribe(new HTTPSubscriber<EditInfoBean>() {

            @Override
            public void onSuccess(EditInfoBean coloectionBean) {
                String errInfo=coloectionBean.getHead().getErrinfo();
                if (coloectionBean.getHead().getErrcode().equals("00000000")){
                    Toast.makeText(CompileActivity.this,param,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CompileActivity.this,errInfo,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("TAG", "错误");
                Log.i("TAG", "errorUserCode="+errorUserCode);
                Log.i("TAG", "message="+message);
            }

        });
    }

}

