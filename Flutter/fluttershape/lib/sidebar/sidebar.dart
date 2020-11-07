import 'package:flutter/material.dart';
import 'package:fluttershape/sidebar/homepage.dart';
import 'package:fluttershape/sidebar/sideabarmain.dart';

class SideBarLayout extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Stack(
          children: [
            HomePage(),
            SideBar(),
          ],
        ),
      ),
    );
  }
}
