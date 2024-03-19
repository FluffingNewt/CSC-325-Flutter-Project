import 'package:flutter_test/flutter_test.dart';
import 'package:wordle/app/app.dart';
import 'package:wordle/app/wordle.dart';

void main() {
  testWidgets('Smoke Test', (WidgetTester tester) async {

    await tester.pumpWidget(const App());

    expect(find.text('CSC 325'), findsOneWidget);
    expect(find.byType(HomeScreen), findsOneWidget);

  });
}