package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.config.ad.AdToBs;
import com.config.bs.BsToAd;

public class DateConvert {

static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

public DateConvert() {
}

public static String now() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //hh:mm H");
    return df.format(new Date());
}

public static String today() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return df.format(new Date());
}

public static Date toDate(String date) {

    try {
        return df.parse(date);
    } catch (ParseException e) {
    }
    return null;
}

public static String toString(Date date) {
    return df.format(date);
}

public static String adtobsDate(Date date) {
    return new AdToBs().convet(df.format(date));

}

public static String adtobsDate(String date) {
    return new AdToBs().convet(date);

}

public static String bsToAd(String date) {
    return new BsToAd().convet(date);

}

public static Date bsToAdDate(String date) {
    return toDate(new BsToAd().convet(date));

}

public static String getTime(Date date) {
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    return df.format(date);

}

public static String toStringDateTime(Date date) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(date);
}
}
