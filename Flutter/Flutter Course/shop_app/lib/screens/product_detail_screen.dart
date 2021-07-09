import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../provider/products.dart';

class ProductDetailScreen extends StatelessWidget {
  static const routename = '/detailscreen';
  //ProductDetailScreen(this.title);

  @override
  Widget build(BuildContext context) {
    final productId = ModalRoute.of(context)!.settings.arguments as String;
    final loadedProduct = Provider.of<Products>(
      context,
      listen: false,
    ).findById(productId);
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        title: Text(loadedProduct.title),
      ),
      body: Column(
        children: [
          Container(
            child: Image.network(
              loadedProduct.imageUrl,
              fit: BoxFit.scaleDown,
            ),
          ),
          SizedBox(
            height: 20,
          ),
          Padding(
            padding: const EdgeInsets.all(10.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(
                  loadedProduct.title,
                  style: TextStyle(fontSize: 30, color: Colors.white),
                ),
                Text(
                  loadedProduct.price.toString(),
                  style: TextStyle(fontSize: 30, color: Colors.white),
                )
              ],
            ),
          ),
          SizedBox(height: 30),
          Text(
            loadedProduct.description,
            style: TextStyle(fontSize: 30, color: Colors.white),
          )
        ],
      ),
    );
  }
}
