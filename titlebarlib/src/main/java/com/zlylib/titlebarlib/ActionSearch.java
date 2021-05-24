package com.zlylib.titlebarlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.zlylib.titlebarlib.widget.ActionBarEx;

/**
 * @author zhangliyang
 * @date 2019/11/16
 * @github https://github.com/ZLYang110
 */
public final class ActionSearch extends ActionBarEx implements Animation.AnimationListener,View.OnClickListener,View.OnFocusChangeListener, TextView.OnEditorActionListener {


    private String leftText;
    private float leftTextSize;
    private int leftTextColor;
    private int leftTextPaddingLeft;
    private int leftTextPaddingRight;
    private int leftIconRes;
    private int leftIconColor;
    private int leftIconPadding;
    private int leftIconMarginLeft;
    private int rightIconRes;
    private int rightIconColor;
    private int rightIconPadding;
    private int rightIconMarginRight;
    private String titleText;
    private float titleTextSize;
    private int titleTextColor;
    private int titleTextMaxWidth;
    private boolean leftTextClickToFinish;
    private boolean leftIconClickToFinish;

    private int searchLeftIconRes;
    private int searchLeftIconColor;
    private int searchLeftIconPadding;
    private int searchLeftIconMarginRight;
    private int searchRightIconRes;
    private int searchRightIconColor;
    private int searchRightIconPadding;
    private int searchRightIconMarginLeft;
    private String searchEditHintText;
    private int searchEditTextHintColor;
    private String searchEditText;
    private float searchEditTextSize;
    private int searchEditTextColor;
    private int searchEditBgRes;

    private int searchIconRes;
    private int searchIconColor;
    private int searchIconPadding;
    private int searchIconMarginRight;


    private ImageView leftIconView;
    private TextView leftTextView;
    private TextView titleTextView;
    private ImageView rightSearchIcon;
    private ImageView rightIconView;
    private LinearLayout inputContainer;
    private EditText searchEdit;
    private ImageView searchArrowIcon;
    private ImageView searchClearIcon;

    private boolean searchOpened;                             //搜索框是否显示
    private boolean centerTextMarquee =true;                  // 中间TextView字体是否显示跑马灯效果
    private OnSearchActionListener onSearchActionListener;    //搜索框事件监听

    public ActionSearch(Context context) {
        this(context, null);
    }

    public ActionSearch(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionSearch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageView getLeftIconView() {
        return leftIconView;
    }

    public TextView getLeftTextView() {
        return leftTextView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public ImageView getrightSearchIconView() {
        return rightSearchIcon;
    }

    public ImageView getRightIconView() {
        return rightIconView;
    }

    public EditText getSearchEditView() {
        return searchEdit;
    }

    public ImageView getSearchBackIconView() {
        return searchArrowIcon;
    }

    public ImageView getSearchClearIconView() {
        return searchClearIcon;
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionSearch);

        float titleTextMaxWidthDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_title_text_max_width_def);
        float iconPadding = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_icon_padding);
        float iconPaddingDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_icon_padding_def);
        float textSizeDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_text_size_def);
        float textPaddingLeftDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_text_padding_left_def);
        float textPaddingRightDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_text_padding_right_def);
        float titleTextSizeDef = getContext().getResources().getDimension(R.dimen.actionbarex_common_title_bar_title_text_size_def);
        int iconColorDef = ContextCompat.getColor(getContext(), R.color.actionbarex_common_title_bar_icon_color_def);
        int textColorDef = ContextCompat.getColor(getContext(), R.color.actionbarex_common_title_bar_text_color_def);
        int titleTextColorDef = ContextCompat.getColor(getContext(), R.color.actionbarex_common_title_bar_title_text_color_def);
        int titleHintTextColorDef = ContextCompat.getColor(getContext(), R.color.actionbarex_common_title_bar_title_text_hint_color_def);

        leftTextClickToFinish = typedArray.getBoolean(R.styleable.ActionSearch_as_leftTextClickToFinish, false);
        leftIconClickToFinish = typedArray.getBoolean(R.styleable.ActionSearch_as_leftIconClickToFinish, false);
        leftText = typedArray.getString(R.styleable.ActionSearch_as_leftText);
        leftTextSize = typedArray.getDimension(R.styleable.ActionSearch_as_leftTextSize, textSizeDef);
        leftTextColor = typedArray.getColor(R.styleable.ActionSearch_as_leftTextColor, textColorDef);
        leftTextPaddingLeft = (int) typedArray.getDimension(R.styleable.ActionSearch_as_leftTextPaddingLeft, textPaddingLeftDef);
        leftTextPaddingRight = (int) typedArray.getDimension(R.styleable.ActionSearch_as_leftTextPaddingRight, textPaddingRightDef);
        leftIconRes = typedArray.getResourceId(R.styleable.ActionSearch_as_leftIconRes, 0);
        leftIconColor = typedArray.getColor(R.styleable.ActionSearch_as_leftIconColor, iconColorDef);
        leftIconPadding = (int) typedArray.getDimension(R.styleable.ActionSearch_as_leftIconPadding, iconPaddingDef);
        leftIconMarginLeft = (int) typedArray.getDimension(R.styleable.ActionSearch_as_leftIconMarginLeft, 0);


        searchLeftIconRes = typedArray.getResourceId(R.styleable.ActionSearch_as_searchLeftIconRes, 0);
        searchLeftIconColor = typedArray.getColor(R.styleable.ActionSearch_as_searchLeftIconColor, iconColorDef);
        searchLeftIconPadding = (int) typedArray.getDimension(R.styleable.ActionSearch_as_searchLeftIconPadding, iconPadding);
        searchLeftIconMarginRight = (int) typedArray.getDimension(R.styleable.ActionSearch_as_searchLeftIconMarginRight, 0);

        searchEditHintText = typedArray.getString(R.styleable.ActionSearch_as_searchEditHintText);
        searchEditTextHintColor = typedArray.getColor(R.styleable.ActionSearch_as_searchEditTextHintColor, 0);

        searchEditText = typedArray.getString(R.styleable.ActionSearch_as_searchEditText);
        searchEditTextSize = typedArray.getDimension(R.styleable.ActionSearch_as_searchEditTextSize, titleTextSizeDef);
        searchEditTextColor = typedArray.getColor(R.styleable.ActionSearch_as_searchEditTextColor, 0);

        searchRightIconRes = typedArray.getResourceId(R.styleable.ActionSearch_as_searchRightIconRes, 0);
        searchRightIconColor = typedArray.getColor(R.styleable.ActionSearch_as_searchRightIconColor, iconColorDef);
        searchRightIconPadding = (int) typedArray.getDimension(R.styleable.ActionSearch_as_searchRightIconPadding, iconPadding);
        searchRightIconMarginLeft = (int) typedArray.getDimension(R.styleable.ActionSearch_as_searchRightIconMarginLeft, 0);

        searchIconRes = typedArray.getResourceId(R.styleable.ActionSearch_as_searchIconRes, 0);
        searchIconColor = typedArray.getColor(R.styleable.ActionSearch_as_searchIconColor, iconColorDef);
        searchIconPadding = (int) typedArray.getDimension(R.styleable.ActionSearch_as_searchIconPadding, iconPadding);
        searchIconMarginRight = (int) typedArray.getDimension(R.styleable.ActionSearch_as_searchIconMarginRight, 0);

        searchEditBgRes = typedArray.getResourceId(R.styleable.ActionSearch_as_searchEditBgRes, 0);

        rightIconRes = typedArray.getResourceId(R.styleable.ActionSearch_as_rightIconRes, 0);
        rightIconColor = typedArray.getColor(R.styleable.ActionSearch_as_rightIconColor, iconColorDef);
        rightIconPadding = (int) typedArray.getDimension(R.styleable.ActionSearch_as_rightIconPadding, iconPaddingDef);
        rightIconMarginRight = (int) typedArray.getDimension(R.styleable.ActionSearch_as_rightIconMarginRight, 0);

        titleText = typedArray.getString(R.styleable.ActionSearch_as_titleText);
        titleTextSize = typedArray.getDimension(R.styleable.ActionSearch_as_titleTextSize, titleTextSizeDef);
        titleTextColor = typedArray.getColor(R.styleable.ActionSearch_as_titleTextColor, titleTextColorDef);
        titleTextMaxWidth = (int) typedArray.getDimension(R.styleable.ActionSearch_as_titleTextMaxWidth, titleTextMaxWidthDef);

        typedArray.recycle();
    }
    @SuppressLint("ResourceType")
    @Override
    protected View inflateTitleBar() {
        RelativeLayout titleBarChild = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.actionbarex_common_action_bar_title_search, getTitleBar(), false);

        leftIconView = titleBarChild.findViewById(R.id.actionbarex_search_iv_left);
        leftTextView = titleBarChild.findViewById(R.id.actionbarex_search_tv_left);
        titleTextView = titleBarChild.findViewById(R.id.actionbarex_search_tv_title);
        rightSearchIcon = titleBarChild.findViewById(R.id.actionbarex_search_icon);
        rightIconView = titleBarChild.findViewById(R.id.actionbarex_search_iv_right);

        inputContainer = titleBarChild.findViewById(R.id.actionbarex_search_ll_inputContainer);
        searchEdit = titleBarChild.findViewById(R.id.actionbarex_search_editText);
        searchClearIcon =titleBarChild.findViewById(R.id.actionbarex_search_iv_clear);
        searchArrowIcon =titleBarChild.findViewById(R.id.actionbarex_search_iv_arrow);

        if (searchEditBgRes > 0) {
            inputContainer.setBackgroundResource(searchEditBgRes);
        } else {
            inputContainer.setBackground(null);
        }

        //左边图标
        LinearLayout.LayoutParams leftIconViewParams = (LinearLayout.LayoutParams) leftIconView.getLayoutParams();
        leftIconViewParams.leftMargin = leftIconMarginLeft;
        leftIconView.setLayoutParams(leftIconViewParams);
        if (leftIconRes > 0) {
            leftIconView.setVisibility(VISIBLE);
            leftIconView.setPadding(leftIconPadding, leftIconPadding, leftIconPadding, leftIconPadding);
            leftIconView.setImageResource(leftIconRes);
            leftIconView.setColorFilter(leftIconColor);
            if (leftIconClickToFinish) {
                leftIconView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        } else {
            leftIconView.setVisibility(GONE);
        }
        //左边文字
        if (!TextUtils.isEmpty(leftText)) {
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(leftText);
            leftTextView.setTextColor(leftTextColor);
            leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
            leftTextView.setPadding(leftTextPaddingLeft, 0, leftTextPaddingRight, 0);
            if (leftTextClickToFinish) {
                leftTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishActivity();
                    }
                });
            }
        } else {
            leftTextView.setVisibility(GONE);
        }

        //中间title
        titleTextView.setVisibility(VISIBLE);
        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        titleTextView.setMaxWidth(titleTextMaxWidth);


        //输入框弹出，返回图标 不设置使用默认图标
        LinearLayout.LayoutParams searchArrowViewParams = (LinearLayout.LayoutParams) searchArrowIcon.getLayoutParams();
        searchArrowViewParams.rightMargin = searchLeftIconMarginRight;
        rightSearchIcon.setLayoutParams(searchArrowViewParams);
        if (searchLeftIconRes > 0) {
            rightSearchIcon.setVisibility(VISIBLE);
            rightSearchIcon.setPadding(searchLeftIconPadding, searchLeftIconPadding, searchLeftIconPadding, searchLeftIconPadding);
            rightSearchIcon.setImageResource(searchLeftIconRes);
            rightSearchIcon.setColorFilter(searchLeftIconColor);
        } else {
            rightSearchIcon.setVisibility(VISIBLE);
            rightSearchIcon.setPadding(searchLeftIconPadding, searchLeftIconPadding, searchLeftIconPadding, searchLeftIconPadding);
            rightSearchIcon.setImageResource(R.drawable.arrow_left_black_36dp);
            rightSearchIcon.setColorFilter(searchLeftIconColor);
        }

        //输入框弹出提示文字
        if (!TextUtils.isEmpty(searchEditHintText)) {
            searchEdit.setHint(searchEditHintText);
        } else {
            searchEdit.setHint(R.string.actionbarex_title_bar_title_text_hint_def);
        }
        if(searchEditTextHintColor!=0){
            searchEdit.setHintTextColor(searchEditTextHintColor);
        }
        if (!TextUtils.isEmpty(searchEditText)) {
            searchEdit.setText(searchEditText);
        }
        if(searchEditTextColor!=0){
            searchEdit.setTextColor(searchEditTextColor);
        }
        searchEdit.setTextSize(TypedValue.COMPLEX_UNIT_PX, searchEditTextSize);


        //输入框弹出，清除内容图标 不设置使用默认图标
        LinearLayout.LayoutParams searchClearViewParams = (LinearLayout.LayoutParams) searchClearIcon.getLayoutParams();
        searchClearViewParams.rightMargin = searchRightIconMarginLeft;
        rightSearchIcon.setLayoutParams(searchClearViewParams);
        if (searchRightIconRes > 0) {
            rightSearchIcon.setVisibility(VISIBLE);
            rightSearchIcon.setPadding(searchRightIconPadding, searchRightIconPadding, searchRightIconPadding, searchRightIconPadding);
            rightSearchIcon.setImageResource(searchRightIconRes);
            rightSearchIcon.setColorFilter(searchRightIconColor);
        } else {
            rightSearchIcon.setVisibility(VISIBLE);
            rightSearchIcon.setPadding(searchRightIconPadding, searchRightIconPadding, searchRightIconPadding, searchRightIconPadding);
            rightSearchIcon.setImageResource(R.drawable.outline_close_black_36dp);
            rightSearchIcon.setColorFilter(searchRightIconColor);
        }

        //搜索图标 不设置使用默认图标
        LinearLayout.LayoutParams searchIconViewParams = (LinearLayout.LayoutParams) rightSearchIcon.getLayoutParams();
        searchIconViewParams.rightMargin = searchIconMarginRight;
        rightSearchIcon.setLayoutParams(searchIconViewParams);
        if (searchIconRes > 0) {
            rightSearchIcon.setVisibility(VISIBLE);
            rightSearchIcon.setPadding(searchIconPadding, searchIconPadding, searchIconPadding, searchIconPadding);
            rightSearchIcon.setImageResource(searchIconRes);
            rightSearchIcon.setColorFilter(searchIconColor);
        } else {
            rightSearchIcon.setVisibility(VISIBLE);
            rightSearchIcon.setPadding(searchIconPadding, searchIconPadding, searchIconPadding, searchIconPadding);
            rightSearchIcon.setImageResource(R.drawable.outline_search_black_36dp);
            rightSearchIcon.setColorFilter(searchIconColor);
        }

        //右边图标
        LinearLayout.LayoutParams rightIconViewParams = (LinearLayout.LayoutParams) rightIconView.getLayoutParams();
        rightIconViewParams.rightMargin = rightIconMarginRight;
        rightIconView.setLayoutParams(rightIconViewParams);
        if (rightIconRes > 0) {
            rightIconView.setVisibility(VISIBLE);
            rightIconView.setPadding(rightIconPadding, rightIconPadding, rightIconPadding, rightIconPadding);
            rightIconView.setImageResource(rightIconRes);
            rightIconView.setColorFilter(rightIconColor);
        } else {
            rightIconView.setVisibility(GONE);
        }

        rightSearchIcon.setOnClickListener(this);
        searchClearIcon.setOnClickListener(this);
        searchArrowIcon.setOnClickListener(this);
        searchEdit.setOnEditorActionListener(this);
        return titleBarChild;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == searchClearIcon.getId()) {
            searchEdit.setText("");
        } else if (id == searchArrowIcon.getId()) {
            closeSearch();
        }else if (id == rightSearchIcon.getId()) {
            openSearch();
        }
    }

    public void setOnLeftIconClickListener(final OnActionBarChildClickListener onLeftIconClickListener) {
        leftIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftIconClickListener != null) {
                    onLeftIconClickListener.onClick(v);
                }
            }
        });
    }

    public void setOnLeftTextClickListener(final OnActionBarChildClickListener onLeftTextClickListener) {
        leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftTextClickListener != null) {
                    onLeftTextClickListener.onClick(v);
                }
            }
        });
    }



    public void setOnRightIconClickListener(final OnActionBarChildClickListener onRightIconClickListener) {
        rightIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (onRightIconClickListener != null) {
                    onRightIconClickListener.onClick(v);
                }
            }
        });
    }

    public boolean isSearchOpened() {
        return searchOpened;
    }
    /**
     * Hides search input and close arrow
     */
    public void closeSearch() {
       //  animateNavIcon(false);
        if (listenerExists()) {
            onSearchActionListener.onSearchStateChanged(false);
        }
        searchOpened = false;
        Animation out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        Animation in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_right);
        Animation left_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_left);
        out.setAnimationListener(this);
        inputContainer.startAnimation(out);

        if (titleText != null) {
            titleTextView.setVisibility(VISIBLE);
            titleTextView.startAnimation(in);
        }
        rightSearchIcon.setVisibility(VISIBLE);
        rightSearchIcon.startAnimation(left_in);
        if (rightIconRes > 0) {
            rightIconView.setVisibility(VISIBLE);
            rightIconView.startAnimation(left_in);
        } else {
            rightIconView.setVisibility(GONE);
        }
        if (leftIconRes > 0) {
            leftIconView.setVisibility(VISIBLE);
            leftIconView.startAnimation(in);
        } else {
            leftIconView.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(leftText)) {
            leftTextView.setVisibility(VISIBLE);
            leftIconView.startAnimation(in);
        } else {
            leftTextView.setVisibility(GONE);
        }
    }

    /**
     * Shows search input and close arrow
     */
    public void openSearch() {
        if (isSearchOpened()) {
            searchEdit.requestFocus();
            return;
        }
        if (listenerExists()) {
            onSearchActionListener.onSearchStateChanged(true);
        }
        searchOpened = true;
        Animation left_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_left);
        Animation left_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out_left);
        left_in.setAnimationListener(this);
        inputContainer.setVisibility(VISIBLE);
        inputContainer.startAnimation(left_in);
        rightSearchIcon.startAnimation(left_out);

        titleTextView.setVisibility(GONE);
        rightIconView.setVisibility(GONE);
        leftIconView.setVisibility(GONE);
        leftTextView.setVisibility(GONE);
    }

     private void animateNavIcon(boolean menuState) {
        if (menuState) {
            this.rightIconView.setImageResource(leftIconRes);
        } else {
            this.rightIconView.setImageResource(R.drawable.arrow_left_black_36dp);
        }
        Drawable mDrawable = rightIconView.getDrawable();
        if (mDrawable instanceof Animatable) {
            ((Animatable) mDrawable).start();
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (!searchOpened) {
            inputContainer.setVisibility(GONE);
            searchEdit.setText("");
            onFocusChange(searchEdit,false);

        } else {
            rightSearchIcon.setVisibility(GONE);
            searchEdit.requestFocus();
            onFocusChange(searchEdit,true);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (hasFocus) {
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (listenerExists()){
            onSearchActionListener.onSearchConfirmed(searchEdit.getText());
        }
        closeSearch();
        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && searchOpened) {
            closeSearch();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    //是否设置了监听
    private boolean listenerExists() {
        return onSearchActionListener != null;
    }
    public void setOnSearchActionListener(OnSearchActionListener onSearchActionListener) {
        this.onSearchActionListener = onSearchActionListener;
    }


    public interface OnSearchActionListener {
        /**
         * 搜索框显示隐藏
         * @param enabled state
         */
        void onSearchStateChanged(boolean enabled);

        //搜索框搜索内容
        void onSearchConfirmed(CharSequence text);
    }

}
