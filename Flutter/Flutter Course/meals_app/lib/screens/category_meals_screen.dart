import 'package:flutter/material.dart';

class CategoryMealsScreen extends StatelessWidget {
  // CategoryMealsScreen(this.id, this.title);

  // final String id;
  // final String title;

  @override
  Widget build(BuildContext context) {
    final routeArgs =
        ModalRoute.of(context)!.settings.arguments as Map<String, String>;
    final title = routeArgs['title'];
    final id = routeArgs['id'];

    return Scaffold(
      appBar: AppBar(
        title: Text(title!),
      ),
      body: Center(
        child: Text('THE RECIPIES WILL SHOW UP HERE'),
      ),
    );
  }
}
