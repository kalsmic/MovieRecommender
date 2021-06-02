# Movie Recommender

[![Build Status](https://travis-ci.com/kalsmic/MovieRecommender.svg?branch=master)](https://travis-ci.com/kalsmic/MovieReccomender)

## Programming Exercise: Step One

Specifically for this assignment you will write the following methods in a new class named FirstRatings:

- Write a method named loadMovies that has one parameter, a String named filename. This method should process every
  record from the CSV file whose name is filename, a file of movie information, and return an ArrayList of type Movie
  with all the movie data from the file.

- Write a void method named testLoadMovies that should do several things.

- Call the method loadMovies on the file ratedmovies_short.csv and store the result in an ArrayList local variable .
  Print the number of movies, and print each movie. You should see there are five movies in this file, which are all
  shown above. After this works you should comment out the printing of the movies. If you run your program on the file
  ratedmoviesfull.csv, you should see there are 3143 movies in the file.
- Add code to determine how many movies include the Comedy genre. In the file ratedmovies_short.csv, there is only one.
- Add code to determine how many movies are greater than 150 minutes in length. In the file ratedmovies_short.csv, there
  are two.
- Add code to determine the maximum number of movies by any director, and who the directors are that directed that many
  movies. Remember that some movies may have more than one director. In the file ratedmovies_short.csv the maximum
  number of movies by any director is one, and there are five directors that directed one such movie.
- In the FirstRatings class, write a method named loadRaters that has one parameter named filename. This method should
  process every record from the CSV file whose name is filename, a file of raters and their ratings, and return an
  ArrayList of type Rater with all the rater data from the file.

- Write a void method named testLoadRaters that should do several things.
- Call the method loadRaters on the file ratings_short.csv and store the result in a local ArrayList variable. Print the
  total number of raters. Then for each rater, print the rater’s ID and the number of ratings they did on one line,
  followed by each rating (both the movie ID and the rating given) on a separate line. If you run your program on the
  file ratings_short.csv you will see there are five raters. After it looks like it works, you may want to comment out
  the printing of each rater and their ratings. If you run your program on the file ratings.csv, you should get 1048
  raters.
- Add code to find the number of ratings for a particular rater you specify in your code. For example, if you run this
  code on the rater whose rater_id is 2 for the file ratings_short.csv, you will see they have three ratings.
- Add code to find the maximum number of ratings by any rater. Determine how many raters have this maximum number of
  ratings and who those raters are. If you run this code on the file ratings_short.csv, you will see rater 2 has three
  ratings, the maximum number of ratings of all the raters, and that there is only one rater with three ratings.
- Add code to find the number of ratings a particular movie has. If you run this code on the file ratings_short.csv for
  the movie “1798709”, you will see it was rated by four raters.
- Add code to determine how many different movies have been rated by all these raters. If you run this code on the file
  ratings_short.csv, you will see there were four movies rated. 
  