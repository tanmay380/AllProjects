import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../provider/products.dart';
import './product_item.dart';

class ProductGrid extends StatelessWidget {
  final bool showfvs;

  ProductGrid(this.showfvs);

  @override
  Widget build(BuildContext context) {
    final ProductsData = Provider.of<Products>(context);
    final products = showfvs ? ProductsData.favoriteItems : ProductsData.items;

    return GridView.builder(
      itemCount: products.length,
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
        childAspectRatio: 3 / 2,
        crossAxisSpacing: 10,
        mainAxisSpacing: 10,
      ),
      itemBuilder: (ctx, i) => ChangeNotifierProvider.value(
        value: products[i],
        //  create: (ctx) => products[i],
        child: ProductItem(
            // products[i].id,
            // products[i].title,
            // products[i].imageUrl,
            ),
      ),
      padding: const EdgeInsets.all(10),
    );
  }
}
