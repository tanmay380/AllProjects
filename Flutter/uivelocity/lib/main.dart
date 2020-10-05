import 'package:flutter/material.dart';
import 'package:uivelocity/example.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
          primaryColor: Colors.red,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: Example1());
  }
}
