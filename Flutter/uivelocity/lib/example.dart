import 'package:flutter/material.dart';
import 'package:velocity_x/velocity_x.dart';

class Example1 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Material(
      color: Colors.red,
      child: Column(
        children: [
          Container(
              color: Colors.blueAccent,
              child: "Text is without shimklsdhfnkldshhhhhhhhhlkdsh\nmer"
                  .text
                  .white
                  .make()
                  .shimmer(primaryColor: Colors.green)),
          HeightBox(20),
          Container(
            child: "Text is with shimmer".text.white.make().shimmer(
                primaryColor: Colors.black,
                secondaryColor: Colors.red,
                duration: Duration(seconds: 5)),
          ),
        ],
      ),
    );
  }
}
