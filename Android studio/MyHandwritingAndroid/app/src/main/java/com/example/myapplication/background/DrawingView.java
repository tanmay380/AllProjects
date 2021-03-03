package com.example.myapplication.background;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.infront.SplashScreen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Stack;

public class DrawingView extends View {
    private Path mDrawPath;
    private ArrayList<Path> undoPath= new ArrayList<>();

    private DisplayMetrics displayMetrics;
    /**
     * The paint used to draw the touches
     */
    private Paint mDrawPaint;

    private Paint mCanvasPaint;  //The paints used to draw on the canvas
    /**
     * The canvas of the view
     */
    private Canvas mDrawCanvas;

    /**
     * The bitmap that is set
     */
    public Bitmap mCanvasBitmap;

    /**
     * Vibrator instance to vibrate if the user traces outside the boundary of the string
     */
    private Vibrator mVibrator;

    private boolean mVibrate;
    private long mVibrationStartTime;
    /**
     * number of wrong touches
     */
    private long mWrongTouches;

    /**
     * number of correct touches
     */
    private long mCorrectTouches;

    /**
     * Boolean variable that enables drawing
     */
    private boolean mDraw;
    private int minX, minY, maxX, maxY;

    /**
     * List of strokes. Each ArrayList<Point> is the touches from one MOTION_DOWN even to a MOTION_UP even
     */
    private ArrayList<ArrayList<Point>> mTouchPoints;

    /**
     * The context of the view
     */
    private Context mContext;

    /**
     * View width
     */
    public int mWidth;//=MainActivity.getwidht();

    public int mHeight;

    // private ArrayList<Integer> widths= new ArrayList<>();
    private int currentWidht;
    private boolean candraw = true;
    private Boolean erase = false;
    private Boolean mScoring;
    private Toast mErrorToast;


    public DrawingView(Context context) {
        super(context);

        init(context);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        //context based init goes here
        mContext = context;
        //vibrate
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        //Getting display width and
        mHeight = SplashScreen.displayMetrics.heightPixels;
        mWidth = SplashScreen.displayMetrics.widthPixels;

        ((Activity)mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mErrorToast = new Toast(mContext);
                mErrorToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, mHeight / 4);
                mErrorToast.setDuration(Toast.LENGTH_SHORT);
                mErrorToast.setView(((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.toast_layout_root)));
            }
        });

        //initializing without context
        init();
    }


    /**
     * Function to initialize all the variables of the class that do not require a context
     */
    public void init() {
        //get drawing area setup for interaction
        int mTouchColour = getResources().getColor(R.color.Red);
        currentWidht=80;

        mDrawPath = new Path();
        mDrawPaint = new Paint();
        mDrawPaint.setColor(mTouchColour);
        mDrawPaint.setAntiAlias(true);
        mDrawCanvas=new Canvas();

        //Setting the paint to draw round strokes
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        mDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        mDrawPaint.setStrokeWidth(currentWidht);
        mCanvasPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCorrectTouches = 0;
        mWrongTouches = 0;
        mDraw = true;

        minX = mWidth;
        maxX = -1;
        minY = mHeight;
        maxY = -1;

        mVibrate = true;
        mScoring=true;

        mTouchPoints = new ArrayList<>(); //Empty list as no touches yet

        System.gc();
        if (mCanvasBitmap != null) {
            mCanvasBitmap.recycle();
            mCanvasBitmap = null;
            mDrawCanvas = null;
        }
        mCanvasBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_4444);
        mDrawCanvas = new Canvas(mCanvasBitmap);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(mCanvasBitmap, 0, 0, mCanvasPaint);
        canvas.drawPath(mDrawPath, mDrawPaint);
        mDrawPaint.setStrokeWidth(currentWidht);
    }

    public void setcolor(int color){
        mDrawPaint.setColor(color);
    }

    public void setBitmapFromText(String str) {
        init();

        Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.BLACK);
        paintText.setStyle(Paint.Style.FILL);
        int size = SplashScreen.displayMetrics.densityDpi / str.length();//Starting size of the text
        float textHeight;
        do {
            paintText.setTextSize(++size);
            textHeight = paintText.descent() - paintText.ascent();

        } while (paintText.measureText(str) < mWidth * .8 && textHeight < mHeight * .8);//setting the max size of the text for the given screen
        mDrawPaint.setStrokeWidth(size * 3 / 182); //values got from experimenting

        float textOffset = textHeight / 2 - paintText.descent();
        //Drawing the text at the center of the view
        mDrawCanvas.drawText(str, (mWidth - paintText.measureText(str)) / 2, (mHeight / 2) + textOffset, paintText);
        invalidate();
    }

    public void setBitmap(Bitmap b) {
        init();
        //Drawing the bitmap at the center of the view
        mDrawCanvas.drawBitmap(b, (mWidth - b.getWidth()) / 2, (mHeight - b.getHeight()) / 2, mCanvasPaint);
        invalidate();
    }
    public void canerase(Boolean iserasing) {
        erase = iserasing;

        if (erase) {

                mDrawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        } else
            mDrawPaint.setXfermode(null);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (candraw) {
            float touchX = event.getX();
            float touchY = event.getY();
            // mapping screen touch co-ordinates to image pixel co-ordinates
            int x = (int) (touchX * mCanvasBitmap.getWidth() / mWidth);
            int y = (int) (touchY * mCanvasBitmap.getHeight() / mHeight);

            //updating the touch bounds
            if (x < minX)
                minX = x;
            if (x > maxX)
                maxX = x;
            if (y < minY)
                minY = y;
            if (y > maxY)
                maxY = y;

            if(mScoring) {
                //checking if the touches are correct or wrong (inside or outside the boundary
                if ((x >= 0 && x < mWidth && y >= 0 && y < mHeight && mCanvasBitmap.getPixel(x, y) == Color.TRANSPARENT) || (x < 0 || x >= mWidth || y < 0 || y >= mHeight)) {
                    mWrongTouches++;
                    if (mVibrate) {//Device will vibrate only if mVibrate is true
                        mVibrator.vibrate(100);
                        if (mVibrationStartTime == 0) {
                            mVibrationStartTime = new Date().getTime();
                            mErrorToast.cancel();
                        } else if (new Date().getTime() - mVibrationStartTime > 1000 && mErrorToast.getView().getWindowVisibility() != View.VISIBLE) {
                            mErrorToast.show();
                        }
                    }
                } else {
                    mVibrationStartTime = 0;
                    mCorrectTouches++;
                }
            }


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDrawPath.moveTo(touchX, touchY);
                    mTouchPoints.add(new ArrayList<Point>());//ACTION_DOWN event means a new stroke so a new ArrayList
                    break;
                case MotionEvent.ACTION_MOVE:
                    mDrawPath.lineTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    mVibrationStartTime = 0;
                    mDrawCanvas.drawPath(mDrawPath, mDrawPaint);
                    //paths.push(new Path(mDrawPath));

                    mDrawPath.reset();//End of the current stroke
                    invalidate();
                    break;
                default:
                    return false;
            }
            //Adding the touch point to the last ArrayList
            mTouchPoints.get(mTouchPoints.size() - 1).add(new Point((int) touchX, (int) touchY));
            invalidate();
        }

        return true;
    }
    public float score() {
        return (mCorrectTouches + mWrongTouches !=0)?100* mCorrectTouches /(mCorrectTouches + mWrongTouches):0;
    }
    public void canScore(boolean scoring){
        mScoring = scoring;
    }

    public Bitmap getBitmap() {
        Bitmap overlayBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_4444);
        overlayBitmap.eraseColor(getResources().getColor(R.color.AppBg));
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.drawBitmap(mCanvasBitmap,0,0,null);
        return overlayBitmap;
    }

    public void candraw(boolean draw) {
        candraw = draw;
    }

    public void canVibrate(boolean vibrate) {
        mVibrate = vibrate;
    }

    public void setCurrentWidth(int width) {
        currentWidht = (width + 1) * 7;
        //Toast.makeText(getContext(),Integer.toString(currentWidht),Toast.LENGTH_SHORT).show();
    }
    public String saveBitmap(String practiceString, String dirExtra) {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + dirExtra);
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            return "Could not save trace. Unable to create directory";
        } else {//Compress the bitmap and then store it
            File file;
            if (!dirExtra.equals(""))
                file = new File(mediaStorageDir.getPath() + File.separator + practiceString + "_" + String.valueOf(score()) + ".jpg");
            else
                file = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + "_" + practiceString + ".jpg");

            int actualHeight = mHeight;
            int actualWidth = mWidth;

            float maxHeight = 800.0f;
            float maxWidth = 600.0f;
            float imgRatio = actualWidth / actualHeight;
            float maxRatio = maxWidth/maxHeight;

            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight;
                    actualWidth = (int) (imgRatio * actualWidth);
                    actualHeight = (int) maxHeight;
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth;
                    actualHeight = (int) (imgRatio * actualHeight);
                    actualWidth = (int) maxWidth;
                } else {
                    actualHeight = (int) maxHeight;
                    actualWidth = (int) maxWidth;
                }
            }
            Bitmap scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_4444);

            float ratioX = actualWidth / (float) mWidth;
            float ratioY = actualHeight / (float) mHeight;
            float middleX = actualWidth / 2.0f;
            float middleY = actualHeight / 2.0f;

            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(getBitmap(), middleX - mWidth / 2, middleY - mHeight / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

            ByteArrayOutputStream tempStream = new ByteArrayOutputStream();
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, tempStream);

            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight());

            try{
                FileOutputStream fOut = new FileOutputStream(file);
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                fOut.flush();
                fOut.close();
                return file.getPath();
            } catch (FileNotFoundException e) {
                file.delete();
                return "Could not save trace. Unable to open file";
            } catch (IOException e) {
                file.delete();
                return "Could not save trace. Unable to save file";
            }
        }
    }

    /**
     * Function to release the memory used by the DrawingView
     */
    void destroyBitmap() {
        if(mCanvasBitmap!=null) {
            mCanvasBitmap.recycle();
            mCanvasBitmap = null;
            mDrawCanvas = null;
        }
    }
}