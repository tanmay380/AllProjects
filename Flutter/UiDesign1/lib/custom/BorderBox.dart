import 'package:flutter/material.dart';

class BorderBox extends StatelessWidget {
  final Widget child;
  final EdgeInsets padding;
  final double width, heigth;

  const BorderBox(
      {Key key, this.padding, this.width, this.heigth, @required this.child})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Widget();
  }
}
