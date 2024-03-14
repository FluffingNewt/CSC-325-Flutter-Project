import 'package:flutter/material.dart';

class App extends StatelessWidget {
  const App({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Wordle App',
      theme: ThemeData.dark().copyWith(scaffoldBackgroundColor: Colors.black),
      home: const GameScreen(),
    );
  }
}