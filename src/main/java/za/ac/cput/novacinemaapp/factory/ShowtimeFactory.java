package za.ac.cput.novacinemaapp.factory;

import za.ac.cput.novacinemaapp.domain.*;
import za.ac.cput.novacinemaapp.util.Helper;
import java.time.LocalTime;

/*  ShowtimeFactory.java
 *   Factory class for Showtime
 *   Author: Musaddiq McWhite (219369151)
 *   17 May 2024
 * */
public class ShowtimeFactory {

    public static Showtime buildShowtime(LocalTime showtime, LocalTime endTime, Movie movie) {
        if (Helper.isNullOrEmpty(showtime) || Helper.isNullOrEmpty(endTime) || Helper.isNullOrEmpty(movie))
            return null;

        return new Showtime.Builder().setShowtime(showtime)
                .setEndTime(endTime)
                .setMovie(movie)
                .build();
    }
}
