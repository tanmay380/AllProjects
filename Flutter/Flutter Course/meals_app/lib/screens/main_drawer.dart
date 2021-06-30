import 'package:flutter/material.dart';

class main_drawer extends StatelessWidget {
  const main_drawer({Key? key}) : super(key: key);
  Widget buildListtile(String title, IconData icon) {
    return ListTile(
      leading: Icon(
        icon,
        size: 26,
      ),
      title: Text(
        title,
        style: TextStyle(
            fontFamily: 'RobotoCondensed',
            fontSize: 24,
            fontWeight: FontWeight.w900),
      ),
      onTap: () => {},
    );
  }

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: Column(
        children: [
          Container(
            height: 120,
            width: double.infinity,
            alignment: Alignment.centerLeft,
            color: Theme.of(context).accentColor,
            child: Text(
              'Cooking Up ',
              style: TextStyle(
                  fontSize: 30,
                  color: Theme.of(context).primaryColor,
                  fontWeight: FontWeight.w900),
            ),
          ),
          buildListtile('Meals', Icons.restaurant),
          buildListtile('Filters', Icons.settings),
        ],
      ),
    );
  }
}
