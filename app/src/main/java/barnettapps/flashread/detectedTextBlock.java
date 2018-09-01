package barnettapps.flashread;

import android.graphics.Color;
import android.graphics.Paint;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

public class detectedTextBlock {

    boolean clicked;
    FirebaseVisionText.TextBlock textblock;
    Paint color;

    public detectedTextBlock(FirebaseVisionText.TextBlock inputblock){
        this.textblock = inputblock;
        this.clicked = false;
        this.color = getPaintNotClicked();
    }

    public Paint getColor(){
        if (clicked){ return getPaintClicked(); }
        else{ return getPaintNotClicked(); }
    }

    public Paint getPaintNotClicked(){
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(4.0f);
        return rectPaint;
    }
    public Paint getPaintClicked(){
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(4.0f);
        return rectPaint;
    }

    public void setColor( Paint color){
        this.color = color;
    }

    public Paint toggleClicked(){
        if (clicked){
            clicked = false;
        } else {
            clicked = true;
        }
        return color;
    }

}
