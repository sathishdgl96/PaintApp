package com.example.paintfinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import static com.example.paintfinal.R.color.purple_700;

public class DrawingView extends View
{
    private static final int TOUCH_TOLERANCE = 3;
    Paint mPaint;
    float mx,my;
    String shape="circle";
    float mStartX,mStartY;
    boolean isDrawing=false;
    protected Bitmap mBitmap;
    protected Canvas mCanvas;
    protected Canvas canvas;
    Rect button = new Rect(); // Define the dimensions of the button here
    Rect buttonsq=new Rect();
    boolean buttonClicked;
    Rect buttonTriangle=new Rect();
    boolean buttonTriangleClicked;
    Rect buttonLine=new Rect();
    boolean buttonLineClicked;
    Rect buttonCircle=new Rect();
    Rect colorGreen=new Rect();
    Rect colorBlue=new Rect();
    Rect colorRed=new Rect();
    Rect clrrect=new Rect();
    boolean buttonSqClicked;
    boolean buttonCircleClicked;
    Paint paintBrush,paintStroke;
    int countTouch =0;
    float basexTriangle =0;
    float baseyTriangle =0;
    public DrawingView(Context context) {
        super(context);
        init();
    }

    protected void init() {
        mPaint = new Paint(Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(30);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        if (button.contains((int)event.getX(), (int)event.getY())) {
            buttonClicked = true;
            shape="rectangle";
            Toast.makeText(getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
            System.out.println("button clicked");
        }
       else if (buttonsq.contains((int)event.getX(), (int)event.getY())) {
            buttonSqClicked = true;
            shape = "square";
            Toast.makeText(getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (buttonLine.contains((int)event.getX(), (int)event.getY())) {
            buttonLineClicked = true;
            shape = "line";
            Toast.makeText(getContext(), "Line Button Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (buttonTriangle.contains((int)event.getX(), (int)event.getY())) {
            buttonTriangleClicked = true;
            shape = "triangle";
            Toast.makeText(getContext(), "Triangle Button Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (buttonCircle.contains((int)event.getX(), (int)event.getY())) {
            buttonCircleClicked = true;
            shape = "circle";
            Toast.makeText(getContext(), "Triangle Button Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (colorGreen.contains((int)event.getX(), (int)event.getY())) {
            mPaint.setColor(Color.GREEN);
            mPaint.setStyle(Paint.Style.STROKE);
            Toast.makeText(getContext(), "Green Color Selected", Toast.LENGTH_SHORT).show();
        }
        else if (colorBlue.contains((int)event.getX(), (int)event.getY())) {
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.STROKE);
            Toast.makeText(getContext(), "Blue Color Selected", Toast.LENGTH_SHORT).show();
        }
        else if (colorRed.contains((int)event.getX(), (int)event.getY())) {
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.STROKE);
            Toast.makeText(getContext(), "Red Color Selected", Toast.LENGTH_SHORT).show();
        }

        else if (clrrect.contains((int)event.getX(), (int)event.getY())) {
            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.FILL);
            Toast.makeText(getContext(), "All drawing cleared", Toast.LENGTH_SHORT).show();
        }

        else {
            buttonCircleClicked =true;
        }

        mx = event.getX();
        my = event.getY();
        switch (shape) {
            case "rectangle":
                onTouchEventRectangle(event);
                break;
            case "square":
                onTouchEventSquare(event);
                break;
            case "circle":
                onTouchEventCircle(event);
                break;
            case "line":
                onTouchEventLine(event);
                break;
            case "triangle":
                onTouchEventTriangle(event);
                break;
            default:
                Toast.makeText(getContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintBrush=new Paint();
        paintBrush.setColor(Color.RED);
        paintBrush.setStyle(Paint.Style.FILL);
        paintStroke=new Paint();
        paintStroke.setColor(Color.RED);
        paintStroke.setStyle(Paint.Style.STROKE);
        button=new Rect();
        button.set(10,10,150,100);
        canvas.drawRect(button,paintBrush);
        //invalidate();
        buttonsq=new Rect();
        buttonsq.set(180,10,280,100);
        buttonLine=new Rect();
        buttonLine.set(300,10,400,100);
        buttonTriangle=new Rect();
        buttonTriangle.set(430,10,530,100);
        buttonCircle=new Rect();
        buttonCircle.set(550,10,650,100);
        colorGreen.set(700,10,850,100);
        colorRed.set(900,10,1050,100);
        canvas.drawRect(colorRed,paintBrush);
        canvas.drawLine(320,5,390,80,paintStroke);
        canvas.drawCircle(600,35,30,paintStroke);
        canvas.drawRect(buttonLine,paintStroke);
        canvas.drawRect(buttonTriangle,paintStroke);
        canvas.drawRect(buttonsq,paintBrush);
        canvas.drawRect(buttonCircle,paintStroke);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        paintBrush.setColor(Color.GREEN);
        canvas.drawRect(colorGreen,paintBrush);
        colorBlue.set(900,150,1050,250);
        paintBrush.setColor(Color.BLUE);
        canvas.drawRect(colorBlue,paintBrush);

        paintBrush.setColor(Color.BLACK);
        clrrect.set(700,150,850,250);
        canvas.drawRect(clrrect,paintBrush);

        if (isDrawing){
            switch (shape) {
                case "rectangle":
                    onDrawRectangle(canvas);
                    break;
                case "square":
                    onDrawSquare(canvas);
                    break;
                case "circle":
                    onDrawCircle(canvas);
                    break;
                case "line":
                    onDrawLine(canvas);
                    break;
                case "triangle":
                    onDrawTriangle(canvas);
                    break;
                default:
                    Toast.makeText(getContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //------------------------------------------------------------------
    // Rectangle
    //------------------------------------------------------------------

    private void onTouchEventRectangle(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = mx;
                mStartY = my;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                drawRectangle(mCanvas,mPaint);
                invalidate();
                break;
        }
    }

    private void onDrawRectangle(Canvas canvas) {
        drawRectangle(canvas,mPaint);
    }
    private void onDrawCircle(Canvas canvas){
        canvas.drawCircle(mStartX, mStartY, calculateRadius(mStartX, mStartY, mx, my), mPaint);
    }

    private void onTouchEventCircle(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = mx;
                mStartY = my;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                mCanvas.drawCircle(mStartX, mStartY,
                        calculateRadius(mStartX,mStartY,mx,my), mPaint);
                invalidate();
                break;
        }
    }
    protected float calculateRadius(float x1, float y1, float x2, float y2) {

        return (float) Math.sqrt(
                Math.pow(x1 - x2, 2) +
                        Math.pow(y1 - y2, 2)
        );
    }


    private void drawRectangle(Canvas canvas, Paint paint){
        float right = mStartX > mx ? mStartX : mx;
        float left = mStartX > mx ? mx : mStartX;
        float bottom = mStartY > my ? mStartY : my;
        float top = mStartY > my ? my : mStartY;
        canvas.drawRect(left, top , right, bottom, paint);
    }



    private void onDrawSquare(Canvas canvas) {
        onDrawRectangle(canvas);
    }

    private void onTouchEventSquare(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = mx;
                mStartY = my;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                adjustSquare(mx, my);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                adjustSquare(mx, my);
                drawRectangle(mCanvas,mPaint);
                invalidate();
                break;
        }
    }

    /**
     * Adjusts current coordinates to build a square
     * @param x
     * @param y
     */
    protected void adjustSquare(float x, float y) {
        float deltaX = Math.abs(mStartX - x);
        float deltaY = Math.abs(mStartY - y);

        float max = Math.max(deltaX, deltaY);

        mx = mStartX - x < 0 ? mStartX + max : mStartX - max;
        my = mStartY - y < 0 ? mStartY + max : mStartY - max;
    }

    private void onDrawLine(Canvas canvas) {

        float dx = Math.abs(mx - mStartX);
        float dy = Math.abs(my - mStartY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            canvas.drawLine(mStartX, mStartY, mx, my, mPaint);
        }
    }

    private void onTouchEventLine(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = mx;
                mStartY = my;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                mCanvas.drawLine(mStartX, mStartY, mx, my, mPaint);
                invalidate();
                break;
        }
    }
    private void onDrawTriangle(Canvas canvas){

        if (countTouch<3){
            canvas.drawLine(mStartX,mStartY,mx,my,mPaint);
        }else if (countTouch==3){
            canvas.drawLine(mx,my,mStartX,mStartY,mPaint);
            canvas.drawLine(mx,my,basexTriangle,baseyTriangle,mPaint);
        }
    }
    private void onTouchEventTriangle(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                countTouch++;
                if (countTouch==1){
                    isDrawing = true;
                    mStartX = mx;
                    mStartY = my;
                } else if (countTouch==3){
                    isDrawing = true;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                countTouch++;
                isDrawing = false;
                if (countTouch<3){
                    basexTriangle=mx;
                    baseyTriangle=my;
                    mCanvas.drawLine(mStartX,mStartY,mx,my,mPaint);
                } else if (countTouch>=3){
                    mCanvas.drawLine(mx,my,mStartX,mStartY,mPaint);
                    mCanvas.drawLine(mx,my,basexTriangle,baseyTriangle,mPaint);
                    countTouch =0;
                }
                invalidate();
                break;
        }
    }

    }


