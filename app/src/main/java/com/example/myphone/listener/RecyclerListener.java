package com.example.myphone.listener;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myphone.MainActivity;
import com.example.myphone.R;
import com.example.myphone.util.QRCode;

public class RecyclerListener implements View.OnClickListener {

    String content = "html";
    private MainActivity mContext;

    private LinearLayout buy_view ;

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static final String CHARACTER_SET = "utf-8";
    public static final String ERROR_CORRECTION_LEVEL = "H";
    public static final String MARGIN = "1";
    public static final int COLOR_BLACK = Color.BLACK;
    public static final int COLOR_WHITE = Color.WHITE;

    public RecyclerListener(MainActivity mContext,int commodityId,int count){
        content="购买商品id为:"+commodityId+",购买数量为:"+count;
        this.mContext=mContext;
    }

    @Override
    public void onClick(View v) {
        Bitmap bitmap = QRCode.createQRCodeBitmap(content,WIDTH,HEIGHT,CHARACTER_SET,ERROR_CORRECTION_LEVEL, MARGIN, COLOR_BLACK, COLOR_WHITE);
        ImageView image_qrCode = mContext.findViewById(R.id.image_qrCode);
        buy_view = mContext.findViewById(R.id.buy_view);
        Button button_cancel = mContext.findViewById(R.id.button_cancel);
        TextView text_tips=mContext.findViewById(R.id.text_tips);
        text_tips.setText("请扫码付款");
        buy_view.setVisibility(View.VISIBLE);
        image_qrCode.setImageBitmap(bitmap);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy_view.setVisibility(View.GONE);
            }
        });
    }
}
