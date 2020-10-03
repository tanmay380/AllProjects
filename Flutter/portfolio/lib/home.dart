import 'package:flutter/material.dart';
import 'package:portfolio/cooler.dart';
import 'package:portfolio/header.dart';
import 'package:velocity_x/velocity_x.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Material(
      color: Coolers.primaryColor,
      child: VStack([
        HeaderScreen(),
      ]).scrollVertical(),
    );
  }
}
