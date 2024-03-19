import 'package:flutter/material.dart';
import 'package:wordle/app/wordle.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData.dark().copyWith(scaffoldBackgroundColor: const Color.fromARGB(255, 36, 36, 36)),
      home: const HomeScreen(),
    );
  }
}