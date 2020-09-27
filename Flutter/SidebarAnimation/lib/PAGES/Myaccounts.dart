import 'package:flutter/material.dart';

class MyAccounts extends StatelessWidget {
  const MyAccounts({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        "Accounts",
        style: TextStyle(fontWeight: FontWeight.bold, fontSize: 28),
      ),
    );
  }
}
