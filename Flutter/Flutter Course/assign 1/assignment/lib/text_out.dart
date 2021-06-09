import 'package:flutter/material.dart';

class TextOutpit extends StatelessWidget {
  final String maintext;
  TextOutpit(this.maintext);

  @override
  Widget build(BuildContext context) {
    return Text(maintext);
  }
}
