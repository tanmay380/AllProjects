import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/provider/products.dart';

import 'package:shop_app/widgets/products_grid.dart';

import '../provider/product.dart';

enum FilterOptions {
  Favorites,
  All,
}

class ProductsOverviewScreen extends StatefulWidget {
  static const routename = '/overviewscreen';

  @override
  _ProductsOverviewScreenState createState() => _ProductsOverviewScreenState();
}

class _ProductsOverviewScreenState extends State<ProductsOverviewScreen> {
  bool _showonlyfavs = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('MyShop'),
        actions: [
          PopupMenuButton(
              onSelected: (FilterOptions selectedvalue) {
                setState(() {
                  if (selectedvalue == FilterOptions.Favorites) {
                    _showonlyfavs = true;
                  } else {
                    _showonlyfavs = false;
                  }
                });
              },
              icon: Icon(
                Icons.more_vert,
              ),
              itemBuilder: (_) => [
                    PopupMenuItem(
                        child: Text('Only Favorites'),
                        value: FilterOptions.Favorites),
                    PopupMenuItem(
                        child: Text('Show All'), value: FilterOptions.All),
                  ]),
        ],
      ),
      body: ProductGrid(_showonlyfavs),
    );
  }
}
