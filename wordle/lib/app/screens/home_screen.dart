import 'package:flutter/material.dart';
import 'package:wordle/app/data/colors.dart';
import 'dart:math';
import 'package:wordle/app/wordle.dart';

enum GameStatus {playing, submitting, lost, won}

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  ScreenState createState() => ScreenState();
}

class ScreenState extends State<HomeScreen> {
  
  GameStatus gameStatus = GameStatus.playing;

  final List<Word> board = List.generate(6, (i) => Word(letters: List.generate(5, (i) => Letter.empty())));

  int currWordIndex = 0;

  Word? get currWord => currWordIndex < board.length ? board[currWordIndex] : null;

  Word solution = Word.fromString(fiveLetterWords[Random().nextInt(fiveLetterWords.length)].toUpperCase());

  final Set<Letter> keyboardLetters = {};

  @override
  // Widget build(BuildContext context) {
  //   return Scaffold(
  //     body: Column(
  //       mainAxisAlignment: MainAxisAlignment.center,
  //       children: [

  //         const Padding(
  //           padding: EdgeInsets.symmetric(horizontal: 30, vertical: 30),
  //           child: Text(
  //             'Wordle',
  //             style: TextStyle(
  //               fontSize: 52,
  //               fontWeight: FontWeight.bold,
  //               letterSpacing: 4,
  //           ))
  //         ),

  //         Board(board: board),

  //         const SizedBox(height: 80),

  //         Keyboard(onKeyTapped: onKeyTapped,
  //                  onDeleteTapped: onDeleteTapped,
  //                  onEnterTapped: onEnterTapped,
  //                  letters: keyboardLetters)
  //       ],
  //     )
  //   );
  // }

  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('CSC 325'),
      ),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Padding(
                padding: EdgeInsets.symmetric(horizontal: 30, vertical: 30),
                child: Text(
                  'Wordle',
                  style: TextStyle(
                    fontSize: 52,
                    fontWeight: FontWeight.bold,
                    letterSpacing: 4,
                  ),
                ),
              ),
              Board(board: board),
              const SizedBox(height: 80),
            
              Keyboard(onKeyTapped: onKeyTapped,
                   onDeleteTapped: onDeleteTapped,
                   onEnterTapped: onEnterTapped,
                   letters: keyboardLetters)
            ],
          ),
        ),
      ),
    );
  }


  void onKeyTapped(String val) {
    if (gameStatus == GameStatus.playing) {
      setState(() => currWord?.addLetter(val));
    }
  }

  void onDeleteTapped() {
    if (gameStatus == GameStatus.playing) {
      setState(() => currWord?.removeLetter());
    }
  }

   void onEnterTapped() {
    if (gameStatus == GameStatus.playing && currWord != null && !currWord!.letters.contains(Letter.empty())) {
      gameStatus == GameStatus.submitting;

      for (var i = 0; i < currWord!.letters.length; i++) {
        final currWordLetter = currWord!.letters[i];
        final currSolutionLetter = solution.letters[i];

        setState(() {
          if (currWordLetter == currSolutionLetter) {
            currWord!.letters[i] = currWordLetter.copyWith(status: LetterStatus.correct);

          } else if (solution.letters.contains(currWordLetter)) {
            currWord!.letters[i] = currWordLetter.copyWith(status: LetterStatus.inWord);

          } else {
            currWord!.letters[i] = currWordLetter.copyWith(status: LetterStatus.incorrect);
          }
        });

        final letter = keyboardLetters.firstWhere(
          (e) => e.val == currWordLetter.val,
          orElse: () => Letter.empty()
        );

        if (letter.status != LetterStatus.correct) {
          keyboardLetters.removeWhere((e) => e.val == currWordLetter.val);
          keyboardLetters.add(currWord!.letters[i]);
        }

      }

      checkIfWinOrLose();
    }
  }

  void checkIfWinOrLose() {
    if (currWord!.wordString == solution.wordString) {
      gameStatus = GameStatus.won;

      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          dismissDirection: DismissDirection.none,
          duration: const Duration(days: 1),
          backgroundColor: correctColor,
          content: const Text(
            'You won!',
            style: TextStyle(color: Colors.white),
          ),
          action: SnackBarAction(
            onPressed: restart,
            textColor: Colors.white,
            label: 'New Game',
          ),
        )
      );

    } else if (currWordIndex + 1 >= board.length) {
      gameStatus = GameStatus.lost;

      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          dismissDirection: DismissDirection.none,
          duration: const Duration(days: 1),
          backgroundColor: Color.fromARGB(255, 154, 42, 42),
          content: Text(
            'You lost... Solution: ${solution.wordString})',
            style: const TextStyle(color: Colors.white),
          ),
          action: SnackBarAction(
            onPressed: restart,
            textColor: Colors.white,
            label: 'New Game',
          ),
        )
      );

    } else {
      gameStatus = GameStatus.playing;
    }

    currWordIndex += 1;
  }

  void restart() {
    setState(() {
      gameStatus =GameStatus.playing;
      currWordIndex = 0;
      board
        ..clear()
        ..addAll(
          List.generate(6, (_) => Word(letters: List.generate(5, (_) => Letter.empty())))
        );

      solution = Word.fromString(fiveLetterWords[Random().nextInt(fiveLetterWords.length)].toUpperCase());

      keyboardLetters.clear();
    });
  }
}