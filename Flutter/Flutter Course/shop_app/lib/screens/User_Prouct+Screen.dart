import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/screens/edit_product_screen.dart';
import 'package:shop_app/widgets/add_drawer.dart';
import 'package:shop_app/widgets/user_products.dart';

import '../provider/products.dart';

class UserProductScreen extends StatelessWidget {
  const UserProductScreen({Key? key}) : super(key: key);
  static const routeName = '/userscreen';

  @override
  Widget build(BuildContext context) {
    // final produtData = Provider.of<Products>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text("Your Products"),
        actions: [
          IconButton(
              onPressed: () {
                Navigator.of(context).pushNamed(EditProductScreen.routeName);
              },
              icon: Icon(Icons.add)),
        ],
      ),
      drawer: AppDrawer(),
      body: Consumer<Products>(
        builder: (_, products, _2) => Padding(
          padding: EdgeInsets.all(8),
          child: ListView.builder(
            itemBuilder: (_, i) => Column(
              children: [
                UserProduct(products.items[i].id, products.items[i].title,
                    products.items[i].imageUrl),
                Divider()
              ],
            ),
            itemCount: products.items.length,
          ),
        ),
      ),
    );
  }
}
