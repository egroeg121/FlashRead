package barnettapps.flashread;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

public class detectedTextBlock {

    boolean clicked;
    String text;
    Point[] cornerPoints;
    Rect boundingBox;



    public detectedTextBlock(FirebaseVisionText.TextBlock inputblock){
        this.text = inputblock.getText();
        this.clicked = false;
        this.cornerPoints = inputblock.getCornerPoints();
        this.boundingBox = inputblock.getBoundingBox();
    }



    public void setClicked( Boolean cliceked){
        this.clicked = cliceked;
    }

    public Boolean toggleClicked(){
        if (clicked){
            clicked = false;
        } else {
            clicked = true;
        }
        return clicked;
    }

    public boolean isClicked() {
        return clicked;
    }
    public String getText() {
        return text;
    }
    public Point[] getCornerPoints() {
        return cornerPoints;
    }
    public Rect getBoundingBox() {
        return boundingBox;
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
}

