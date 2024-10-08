package za.ac.cput.novacinemaapp.service;
import  za.ac.cput.novacinemaapp.domain.Review;
import java.util.Set;

/*  IReviewService.java
 *   Service Interface for Review
 *   Author: Musaddiq McWhite (219369151)
 *   19 May 2024
 * */

public interface IReviewService extends IService<Review, Long> {
    Set<Review> getAll();
}
