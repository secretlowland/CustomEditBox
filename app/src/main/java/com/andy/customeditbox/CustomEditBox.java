package com.andy.customeditbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by lxn on 2015/10/16.
 */
public class CustomEditBox extends RelativeLayout {

    private EditText contentBox;
    private ImageView deleteBtn;

    private CharSequence hint;
    private Drawable cleanIcon;
    private int inputType;

    public CustomEditBox(Context context) {
        this(context, null);
    }

    public CustomEditBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomEditBox);
        int count = ta.getIndexCount();
        for (int i=0; i<count; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.CustomEditBox_hint:
                    hint = ta.getString(attr);
                    break;
                case R.styleable.CustomEditBox_cleanIcon:
                    cleanIcon  = ta.getDrawable(attr);
                    break;
                case R.styleable.CustomEditBox_inputType:
                    inputType = ta.getInt(attr, 0x00000001);
                    break;
                default:break;
            }
        }
        ta.recycle();
        init(context);
    }

    /**
     * 获取输入的值
     * @return 输入的值
     */
    public String getText() {
        return contentBox.getText().toString();
    }


    /**
     * 设置提示文字
     * @param hint 提示
     */
    public void setHint(CharSequence hint) {
        contentBox.setHint(hint);
    }

    /**
     * 设置文字的输入类型
     * @param type 文字类型
     */
    public void setInputType(int type) {
        contentBox.setInputType(type);
    }


    private void init(final Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_edit_box, this, true);
        contentBox = (EditText)view.findViewById(R.id.content);
        deleteBtn = (ImageView)view.findViewById(R.id.delete_btn);

        contentBox.setHint(hint);
        contentBox.setInputType(inputType);
        deleteBtn.setImageDrawable(cleanIcon);
        deleteBtn.setVisibility(INVISIBLE);

        deleteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                contentBox.setText("");
            }
        });

        contentBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {   }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {  }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    deleteBtn.setVisibility(INVISIBLE);
                } else {
                    deleteBtn.setVisibility(VISIBLE);
                }
            }
        });
    }



}
