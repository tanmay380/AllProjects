import 'package:flutter/material.dart';

class SideBar extends StatelessWidget {
  const SideBar({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Expanded(
          child: Container(
            color: Color(0xFF262AAA),
          ),
        ),
        Container(
          width: 35,
          height: 110,
        )
      ],
    );
  }
}
