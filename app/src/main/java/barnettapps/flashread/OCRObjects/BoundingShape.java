package barnettapps.flashread.OCRObjects;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class BoundingShape {

    private static final String LOG_TAG = "BoundingShape";

    Boolean selected;
    int[] cornersPoints;
    Paint paintSelected;
    Paint paintUnSelected;

    public BoundingShape(int [] cornersPoints, boolean selected){
        this.cornersPoints = cornersPoints;
        paintSelected = setPaintSelected();
        paintUnSelected = setPaintNotSelected();
        this.selected = selected;
    }

    public BoundingShape(int [] cornersPoints){
        this.cornersPoints = cornersPoints;
        paintSelected = setPaintSelected();
        paintUnSelected = setPaintNotSelected();
    }

    private Paint setPaintNotSelected(){
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(4.0f);
        return rectPaint;
    }
    private Paint setPaintSelected(){
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(4.0f);
        return rectPaint;
    }


}
