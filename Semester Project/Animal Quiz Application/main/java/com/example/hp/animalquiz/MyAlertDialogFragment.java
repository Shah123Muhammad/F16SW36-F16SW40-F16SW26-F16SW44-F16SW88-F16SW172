package com.example.hp.animalquiz;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public  class MyAlertDialogFragment extends DialogFragment{

    private static final int NUMBER_OF_ANIMALS_INCLUDED_IN_QUIZ = 10;


    private  MainActivityFragment mainActivityFragment;
    private int numberOfAllGuesses;
    private List<String> animalsNamesQuizList;
    private List<String> allAnimalsNamesList;
    private int numberOfRightAnswers;
    private SecureRandom secureRandomNumber;
    private Set<String> animalTypesInQuiz;
    private String correctAnimalsAnswer;
    private TextView txtAnswer;
    private TextView txtQuestionNumber;
    private ImageView imgAnimal;
    private int numberOfAnimalsGuessRows;
    private LinearLayout[] rowsOfGuessButtonsInAnimalQuiz;
    private LinearLayout animalQuizLinearLayout;
    private Animation wrongAnswerAnimation;
    private Handler handler;




   /* public MyAlertDialogFragment(View.OnClickListener mainActivityFragment) {
        this.mainActivityFragment.onShowResult();
    }
*/
    /*  public void ShowResult(){

          View.OnClickListener btnGuessListener = new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Button btnGuess = ((Button) v);
                  String guessValue = btnGuess.getText().toString();
                  String answerValue = getTheExactAnimalName(correctAnimalsAnswer);
                  ++numberOfAllGuesses;

                  if (guessValue.equals(answerValue)) {
                      ++numberOfRightAnswers;

                      txtAnswer.setText(answerValue + "!" + " RIGHT");
                      disableQuizGuessButton();

                      if (numberOfRightAnswers == NUMBER_OF_ANIMALS_INCLUDED_IN_QUIZ) {


                          MyAlertDialogFragment animalQuizResults = new MyAlertDialogFragment();


                          animalQuizResults.setCancelable(false);


                          assert getFragmentManager() != null;
                          animalQuizResults.show(getFragmentManager(), "AnimalQuizResults");
                      } else {
                          handler.postDelayed(new Runnable() {
                              @Override
                              public void run() {
                                  animateAnimalQuiz(true);
                              }
                          }, 1000);//2000 milliseconds for 2 seconds delay
                      }
                  } else {
                      imgAnimal.startAnimation(wrongAnswerAnimation);
                      txtAnswer.setText(R.string.wrong_answer_message);
                      btnGuess.setEnabled(false);
                  }
              }
          };

      }

*/
                    @Override
                    public Dialog onCreateDialog (Bundle savedInstanceState) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        builder.setMessage(getString(R.string.results_string_value, numberOfAllGuesses,
                                (1000 / (double) numberOfAllGuesses)));//Calculating the Result)

                        builder.setPositiveButton(R.string.reset_animal_quiz, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                resetAnimalQuiz();
                            }
                        });

                        return builder.create();
                    }



                     public void resetAnimalQuiz () {

                    AssetManager assets = getActivity().getAssets();
                    allAnimalsNamesList.clear();
                    try {
                        for (String animalType : animalTypesInQuiz) {
                            String[] animalImagePathsInQuiz = assets.list(animalType);

                            for (String animalImagePathInQuiz : animalImagePathsInQuiz) {
                                allAnimalsNamesList.add(animalImagePathInQuiz.replace(".png", ""));
                            }
                        }
                    } catch (IOException e) {
                        Log.e("AnimalQuiz", "Error", e);
                    }
                    numberOfRightAnswers = 0;
                    numberOfAllGuesses = 0;
                    animalsNamesQuizList.clear();

                    int counter = 1;
                    int numberOfAvailableAnimals = allAnimalsNamesList.size();

                    while (counter <= NUMBER_OF_ANIMALS_INCLUDED_IN_QUIZ) {
                        int randomIndex = secureRandomNumber.nextInt(numberOfAvailableAnimals);
                        String animalImageName = allAnimalsNamesList.get(randomIndex);
                        if (!animalsNamesQuizList.contains(animalImageName)) {
                            animalsNamesQuizList.add(animalImageName);
                            ++counter;
                        }
                    }
                    showNextAnimal();
                }
                private void showNextAnimal () {

                    String nextAnimalImageName = animalsNamesQuizList.remove(0);
                    correctAnimalsAnswer = nextAnimalImageName;
                    txtAnswer.setText("");

                    txtQuestionNumber.setText(getString(R.string.question_text, (numberOfRightAnswers + 1), NUMBER_OF_ANIMALS_INCLUDED_IN_QUIZ));
                    String animalType = nextAnimalImageName.substring(0, nextAnimalImageName.indexOf("-"));

                    AssetManager assets = getActivity().getAssets();

                    try (InputStream stream = assets.open(animalType + "/" + nextAnimalImageName + ".png")) {
                        Drawable animalImage = Drawable.createFromStream(stream, nextAnimalImageName);
                        imgAnimal.setImageDrawable(animalImage);

                        animateAnimalQuiz(false);

                    } catch (IOException e) {
                        Log.e("AnimalQuiz", "There is an Error Getting " + nextAnimalImageName, e);
                    }
                    Collections.shuffle(allAnimalsNamesList);

                    int correctAnimalNameIndex = allAnimalsNamesList.indexOf(correctAnimalsAnswer);
                    String correctAnimalName = allAnimalsNamesList.remove(correctAnimalNameIndex);
                    allAnimalsNamesList.add(correctAnimalName);

                    for (int row = 0; row < numberOfAnimalsGuessRows; row++) {
                        for (int column = 0; column < rowsOfGuessButtonsInAnimalQuiz[row].getChildCount(); column++) {

                            Button btnGuess = (Button) rowsOfGuessButtonsInAnimalQuiz[row].getChildAt(column);
                            btnGuess.setEnabled(true);

                            String animalImageName = allAnimalsNamesList.get((row * 2) + column);
                            btnGuess.setText(getTheExactAnimalName(animalImageName), TextView.BufferType.NORMAL);
                        }
                    }
                    int row = secureRandomNumber.nextInt(numberOfAnimalsGuessRows);//generates a random integer value
                    int column = secureRandomNumber.nextInt(2);
                    LinearLayout randomRow = rowsOfGuessButtonsInAnimalQuiz[row];
                    String correctAnimalImageName = getTheExactAnimalName(correctAnimalsAnswer);
                    ((Button) randomRow.getChildAt(column)).setText(correctAnimalImageName);
                }
                private void animateAnimalQuiz ( boolean animateOutAnimalImage){
                    if (numberOfRightAnswers == 0) {
                        return;
                    }

                    int xTopLeft = 0;
                    int yTopLeft = 0;

                    int xBottomRight = animalQuizLinearLayout.getLeft() + animalQuizLinearLayout.getRight();
                    int yBottomRight = animalQuizLinearLayout.getTop() + animalQuizLinearLayout.getBottom();

                    //Here is max value for radius
                    int radius = Math.max(animalQuizLinearLayout.getWidth(), animalQuizLinearLayout.getHeight());

                    Animator animator;

                    if (animateOutAnimalImage) {
                        animator = ViewAnimationUtils.createCircularReveal(animalQuizLinearLayout, xBottomRight, yBottomRight, radius, 0);

                        animator.addListener(new Animator.AnimatorListener() {

                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {

                                showNextAnimal();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                    } else {

                        animator = ViewAnimationUtils.createCircularReveal(animalQuizLinearLayout, xTopLeft, yTopLeft, 0, radius);
                    }

                    animator.setDuration(700);
                    animator.start();
        }

    private void disableQuizGuessButton () {
        for (int row = 0; row < numberOfAnimalsGuessRows; row++) {
            LinearLayout guessRowLinearLayout = rowsOfGuessButtonsInAnimalQuiz[row];
            for (int buttonIndex = 0; buttonIndex < guessRowLinearLayout.getChildCount(); buttonIndex++) {//buttons are the children of LinearLayout
                guessRowLinearLayout.getChildAt(buttonIndex).setEnabled(false);
            }
        }
    }
    private String getTheExactAnimalName (String animalName){
        return animalName.substring(animalName.indexOf('-') + 1).replace('_', ' ');
    }

}
