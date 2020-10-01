import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';

class MyAppBar extends StatelessWidget {
  const MyAppBar({Key key}) : super(key: key);

  final double barHeight = 66.0;

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Container(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Icon(
                Icons.menu,
                color: Colors.black,
              ),
            ),
          ),
          Container(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(
                'My Digital Currency',
                style: TextStyle(
                    color: Colors.white, fontFamily: 'Poppins', fontSize: 20.0),
              ),
            ),
          ),
          Container(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Icon(
                Icons.account_box,
                color: Colors.white,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
