import 'package:flutter/material.dart';

class SideBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Expanded(
          child: Container(
            color: Colors.blue,
          ),
        ),
        Align(
          alignment: Alignment(0, -0.9),
          child: Align(
            alignment: Alignment.center,
            child: ClipPath(
              clipper: CUstomMenu(),
              child: Container(
                  width: 60,
                  height: 100,
                  color: Colors.amber,
                  child: Icon(Icons.menu)),
            ),
          ),
        ),
      ],
    );
  }
}

class CUstomMenu extends CustomClipper<Path> {
  @override
  Path getClip(Size size) {
    Paint paint = Paint();
    paint.color = Colors.white;

    Path path = Path();
    path.moveTo(0, size.height * 1.00);
    path.quadraticBezierTo(0, size.height * 0.25, 0, size.height * 0.01);
    path.cubicTo(size.width * 0.06, size.height * 0.11, size.width * 0.70,
        size.height * 0.07, size.width * 0.80, size.height * 0.36);
    path.cubicTo(size.width * 0.85, size.height * 0.54, size.width * 0.75,
        size.height * 0.70, size.width * 0.70, size.height * 0.76);
    path.quadraticBezierTo(
        size.width * 0.55, size.height * 0.94, 0, size.height * 1.00);
    path.close();

    return path;
  }

  @override
  bool shouldReclip(covariant CustomClipper<Path> oldClipper) {
    throw UnimplementedError();
  }
}
// @override
// bool shouldRepaint(covariant CustomPainter oldDelegate) {
//   return true;
// }
