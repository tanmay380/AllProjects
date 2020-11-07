import 'dart:io';

import 'package:flutter/material.dart';
import 'package:sidemenu/profile.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primaryColor: Colors.indigo,
        brightness: Brightness.dark,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    var scaffold = Scaffold(
      appBar: AppBar(
        leading: Builder(
          builder: (context) {
            return IconButton(
              icon: const Icon(Icons.menu),
              onPressed: () {
                Scaffold.of(context).openDrawer();
              },
            );
          },
        ),
        title: Text("Side Menu"),
        centerTitle: true,
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: [
            DrawerHeader(
              child: Text("HEllo first sideabr",
                  style: TextStyle(fontSize: 20), textAlign: TextAlign.center),
              decoration: BoxDecoration(
                image: DecorationImage(
                    image: AssetImage("assets/images/dp.jpg"),
                    fit: BoxFit.cover),
              ),
            ),
            ListTile(
              leading: Text("PROFILE"),
              trailing: Icon(Icons.verified_user),
              onTap: () {
                Navigator.pop(context);
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => Profile()));
              },
            ),
            ListTile(
              leading: Text("SIGN OUT"),
              trailing: Icon(Icons.exit_to_app),
              onTap: () {
                Navigator.pop(context);
              },
            )
          ],
        ),
      ),
    );
    return scaffold;
  }
}
