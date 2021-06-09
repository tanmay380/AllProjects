import 'package:flutter/material.dart';

import './text_out.dart';

class TextControl extends StatefulWidget {
  @override
  _TextControlState createState() => _TextControlState();
}

class _TextControlState extends State<TextControl> {
  String mainString = "THIS IS THE FIRST ASSIGNMENT";
  int i = 0;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Text(mainString),
        SizedBox(height: 20),
        RaisedButton(
          child: TextOutpit("Change Text BY Clicking"),
          textColor: Colors.white,
          color: Colors.black,
          onPressed: () => setState(() {
            if (i == 0) {
              mainString = " ASSIGNMENT COMPLETED SUCCESSFULLY";
              i = 1;
            } else {
              mainString = " THIS IS THE FIRST ASSIGNMENT";
              i = 0;
            }
          }),
        ),
      ],
    );
  }
}
