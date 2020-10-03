import 'package:flutter/material.dart';
import 'package:portfolio/cooler.dart';
import 'package:portfolio/home.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Portfolio',
        theme: ThemeData(
          primarySwatch: Colors.orange,
          accentColor: Coolers.accentColor,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: HomeScreen());
  }
}
