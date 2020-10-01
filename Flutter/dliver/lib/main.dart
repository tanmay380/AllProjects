import 'package:dliver/myappbar.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

/// This Widget is the main application widget.
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "helldjkchd",
      home: Scaffold(
        body: CustomScrollView(
          slivers: [
            SliverAppBar(
              pinned: true,
              title: MyAppBar(),
              centerTitle: true,
              expandedHeight: 200,
              flexibleSpace: Container(
                height: 200,
                color: Colors.red,
              ),
            ),
            SliverList(delegate: SliverChildListDelegate(_buildList(50)))
          ],
        ),
      ),
    );
  }

  List _buildList(int count) {
    List<Widget> listItems = List();
    for (int i = 0; i < count; i++) {
      listItems.add(new Padding(
          padding: new EdgeInsets.all(20.0),
          child: new Text('Item ${i.toString()}',
              style: new TextStyle(fontSize: 25.0))));
    }
    return listItems;
  }
}
