import 'package:flutter/material.dart';
import 'package:shop_app/screens/edit_product_screen.dart';

class UserProduct extends StatelessWidget {
  final String id;
  final String title;
  final String imageurl;

  const UserProduct(this.id, this.title, this.imageurl);

  @override
  Widget build(BuildContext context) {
    return Card(
        elevation: 8,
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: ListTile(
            title: Text(
              title,
            ),
            leading: CircleAvatar(
              backgroundImage: NetworkImage(imageurl),
            ),
            trailing: Container(
              width: 100,
              child: Row(
                children: [
                  IconButton(
                      onPressed: () {
                        Navigator.of(context).pushNamed(
                            EditProductScreen.routeName,
                            arguments: id);
                      },
                      icon: Icon(Icons.edit),
                      color: Theme.of(context).primaryColor),
                  IconButton(
                    onPressed: () {},
                    icon: Icon(Icons.delete),
                    color: Theme.of(context).errorColor,
                  )
                ],
              ),
            ),
          ),
        ));
  }
}
