package data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestObj {
    private Double x;
    private Double y;
    private Double r;
    private boolean res;
    long microseconds;
    String date;

    public void setX (double x) {this.x = x;}

    public void setY (double y) {this.y = y;}

    public void setR (double r) {this.r = r;}

    public void setDate() {
        Date dateNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/y hh:mm:ss a");
        this.date = sdf.format(dateNow);
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }

    public double getX() {return x;}

    public double getY() {return y;}

    public double getR() {return r;}

    public String getDate() {return date;}

    public String getMicroseconds() {return String.format("%.6f",(double) microseconds/10000000);}

    public boolean getRes() {return res;}

    public void check_hit() {
        long start = System.nanoTime();
        if (x == null || y == null || r == null) throw new NullPointerException();
        if (((x >= 0) && (y >= 0) && (x + y <= r))
                || ((x <= 0) && (y >= 0))
                || ((x <= 0) && (y <= 0) && ((x * x) + (y * y) <= r * r))) res = true;
        else res=false;
        long end = System.nanoTime();
        setMicroseconds(end-start);
    }
}
