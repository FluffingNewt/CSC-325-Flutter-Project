import 'package:equatable/equatable.dart';
import 'package:wordle/app/wordle.dart';

class Word extends Equatable {

  final List<Letter> letters;

  const Word({required this.letters});

  factory Word.fromString(String word) => Word(letters: word.split('').map((e) => Letter(val: e)).toList());


  // Adds letter to current word.
  void addLetter(String val) {
    final currIndex = letters.indexWhere((e) => e.val.isEmpty);

    if (currIndex != -1) {
      letters[currIndex] = Letter(val: val);
    }
  }


  // Removes letter from current word.
  void removeLetter() {
    final recentLetterIndex = letters.lastIndexWhere((e) => e.val.isNotEmpty);

    if (recentLetterIndex != -1) {
      letters[recentLetterIndex] = Letter.empty();
    }
  }


  // returns a String representation of the current word.
  String get wordString => letters.map((e) => e.val).join();



  @override
  List<Object?> get props => [letters];


}